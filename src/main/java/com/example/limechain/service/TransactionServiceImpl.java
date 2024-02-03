package com.example.limechain.service;

import com.example.limechain.model.Transaction;
import com.example.limechain.repository.TransactionRepository;
import com.example.limechain.service.api.TransactionService;
import com.example.limechain.service.api.Web3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    Web3Service web3Service;

    @Override
    public List<Transaction> getTransactions(List<String> transactionHashes) {
         final List<Transaction> transactionsExist = transactionHashes.stream()
                 .map(hash -> transactionRepository.findByHash(hash))
                 .filter(Objects::nonNull)
                 .collect(Collectors.toList());
        transactionHashes.removeAll(transactionsExist.stream().map(Transaction::getHash).toList());
        final List<Transaction> transactions = web3Service.getTransactions(transactionHashes);
        transactionRepository.saveAll(transactions);
        transactions.addAll(transactionsExist);
        return transactions;
    }

    @Override
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }
}
