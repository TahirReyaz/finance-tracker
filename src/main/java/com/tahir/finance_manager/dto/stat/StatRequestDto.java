package com.tahir.finance_manager.dto.stat;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class StatRequestDto {
//   @NotNull(message = "Time is required")
//   private Timestamp startTime;

//   @NotNull(message = "Time is required")
//   private Timestamp endTime;

    @JsonProperty("expenseTypes")
    @NotEmpty(message = "Expense Types list cannot be empty")
    private List<@NotNull(message = "Each expense type must be a number") Long> expenseTypes;


//   @JsonProperty("expense_group")
//   private List<Long> expense_group;

//   @NotNull(message = "Amount is required")
//   @Positive(message = "Amount must greater than 0")
//   private BigDecimal startAmount;

//   @NotNull(message = "Amount is required")
//   @Positive(message = "Amount must greater than 0")
//   private BigDecimal endAmount;
}
