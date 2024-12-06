package com.expensetracker.controller;


import com.expensetracker.constants.ConstantMessage;
import com.expensetracker.dto.CommonResponse;
import com.expensetracker.dto.ExpenseRequest;
import com.expensetracker.dto.ExpenseResponse;
import com.expensetracker.enums.Category;
import com.expensetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    //Api to add expense
    @PostMapping
    public final ResponseEntity<CommonResponse> addExpense(@RequestBody ExpenseRequest expenseRequest){
        ExpenseResponse expenseResponse= expenseService.addExpense(expenseRequest);
        CommonResponse commonResponse= new CommonResponse(ConstantMessage.EXPENSE_ADDED_SUCCESSFULLY);
         return new ResponseEntity<>(commonResponse, HttpStatus.CREATED);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ExpenseResponse>> getExpenseByCategory (@PathVariable Category category){
        List<ExpenseResponse> expenses =expenseService.getExpenseByCategory(category);
        return ResponseEntity.ok(expenses);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ExpenseResponse> updateExpense(@PathVariable Long id ,@RequestBody ExpenseRequest request){
        ExpenseResponse updatedExpense= expenseService.updateExpense(id, request);
        return ResponseEntity.ok(updatedExpense);
    }

    //total expense 

}

