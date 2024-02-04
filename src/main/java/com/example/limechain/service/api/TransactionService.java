package com.example.limechain.service.api;

import com.example.limechain.model.Transaction;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface TransactionService {
    Set<Transaction> getTransactions(List<String> transactionHashes, String username) throws IOException;

    List<Transaction> getAll();

    List<Transaction> getMy(String username);
}
