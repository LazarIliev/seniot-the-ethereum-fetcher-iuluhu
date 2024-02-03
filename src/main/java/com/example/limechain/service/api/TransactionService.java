package com.example.limechain.service.api;

import com.example.limechain.model.Transaction;

import java.io.IOException;
import java.util.List;

public interface TransactionService {
    List<Transaction> getTransactions(List<String> transactionHashes) throws IOException;

    List<Transaction> getAll();
}
