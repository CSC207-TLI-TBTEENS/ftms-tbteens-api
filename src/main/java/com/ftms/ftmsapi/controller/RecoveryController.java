package com.ftms.ftmsapi.controller;

import com.ftms.ftmsapi.model.User;
import com.ftms.ftmsapi.payload.ApiResponse;
import com.ftms.ftmsapi.repository.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/recovery")
public class RecoveryController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/password")
    ResponseEntity<?> verifyRecoverPassword(@Valid @RequestBody String jsonData) {
        System.out.println(jsonData);
        JSONObject data = new JSONObject(jsonData);
        String email = (String) data.get("email");
        for (User user: userRepository.findAll()) {
            if (user.getEmail().equals(email)) {
                return new ResponseEntity(new ApiResponse(true, "User found. Proceed below!"), HttpStatus.OK);
            }
        }
        return new ResponseEntity(new ApiResponse(false, "User not found"), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/emailboth")
    ResponseEntity<?> verifyRecoverEmailOrBoth(@Valid @RequestBody String jsonData) {
        System.out.println(jsonData);
        JSONObject data = new JSONObject(jsonData);
        String phone = (String) data.get("phone");
        String firstName = (String) data.get("firstname");
        String lastName = (String) data.get("lastname");

        for (User user: userRepository.findAll()) {
            System.out.println(phone);
            System.out.println(firstName);
            System.out.println(lastName);
            System.out.println(user.getFirstname());
            System.out.println(user.getLastname());
            System.out.println(user.getNumber());
            if (user.getNumber().equals(phone) &&
                    user.getFirstname().equals(firstName) &&
                        user.getLastname().equals(lastName)) {
                return new ResponseEntity(new ApiResponse(true, "User found. Proceed below!"), HttpStatus.OK);
            }
        }
        return new ResponseEntity(new ApiResponse(false, "User not found"), HttpStatus.NOT_FOUND);
    }
}
