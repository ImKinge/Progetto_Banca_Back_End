package com.banca.banca.service;

import com.banca.banca.dto.IbanTransactionDto;
import com.banca.banca.dto.TransferDto;
import com.banca.banca.entity.CurrentAccount;
import com.banca.banca.entity.CustomerData;
import com.banca.banca.entity.IbanTransaction;
import com.banca.banca.exception.CurrentAccountException;
import com.banca.banca.exception.CustomerDataException;
import com.banca.banca.exception.TransferException;
import com.banca.banca.mapper.IbanTransactionMapper;
import com.banca.banca.repository.CurrentAccountRepository;
import com.banca.banca.repository.CustomerDataRepository;
import com.banca.banca.repository.IbanTransactionRepository;
import com.banca.banca.security.jwt.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;



@Service
public class IbanTransactionServiceImpl implements IbanTransactionService {


    @Autowired
    private IbanTransactionRepository ibanTransactionRepository;

    @Autowired
    private CurrentAccountRepository currentAccountRepository;

    @Autowired
    private CustomerDataRepository customerDataRepository;

    @Autowired
    private IbanTransactionMapper ibanTransactionMapper;

    @Autowired
    private JWTGenerator jwtGenerator;


    @Override
    public Optional<IbanTransaction> findIbanById(Integer id) {
        return ibanTransactionRepository.findById(id);
    }

    /**
     * Una volta recuperate le transazione tramite codice fiscale gestiamo la logica sull'amount e sulla descrizione in base
     * che sia o ordinario o beneficiario. Se sarò un lista di transazioni dell'ordinario trasformiamo l'amount in negativo
     *
     * @param fiscalCode lo prendiamo dal token
     * @return lista di transazioni iban
     */
    @Override
    public List<IbanTransactionDto> getIbanTransaction(String fiscalCode) {
        List<IbanTransaction> ibanTransactions = ibanTransactionRepository.findAllTransactionByFiscalCode(fiscalCode);

        for (IbanTransaction ibanTransaction : ibanTransactions) {
            if (fiscalCode.equals(ibanTransaction.getCustomerDataOr().getFiscalCode())) {
                ibanTransaction.setAmount(ibanTransaction.getAmount() * -1);
                ibanTransaction.setDescriptionTransaction(ibanTransaction.getDescriptionTransaction() + " inviato a " + ibanTransaction.getCustomerDataBf().getName() + " " + ibanTransaction.getCustomerDataBf().getSurname());
            } else {
                ibanTransaction.setDescriptionTransaction(ibanTransaction.getDescriptionTransaction() + " ricevuto da " + ibanTransaction.getCustomerDataOr().getName() + " " + ibanTransaction.getCustomerDataOr().getSurname());
            }
        }

        return ibanTransactionMapper.toListIbanTransactionDto(ibanTransactions);
    }

