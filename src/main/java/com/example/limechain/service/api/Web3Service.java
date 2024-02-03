package com.example.limechain.service.api;

import com.example.limechain.model.Transaction;

import java.util.List;

public interface Web3Service {
    List<Transaction> getTransactions(List<String> transactionHashes);
}
