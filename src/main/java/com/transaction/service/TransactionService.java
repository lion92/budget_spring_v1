package com.transaction.service;

import com.kriss.user.entity.Users;
import com.kriss.user.userRepository.UserRepository;
import com.transaction.Transaction;
import com.transaction.dto.TranscationDTO;
import com.transaction.enumTransaction.TransactionType;
import com.transaction.repository.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private UserRepository userRepository;

    public Transaction create(TranscationDTO dto, String email) {
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Transaction transaction = Transaction.builder()
                .title(dto.title())
                .amount(dto.amount())
                .type(dto.type())
                .category(dto.category())
                .date(dto.date())
                .user(user)
                .build();

        return repository.save(transaction);
    }

    public List<Transaction> getUserTransactionsByUsername(String email) {
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return repository.findByUserId(user.getId());
    }

    public Double getBalanceByUsername(String email) {
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return getBalance(user.getId());
    }

    public List<Transaction> getUserTransactions(Long userId) {
        return repository.findByUserId(userId);
    }

    public Double getBalance(Long userId) {
        List<Transaction> transactions = repository.findByUserId(userId);
        return transactions.stream()
                .mapToDouble(t -> t.getType() == TransactionType.INCOME
                        ? t.getAmount()
                        : -t.getAmount())
                .sum();
    }

    public Double getTotalIncome(Long userId) {
        return repository.findByUserIdAndType(userId, TransactionType.INCOME)
                .stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public Double getTotalExpense(Long userId) {
        return repository.findByUserIdAndType(userId, TransactionType.EXPENSE)
                .stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public void delete(Long transactionId) {
        repository.deleteById(transactionId);
    }
}
