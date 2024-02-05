package com.example.limechain.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hash;
    private String blockHash;
    private int transactionStatus;
    private BigInteger blockNumber;
    private String fromAddress;
    private String toAddress;
    private String contractAddress;
    private int logsCount;
    private String input;
    private String value;
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "users")
    private List<String> users = new ArrayList<>();
}
