package com.tahir.finance_manager.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "expenses")
public class Expense {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Timestamp time;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "expense_type_id", nullable = false)
  private ExpenseType expenseType;

  @ManyToOne
  @JoinColumn(name = "expense_group_id")
  private ExpenseGroup expense_group;

  @Column(nullable = false, precision = 12, scale = 2)
  private BigDecimal amount;
  @Column(nullable = false)
  private String item;

  @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()", updatable = false, insertable = false)
  private Timestamp created_at;
  @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()", insertable = false)
  private Timestamp updated_at;

  @PreUpdate
  protected void onUpdate() {
    this.updated_at = new Timestamp(System.currentTimeMillis());
  }
}