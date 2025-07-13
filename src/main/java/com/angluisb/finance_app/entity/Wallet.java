package com.angluisb.finance_app.entity;

import com.angluisb.finance_app.entity.Enum.CurrencyType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "wallet_seq", sequenceName = "wallet_seq", allocationSize = 1)
@Table(name = "wallets")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wallet_seq")
    private  Long id;

    @Size(min = 3, max = 50)
    @Column(nullable = false)
    private  String name;

    @PositiveOrZero
    @Column(nullable = false)
    private Double balance;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CurrencyType currency;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, updatable = false)
    private LocalDate createdAt;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<Goal> goals = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDate.now();
    }

}
