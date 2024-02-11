package com.example.limechain.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class TransactionResponse {
    private final String hash;
    private final String blockHash;
    private final int transactionStatus;
    private final BigInteger blockNumber;
    private final String fromAddress;
    private final String toAddress;
    private final String contractAddress;
    private final int logsCount;
    private final String input;
    private final String value;
}
