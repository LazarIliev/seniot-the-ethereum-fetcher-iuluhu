package com.example.limechain.controller;

import com.example.limechain.model.Transaction;
import com.example.limechain.service.api.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RequestMapping("/v1/api/lime")
@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("eth")
    public List<Transaction> getTransactions(@RequestParam(name = "transactionHashes") List<String> transactionHashes) throws IOException {
        return transactionService.getTransactions(transactionHashes);
    }

    @GetMapping("all")
    public List<Transaction> getAll() {
        return transactionService.getAll();
    }
}