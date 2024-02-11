package com.example.limechain.service.api;

import com.example.limechain.dto.TransactionResponse;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface TransactionService {
    Set<TransactionResponse> getTransactions(List<String> transactionHashes, String username) throws IOException;

    List<TransactionResponse> getAll();

    List<TransactionResponse> getMy(String username);
}
