package com.expensetracker.service;

import com.expensetracker.dto.ExpenseRequest;
import com.expensetracker.dto.ExpenseResponse;
import com.expensetracker.dtoconvertor.DtoConversion;
import com.expensetracker.entities.Expense;
import com.expensetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    //add expenses
    public ExpenseResponse addExpense(ExpenseRequest expenseRequest) {
        //Convert ExpenseRequest to expense
        Expense expense = DtoConversion.expenseRequestToExpense(expenseRequest);

        //Save the expense entity to databse
        Expense savedExpence = expenseRepository.save(expense);

        return DtoConversion.expenseToExpenseResponse(savedExpence);
    }
}

