package com.example.limechain.service;

import com.example.limechain.model.Transaction;
import com.example.limechain.repository.TransactionRepository;
import com.example.limechain.service.api.TransactionService;
import com.example.limechain.service.api.Web3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final Web3Service web3Service;

    @Override
    public Set<Transaction> getTransactions(List<String> transactionHashes, String username) {
         final List<Transaction> transactionsExist = transactionHashes.stream()
                 .map(transactionRepository::findByHash)
                 .filter(Objects::nonNull)
                 .toList();
         final List<Transaction> addUserTransactions = transactionsExist.stream()
                 .filter(tx -> !tx.getUsers().contains(username))
                 .peek(tx -> {
                     List<String> users = tx.getUsers();
                     users.add(username);
                     tx.setUsers(users);
                 })
                 .toList();

        transactionHashes.removeAll(transactionsExist.stream().map(Transaction::getHash).toList());
        final List<Transaction> transactions = web3Service.getTransactions(transactionHashes, username);
        transactions.addAll(addUserTransactions);
        transactionRepository.saveAll(transactions);
        transactions.addAll(transactionsExist);
        return new HashSet<>(transactions);
    }

    @Override
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getMy(String username) {
        return transactionRepository.findByUser(username);
    }
}
