package com.expensetracker.dto;

import com.expensetracker.enums.Category;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ExpenseRequest {
    private Double amount;
    private String description;
    private Category category;
    private LocalDate date;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
