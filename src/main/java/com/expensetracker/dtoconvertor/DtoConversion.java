package com.expensetracker.dtoconvertor;

import com.expensetracker.dto.ExpenseRequest;
import com.expensetracker.dto.ExpenseResponse;
import com.expensetracker.entities.Expense;

public class DtoConversion {

    //convert expenseReuest to Expense
     public static Expense expenseRequestToExpense(ExpenseRequest expenseRequest){
         Expense expense =new Expense();
         expense.setAmount(expenseRequest.getAmount());
         expense.setDescription(expenseRequest.getDescription());
         expense.setCategory(expenseRequest.getCategory());
         expense.setDate(expenseRequest.getDate());
         return expense;
     }

     public static ExpenseResponse expenseToExpenseResponse(Expense expense){
         ExpenseResponse expenseResponse= new ExpenseResponse();
         expense.setAmount(expense.getAmount());
         expense.setDescription(expense.getDescription());
         expense.setCategory(expense.getCategory());
         expense.setDate(expense.getDate());
         return expenseResponse;
     }
}
