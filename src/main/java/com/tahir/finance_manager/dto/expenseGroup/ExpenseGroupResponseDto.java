package com.tahir.finance_manager.dto.expenseGroup;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseGroupResponseDto {
    private Long id;
    private String name;
    private Long userId;
    private Timestamp created_at;
    private Timestamp updated_at;
}
