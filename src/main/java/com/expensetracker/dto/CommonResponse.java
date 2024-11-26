package com.expensetracker.dto;

public class CommonResponse {

    private String message;

    public CommonResponse(){

  }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CommonResponse(String message){

     this.message=message;
  }
}
