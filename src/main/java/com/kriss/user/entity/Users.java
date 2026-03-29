package com.kriss.user.entity;

import com.transaction.Transaction;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;

    public Users(Long id, String email, String password, List<Transaction> transactions) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.transactions = transactions;
    }

    public Users(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public Users(Long id, String email, List<Transaction> transactions) {
        this.id = id;
        this.email = email;
        this.transactions = transactions;
    }
}
