package com.transaction.dto;

import com.transaction.enumTransaction.TransactionType;

import java.time.LocalDate;

public record TranscationDTO(
        String title,
        Double amount,
        TransactionType type,
        String category,
        LocalDate date
) {}
