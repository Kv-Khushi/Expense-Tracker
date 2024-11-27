package com.expensetracker.dtoconvertor;

import com.expensetracker.dto.ExpenseRequest;
import com.expensetracker.dto.ExpenseResponse;
import com.expensetracker.entities.Expense;

import java.util.ArrayList;
import java.util.List;

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
         expenseResponse.setId(expense.getId());
         expenseResponse.setAmount(expense.getAmount());
         expenseResponse.setDescription(expense.getDescription());
         expenseResponse.setCategory(expense.getCategory());
         expenseResponse.setDate(expense.getDate());
         return expenseResponse;
     }

     public static List<ExpenseResponse> expenseListToExpenseResponseList(List<Expense> expenses){
         List<ExpenseResponse> responseList = new ArrayList<>();
         for(Expense expense:expenses){
             ExpenseResponse response = expenseToExpenseResponse(expense);
             responseList.add(response);
         }
         return responseList;
     }
}
