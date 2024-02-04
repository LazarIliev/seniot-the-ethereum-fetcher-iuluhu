package com.example.limechain.repository;

import com.example.limechain.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository  extends JpaRepository<Transaction, Long> {
    Transaction findByHash(String hash);

    @Query("SELECT t FROM Transaction t  JOIN t.users as u WHERE :user = u")
    List<Transaction> findByUser(@Param("user") String user);
}
