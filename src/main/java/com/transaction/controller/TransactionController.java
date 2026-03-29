package com.transaction.controller;

import com.transaction.Transaction;
import com.transaction.dto.TranscationDTO;
import com.transaction.service.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @PostMapping
    public Transaction create(@RequestBody TranscationDTO dto,
                              @AuthenticationPrincipal UserDetails userDetails) {
        return service.create(dto, userDetails.getUsername());
    }

    @GetMapping
    public List<Transaction> getUserTransactions(@AuthenticationPrincipal UserDetails userDetails) {
        return service.getUserTransactionsByUsername(userDetails.getUsername());
    }

    @GetMapping("/balance")
    public Double getBalance(@AuthenticationPrincipal UserDetails userDetails) {
        return service.getBalanceByUsername(userDetails.getUsername());
    }
}
