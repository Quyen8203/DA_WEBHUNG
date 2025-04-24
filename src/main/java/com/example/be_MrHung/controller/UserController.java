package com.example.be_MrHung.controller;

import com.example.be_MrHung.dto.LoginRequest;
import com.example.be_MrHung.eNum.ResponseData;
import com.example.be_MrHung.models.User;
import com.example.be_MrHung.security.CustomUserDetailsService;
import com.example.be_MrHung.security.JwtTokenUtil;
import com.example.be_MrHung.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {})
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @PostMapping("/update-passwords")
    public ResponseEntity<?> updateAllPasswords() {
        userService.updateAllPasswords();
        return ResponseEntity.ok(new ResponseData<>(HttpStatus.OK, "Cập nhật mật khẩu thành công", null));
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user) {
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
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody User user) {
        ResponseData<User> response = userService.updateDUser(id, user);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Kiểm tra email và password không null
            if (loginRequest.getEmail() == null || loginRequest.getPassword() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseData<>(HttpStatus.BAD_REQUEST, "Email và mật khẩu không được để trống", null));
            }

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
            final String token = jwtTokenUtil.generateToken(userDetails);


            ResponseData<User> userResponse = userService.getUser(loginRequest.getEmail());
            if (userResponse.getStatus() != HttpStatus.OK || userResponse.getData() == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseData<>(HttpStatus.NOT_FOUND, "Không tìm thấy người dùng với email: " + loginRequest.getEmail(), null));
            }

            return ResponseEntity.ok(new LoginResponse(token, userResponse.getData()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseData<>(HttpStatus.UNAUTHORIZED, "Email hoặc mật khẩu không đúng", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseData<>(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi server: " + e.getMessage(), null));
        }
    }
}

class LoginResponse {
    private final String token;
    private final User user;

    public LoginResponse(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }
}