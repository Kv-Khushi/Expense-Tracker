package com.expensetracker.service;
import com.expensetracker.constants.ConstantMessage;
import com.expensetracker.dto.CommonResponse;
import com.expensetracker.dto.LoginRequest;
import com.expensetracker.dto.UserRequest;
import com.expensetracker.dto.UserResponse;
import com.expensetracker.dtoconvertor.DtoConversion;
import com.expensetracker.entities.User;
import com.expensetracker.exception.AlreadyExistsException;
import com.expensetracker.exception.InvalidCredentialException;
import com.expensetracker.passwordencoding.PasswordEncodingAndDecoding;
import com.expensetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


     PasswordEncodingAndDecoding passwordEncodingAndDecoding;


    public CommonResponse addUser(UserRequest userRequest){
        User user =DtoConversion.convertUserRequestToUser(userRequest);
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new AlreadyExistsException(ConstantMessage.EMAIL_ALREADY_EXISTS);
        }
        passwordEncodingAndDecoding= new PasswordEncodingAndDecoding();
        user.setPassword(passwordEncodingAndDecoding.encodePassword(user.getPassword()));
        User savedUser=userRepository.save(user);
        return new CommonResponse(ConstantMessage.USER_ADDED_SUCCESSFULLY);
    }

    public UserResponse loginUser(LoginRequest loginRequest){
       User user =userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(()->new InvalidCredentialException(ConstantMessage.UNAUTHORIZED_USER));
       passwordEncodingAndDecoding =new PasswordEncodingAndDecoding();
       if(!passwordEncodingAndDecoding.decodePassword(user.getPassword()).equals(loginRequest.getPassword())){
           throw new InvalidCredentialException(ConstantMessage.UNAUTHORIZED_USER);
       }
       return DtoConversion.convertUserToUserResponse(user);
      }

}
