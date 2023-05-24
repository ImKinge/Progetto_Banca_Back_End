package com.banca.banca.repository;

import com.banca.banca.entity.Card;
import com.banca.banca.entity.CurrentAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CardRepository extends JpaRepository <Card, String> {

    /*
        Optional è utilizzato per un valore che può o non può essere presente.
        Quindi un oggetto Optional può contenere un valore non nullo, quindi presente
        oppure può contenere un valore nullo, quindi vuoto
     */
    Optional<Card> findByCardNumber (String cardNumber);

    @Query(value = "select i from Card i where i.currentAccount.customerData.fiscalCode=?1")
    Optional<Card> findByFiscalCode (String fiscalCode);
}
