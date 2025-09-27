package com.tahir.finance_manager.entities;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "expense_types")
public class ExpenseType {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;
  @Column(unique = true, nullable = false)
  private String name;

  @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()", updatable = false, insertable = false)
  private Timestamp created_at;
  @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()", insertable = false)
  private Timestamp updated_at;

  @PreUpdate
  protected void onUpdate() {
    this.updated_at = new Timestamp(System.currentTimeMillis());
  }
}