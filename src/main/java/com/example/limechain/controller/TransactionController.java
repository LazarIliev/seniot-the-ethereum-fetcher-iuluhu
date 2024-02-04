package com.example.limechain.controller;

import com.example.limechain.model.Transaction;
import com.example.limechain.service.api.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/v1/api/lime")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping("eth")
    public Set<Transaction> getTransactions(@RequestParam(name = "transactionHashes") List<String> transactionHashes, Principal principal) throws IOException {
        return transactionService.getTransactions(transactionHashes, principal.getName());
    }

    @GetMapping("my")
    public List<Transaction> getMyTransactions(Principal principal) {
        final String username = principal.getName();
        return transactionService.getMy(username);
    }

    @GetMapping("all")
    public List<Transaction> getAll() {
        return transactionService.getAll();
    }
}
