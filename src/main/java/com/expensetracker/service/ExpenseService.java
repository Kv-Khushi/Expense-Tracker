package com.expensetracker.service;

import com.expensetracker.constants.ConstantMessage;
import com.expensetracker.dto.ExpenseRequest;
import com.expensetracker.dto.ExpenseResponse;
import com.expensetracker.dtoconvertor.DtoConversion;
import com.expensetracker.entities.Expense;
import com.expensetracker.enums.Category;
import com.expensetracker.exception.ResourceNotFoundException;
import com.expensetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    //get all expenses by category
    public List<ExpenseResponse> getExpenseByCategory(Category category){
        List<Expense> expenses = expenseRepository.findByCategory(category);
        return DtoConversion.expenseListToExpenseResponseList(expenses);

    }

    //update the expenses
    public ExpenseResponse updateExpense(Long id,ExpenseRequest request){
        if(!expenseRepository.existsById(id)){
           throw new ResourceNotFoundException(ConstantMessage.NOT_FOUND_EXCEPTION);
       }
       //Fetch the existing expense
       Expense existingExpense=expenseRepository.findById(id).get();
       //Update fields
       existingExpense.setAmount(request.getAmount());
       existingExpense.setDescription(request.getDescription());
       existingExpense.setCategory(request.getCategory());
       existingExpense.setDate(request.getDate());
       //Save updated expense
        Expense updatedExpense =expenseRepository.save(existingExpense);
        //Convert to DTO
        return DtoConversion.expenseToExpenseResponse(updatedExpense);
    }
}

