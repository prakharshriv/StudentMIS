package com.prakhar.ecom.controller;

import com.prakhar.ecom.exceptions.UserNotFound;
import com.prakhar.ecom.helperClasses.Response;
import com.prakhar.ecom.models.User;
import com.prakhar.ecom.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/user")
public class UserController {
    @Autowired
    UserServices userServices;

    //add user
    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody User user) {
        Response res;
        HttpStatus status=HttpStatus.OK;
        User createdUser=userServices.addUser(user);
        if(createdUser==null) {
            res = new Response(false,null,"user creation failed please try again" );
            status=HttpStatus.BAD_REQUEST;
        }
        res = new Response(true, createdUser, "success");
        return new ResponseEntity(res,status);
    }
    //list users
    @GetMapping("/all")
    public ResponseEntity getAllUsers() {
        Response res;
        HttpStatus status=HttpStatus.OK;
        List<User> userList=userServices.getAllUsers();
        if(userList==null) {
            res = new Response(false,null,"Some problem occurred. Please check back later" );
            status=HttpStatus.BAD_REQUEST;
        }
        res = new Response(true, userList, "success");
        return new ResponseEntity(res,status);
    }
    //get user by id
    @GetMapping("/get/id")
    public ResponseEntity getUserById(@RequestParam("id")String id) {
        Response res;
        HttpStatus status=HttpStatus.OK;
        User foundUser=userServices.getUserById(id);
        if(foundUser==null) {
            res = new Response(false,null,"user not found" );
            status=HttpStatus.NOT_FOUND;
        }
        res = new Response(true, foundUser, "success");
        return new ResponseEntity(res,status);
    }
    // update user
    @PostMapping("/update")
    public ResponseEntity updateUser(@RequestBody User user) {
        Response res;
        HttpStatus status=HttpStatus.OK;
        User updatedUser =null;
        try {
            updatedUser=userServices.updateUserById(user);
            res = new Response(true, updatedUser, "success");

            if(updatedUser==null) {
                res = new Response(false,null,"user update failed. Please make sure the data entered is correct" );
                status=HttpStatus.BAD_REQUEST;
            }


        } catch (UserNotFound userNotFound) {
            res=new Response(false,null,"user does not exist");
            status=HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity(res,status);
    }
    // delete user
    @GetMapping("/delete")
    public ResponseEntity deleteUserById(@RequestParam("id")String id) {
        Response res;
        HttpStatus status=HttpStatus.OK;
        User updatedUser =null;
        try {
            boolean isDeleted=userServices.deleteUserById(id);
            if(isDeleted)
            res = new Response(true, updatedUser, "success");
            else  {
                res = new Response(false,null,"user could not be deleted. Please make sure the data entered is correct" );
                status=HttpStatus.BAD_REQUEST;
            }


        } catch (UserNotFound userNotFound) {
            res=new Response(false,null,"user does not exist");
            status=HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity(res,status);
    }

}
