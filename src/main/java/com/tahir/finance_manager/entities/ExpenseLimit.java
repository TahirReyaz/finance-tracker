package com.tahir.finance_manager.entities;

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
@Table(name = "expense_limits")
public class ExpenseLimit {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;
  @Column(nullable = false)
  private Timestamp time;
  @ManyToOne
  @JoinColumn(name = "user", nullable = false)
  private User user;
  @ManyToOne
  @JoinColumn(name = "expense_type", nullable = false)
  private ExpenseType expense_type;
  @Column(nullable = false)
  private Double amount;

  @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()", updatable = false, insertable = false)
  private Timestamp created_at;
  @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()", insertable = false)
  private Timestamp updated_at;

  @PreUpdate
  protected void onUpdate() {
    this.updated_at = new Timestamp(System.currentTimeMillis());
  }
}