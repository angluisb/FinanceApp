package com.angluisb.finance_app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "goal_seq",  sequenceName = "goal_seq", allocationSize = 1 )
@Table(name = "goals")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "goal_seq")
    private Long id;

    @Column(nullable = false)
    @Size(min = 1, max = 50)
    private String name;

    @Positive
    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private LocalDate deadline;

    @Column(nullable = true)
    @Size(min = 1, max = 100)
    private String description;

    @ManyToOne()
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @Column(nullable = false, updatable = false)
    private LocalDate createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDate.now();
    }
}
