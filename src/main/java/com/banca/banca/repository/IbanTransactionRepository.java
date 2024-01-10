package com.banca.banca.repository;
import com.banca.banca.entity.IbanTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface IbanTransactionRepository extends JpaRepository <IbanTransaction, Integer> {

    /*
        Optional è utilizzato per un valore che può o non può essere presente.
        Quindi un oggetto Optional può contenere un valore non nullo, quindi presente
        oppure può contenere un valore nullo, quindi vuoto
     */
    Optional<IbanTransaction> findById(Integer id);

    /*
       Tramite Query e con JPQL andiamo ad eseguire un'interrogazione sulle ENTITY e non sul DB
       SELECT * FROM `iban_transaction` WHERE orderer='FLAGMR95R25E958I' OR beneficiary='FLAGMR95R25E958I' ORDER BY date_transaction DESC
       1 rappresenta il numero dei parametri, abbiamo solo fiscal code
    */
    @Query(value = "select i from IbanTransaction i where i.customerDataOr.fiscalCode=?1 or i.customerDataBf.fiscalCode=?1 ORDER BY i.dateTransaction DESC")
    List<IbanTransaction> findAllTransactionByFiscalCode (String fiscalCode);

    /*

     */
    @Query(value = "select i from IbanTransaction i where i.customerDataOr.fiscalCode=?1 and i.dateTransaction between ?2 and ?3")
    List<IbanTransaction> makeReportTransactionIban (String fiscalCode, LocalDateTime startDate, LocalDateTime endDate);

}
