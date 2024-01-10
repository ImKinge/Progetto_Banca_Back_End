package com.banca.banca.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    public Cart() {
    }



    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

}
