package com.example.limechain.repository;

import com.example.limechain.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository  extends JpaRepository<Transaction, Long> {
    Transaction findByHash(String hash);
}
