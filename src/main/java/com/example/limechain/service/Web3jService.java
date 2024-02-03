package com.example.limechain.service;

import com.example.limechain.model.Transaction;
import com.example.limechain.service.api.Web3Service;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Web3jService implements Web3Service {

    private final Web3j web3j = Web3j.build(new HttpService("https://eth-mainnet.g.alchemy.com/v2/EyLptHT_VWSWnTqALPkBjFvseUjfQSZB"));

    @Override
    public List<Transaction> getTransactions(List<String> transactionHashes) {
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
                    return transaction;
                })
                .collect(Collectors.toList());
    }
}