    /**
     * In input ho un dto con i seguenti campi: ibanBeneficiary, amount, description, name(beneficiary), surname(beneficiary)
     * Faccio la query per trovare l'iban del beneficiario per un determinato ibanNumber
     * Vado a fare un controllo per l'iban del beneficiario che mi occorre per fare il bonifico facendo una query dove mi restituisce
     * quel determinato record dell'iban del beneficiario se esiste, altrimenti torna una eccezione se non esiste
     * Una volta restituito vado a fare delle operazioni sui balance dei due iban che si sono scambiati i soldi:
     * per il beneficiario andro ad inserire un'operazione del balance + l'importo dell'oggetto passato in entrata al metodo
     * e faccio una salvataggio(update) di quel conto corrente.
     * Stessa cosa per l'ordinario ma stavolta sarà balance - l'importo
     * Il beneficiario otterrà i soldi del beneficiario e quindi un incremento del suo budget
     * L'ordinario avrà un decremento del suo budget
     * Infine salvo il record (oggetto IbanTransaction) dentro alla tabella delle transazioni dell'iban iban_transaction
     * 
     * 1 - Parametri input TransferDto (ibanBeneficiary, amount, description, name(beneficiary), surname(beneficiary)
     * 2 - Controllo dell'amount:
     *      * se è < 1 lancia una eccezione
     * 3 - query per trovare il conto corrente con l'iban passato in input ---> findByIbanNumber
     * 4 - Controllo:
     *      * se non è presente scatta una eccezione
     *      * se è prensente : torno l'intero record
     * 5 - operazioni sui balance beneficiario e ordinario:
     *      * beneficiario: setto il balance = balance + amount e salvataggio(update) dell'oggetto
     *      * ordinario: setto il balance = balance - amount e salvataggio(update) dell'oggetto
     * 6 - Creazione entity (ibanTransaction)
     * 7 - Settaggio dei campi amount, descriptionTransaction, customerDataBf, customerDataOr
     * 8 - Controllo:
     *      * se customerDataBf (nome e cognome) non matcha con i dati passati in input lancia una eccezione
     *      * se customerDataOr (nome e cognome) non matcha con i dati passati (al momento schiantati) lancia un'eccezione
     * 9 - Salvataggio su db
     *
     * @param transferDto input in ingresso
     * @return ibanTransaction
     * @throws TransferException eccezzione lanciata nel caso in cui o iban, o nome, o cognome non matchano con lo stesso conto corrente
     */
    @Override
    /*
    ABBIAMO UTILIZZATO TRANSACTIONAL PERCHE DAL MOMENTO CHE ESEGUE PIU OPERAZIONI CONTEMPORANEAMENTE:
    2 UPDATE E 1 INSERT
    SE L'INSERT DOVESSE FALLIRE FA UN ROLLBACK ED è COME SE TORNASSE INDIETRO E LE TIENE IN STANDBY
    FINO A QUANDO QUEL DETERMINATO PROBLEMA NON SI RISOLVE
     */
    @Transactional(rollbackFor = Exception.class)
    public IbanTransaction makeTransfers(TransferDto transferDto, String token) throws TransferException, CustomerDataException, CurrentAccountException {

        //Variabile d'appoggio per non richiamare sempre il getter di amount, name e surname del transferObject
        Double transferAmount = transferDto.getAmount();
        String transferName = transferDto.getName();
        String transferSurname = transferDto.getSurname();
        Date transferDate = transferDto.getDateTransaction();

        //Eccezzione se il valore che devo inviare(amount) è < 1
        if (transferAmount <= 0) {
            throw new TransferException("Wrong Amount! Enter a value greater than zero");
        }

        //Recupero i dati dell'utente loggato che deve fare il bonifico
        String fiscalCode = jwtGenerator.getFiscalCodeFromJWT(token);
        CustomerData customerDataOpt = customerDataRepository.findByFiscalCode(fiscalCode).orElseThrow(() -> new CustomerDataException("Customer data not find!"));
        /*
        Recupero il CurrentAccount dell'ordinario (colui che fa il bonifico) che poi mi serviranno
        per popolare l'ordinario dell'oggetto Ibantransaction
         */
        List<CurrentAccount> currentAccountList = customerDataOpt.getCurrentAccountList();
        CurrentAccount currentAccount = currentAccountList.stream().findFirst().orElseThrow(() -> new CurrentAccountException("Nothing current account find!"));
        String ibanOrdinary = currentAccount.getIbanNumber();
        String name = customerDataOpt.getName();
        String surname = customerDataOpt.getSurname();

        //Recupero il record per l'iban che mi arriva dall'oggetto in input della richiesta(ibanBeneficiary)
        Optional<CurrentAccount> currentAccountBeneficiaryOpt = currentAccountRepository.findByIbanNumber(transferDto.getIbanBeneficiary());

        /*
        if(!currentAccountBeneficiaryOpt.isPresent()) {
            throw new IllegalArgumentException("Iban not present!");
        }
        CurrentAccount currentAccountBeneficiary = currentAccountBeneficiaryOpt.get();

        Tutto quello scritto sopra lo traduciamo in questa riga di codice
         */
        CurrentAccount currentAccountBeneficiary = currentAccountBeneficiaryOpt.orElseThrow(() -> new TransferException("Beneficiary iban not present!"));

        //Beneficiary
        currentAccountBeneficiary.setBalance(currentAccountBeneficiary.getBalance() + transferAmount);
        currentAccountRepository.save(currentAccountBeneficiary);

        //Orderer
        //Recupero il record per l'iban che mi arriva dall'oggetto in input (ibanOrdinary)
        Optional<CurrentAccount> currentAccountOrdererOpt = currentAccountRepository.findByIbanNumber(ibanOrdinary);
        CurrentAccount currentAccountOrdinary = currentAccountOrdererOpt.orElseThrow(() -> new TransferException("Orderer iban not present!"));
        if (currentAccountOrdinary.getBalance() < transferAmount) {
            throw new TransferException("Balance minore dell'amount!");
        }
        currentAccountOrdinary.setBalance(currentAccountOrdinary.getBalance() - transferAmount);
        currentAccountRepository.save(currentAccountOrdinary);


//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//        String formatDateTime = now.format(formatter);
//        LocalDateTime localDateTime = LocalDateTime.parse(formatDateTime, formatter);
//        ibanTransaction.setDateTransaction(localDateTime);

        //Salvataggio dell'entity su db
        LocalDateTime localDateTime = LocalDateTime.ofInstant(transferDate.toInstant(), ZoneId.systemDefault());

        IbanTransaction ibanTransaction = new IbanTransaction();
        ibanTransaction.setAmount(transferAmount);
        ibanTransaction.setDateTransaction(localDateTime);
        ibanTransaction.setDescriptionTransaction("Bonifico: " + transferDto.getDescription());


        if(!currentAccountBeneficiary.getCustomerData().getName().equals(transferName) &&
                !currentAccountBeneficiary.getCustomerData().getSurname().equals(transferSurname)) {
            throw new TransferException("Can not find Beneficiary with name " + transferName + " and surname " + transferSurname);
        }
        ibanTransaction.setCustomerDataBf(currentAccountBeneficiary.getCustomerData());

        if(!currentAccountOrdinary.getCustomerData().getName().equals(name) &&
                !currentAccountOrdinary.getCustomerData().getSurname().equals(surname)) {
            throw new TransferException("Can not find Ordinary with name " + name + " and surname " + surname);
        }
        ibanTransaction.setCustomerDataOr(currentAccountOrdinary.getCustomerData());

        /*
            Il beneficiario può essere uno tra i proprietari del conto corrente, perchè può essere cointestato,
            quindi filtriamo una lista che deve essere uguale al transferName e transferSurname per il beneficiario,
            mentre uguale al name e surname per l'ordinario
         */
//        ibanTransaction.setCustomerDataBf(currentAccountBeneficiary.getCustomerData()
//                .stream()
//                .filter(customerData -> customerData.getName().equals(transferName) && customerData.getSurname().equals(transferSurname))
//                .findFirst().orElseThrow(() -> new TransferException("Can not find beneficiary with name " + transferName + " and surname " + transferSurname)));
//        ibanTransaction.setCustomerDataOr(currentAccountOrdinary.getCustomerData()
//                .stream()
//                .filter(customerData -> customerData.getName().equals(name) && customerData.getSurname().equals(surname))
//                .findFirst().orElseThrow(() -> new TransferException("Can not find beneficiary with name " + name + " and surname " + surname)));

        return ibanTransactionRepository.save(ibanTransaction);
    }


}
