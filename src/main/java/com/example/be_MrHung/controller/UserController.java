package com.example.be_MrHung.controller;


import com.example.be_MrHung.eNum.ResponseData;



import com.example.be_MrHung.models.User;
import com.example.be_MrHung.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {})
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/list")
    public ResponseEntity<?> getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user){
        ResponseData<User> response = userService.createUser(user);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/by-email/{email}")
    public ResponseEntity<ResponseData<User>> getUser(@PathVariable String email) {
        ResponseData<User> response = userService.getUser(email);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<ResponseData<User>> deleteUser(@PathVariable int id) {
        ResponseData<User> response = userService.deleUserbyID(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable int id,
            @RequestBody User user ) {

        ResponseData<User> response = userService.updateDUser(id, user);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
