package com.example.limechain.service;

import com.example.limechain.model.Transaction;
import com.example.limechain.service.api.Web3Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Web3jService implements Web3Service {
    @Value("${alchemy.ethereum.free.node.url}")
    private String nodeUrl;

    @Override
    public List<Transaction> getTransactions(List<String> transactionHashes, String username) {
        Web3j web3j = Web3j.build(new HttpService(nodeUrl)); //TODO: to research how this can be initialized once
        return transactionHashes.stream()
                .map(element -> {
                    org.web3j.protocol.core.methods.response.Transaction transactionEth = null;
                    try {
                        transactionEth = web3j.ethGetTransactionByHash(element)
                                .send()
                                .getTransaction()
                                .orElseThrow();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    var transaction = new Transaction();
                    assert transactionEth != null;
                    transaction.setHash(transactionEth.getHash());
                    transaction.setBlockHash(transactionEth.getBlockHash());
                    transaction.setUsers(new ArrayList<>(Collections.singletonList(username)));
                    return transaction;
                })
                .collect(Collectors.toList());
    }
}
