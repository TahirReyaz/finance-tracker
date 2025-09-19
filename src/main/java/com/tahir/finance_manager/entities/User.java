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
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;
  private String username;
  private String email;
  private String password;
  @Column(name = "verified", insertable = false)
  private Boolean verfied;
  @Column(name = "auth_level", insertable = false)
  private Integer auth_level;
  @Column(name = "created_at", insertable = false, updatable = false)
  private Timestamp created_at;
  @Column(name = "updated_at", insertable = false)
  private Timestamp updated_at;

  @PreUpdate
  protected void onUpdate() {
    this.updated_at = new Timestamp(System.currentTimeMillis());
  }
}