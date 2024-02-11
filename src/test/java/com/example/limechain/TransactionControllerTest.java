package com.example.limechain;

import com.example.limechain.dto.TransactionResponse;
import com.example.limechain.service.api.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

    @MockBean
    TransactionService transactionService;

    @Autowired
    private WebTestClient client;


    @Test
    @WithMockUser(username = "admin")
    public void getTransactions() throws IOException {
        given(transactionService.getTransactions(any(), any())).willReturn(Set.of(transaction));

        client
                .get()
                .uri("/v1/api/lime/eth?transactionHashes=0x2ebec86abd6f0d7225a47a86d7acd84efc8b5f568e453dd0fd4142c26b7f5d1e")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(TransactionResponse.class)
            .isEqualTo(List.of(transaction));

        verify(
                transactionService,
                times(1)
        )
                .getTransactions(
                        Collections.singletonList("0x2ebec86abd6f0d7225a47a86d7acd84efc8b5f568e453dd0fd4142c26b7f5d1e"),
                        "admin"
                );
    }

    @Test
    @WithMockUser(username = "admin")
    public void getMy() {
        given(transactionService.getMy("admin")).willReturn(List.of(transaction));

        client
                .get()
                .uri("/v1/api/lime/my")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(TransactionResponse.class)
                .isEqualTo(List.of(transaction));

        verify(
                transactionService,
                times(1)
        )
                .getMy("admin");
    }

    @Test
    @WithMockUser(username = "admin")
    public void getAll() {
        given(transactionService.getAll()).willReturn(List.of(transaction));

        client
                .get()
                .uri("/v1/api/lime/all")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(TransactionResponse.class)
                .isEqualTo(List.of(transaction));

        verify(
                transactionService,
                times(1)
        )
        .getAll();
    }

    private final TransactionResponse transaction = new TransactionResponse(
            "hash",
            "blockHash",
            0,
            BigInteger.valueOf(2345),
            "fromAddress",
            "toAddress",
            "contractAddress",
            0,
            "input",
            "value"
    );
}
