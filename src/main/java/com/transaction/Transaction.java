package com.transaction;

import com.kriss.user.entity.Users;
import com.transaction.enumTransaction.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type; // INCOME ou EXPENSE

    private LocalDate date;

    private String category;

    @ManyToOne
    private Users user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(amount, that.amount) && type == that.type && Objects.equals(date, that.date) && Objects.equals(category, that.category) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, amount, type, date, category, user);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", amount=" + amount +
                ", type=" + type +
                ", date=" + date +
                ", category='" + category + '\'' +
                ", user=" + user +
                '}';
    }
}