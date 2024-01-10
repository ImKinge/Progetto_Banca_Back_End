package com.banca.banca.helper;

import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Random;

@Component
public class BankHelper {

    /**
     * Restituisce un iban autogenerato con prefisso "IT"
     * Faccio un for che deve iterare 20 volte, dove ad ogni iterazione deve inserire un numero random della stringa alphabet
     *
     *
     * @return un iban autogenerato
     */
    public String generateIban() {

        String bufferIban = "IT";

        final String alphabet = "0123456789";
        final int N = alphabet.length();

        Random r = new Random();

        for (int i = 0; i < 20; i++) {
            bufferIban = bufferIban + (alphabet.charAt(r.nextInt(N)));
        }

        return bufferIban;
    }


    /**
     * Restituisce un numero di carta autogenerato con logica identica al metodo generateIban()
     *
     * @return una stringa di 16 numeri
     */
    public String generateCardNumber() {

        String bufferCard = "";

        final String alphabet = "0123456789";
        final int N = alphabet.length();

        Random r = new Random();

        for (int i = 0; i < 16; i++) {
            bufferCard = bufferCard + (alphabet.charAt(r.nextInt(N)));
        }

        return bufferCard;

    }

    public String generateSerialOrder () {

        String prefix = "IT";
        final String number = "123456789";
        final int n = number.length();

        Random r = new Random();

        for (int i = 0; i < 7; i++) {
            prefix += (number.charAt(r.nextInt(n)));
        }

        return prefix;
    }
}
