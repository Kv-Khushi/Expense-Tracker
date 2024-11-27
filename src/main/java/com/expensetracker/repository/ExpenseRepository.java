package com.expensetracker.repository;
import com.expensetracker.entities.Expense;
import com.expensetracker.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends  JpaRepository<Expense,Long> {
  List<Expense> findByCategory(Category category);
}
