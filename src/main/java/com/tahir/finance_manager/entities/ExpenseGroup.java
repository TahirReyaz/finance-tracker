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
@Table(name = "expense_groups")
public class ExpenseGroup {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String name;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()", updatable = false, insertable = false)
  private Timestamp created_at;
  @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()", insertable = false)
  private Timestamp updated_at;

  @PreUpdate
  protected void onUpdate() {
    this.updated_at = new Timestamp(System.currentTimeMillis());
  }
}