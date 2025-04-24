package com.example.be_MrHung.services;

import com.example.be_MrHung.eNum.ResponseData;
import com.example.be_MrHung.models.User;
import com.example.be_MrHung.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseData<User> getAllUser() {
        return new ResponseData(HttpStatus.OK, "success", userRepository.findAll());
    }

    public ResponseData<User> createUser(User user) {
        try {
            if (userRepository.findByUserEmail(user.getUser_email()).isPresent()) {
                return new ResponseData<>(HttpStatus.BAD_REQUEST, "Email đã được sử dụng", null);
            }

            if (user.getUsername() == null || user.getUsername().isEmpty()) {
                return new ResponseData<>(HttpStatus.BAD_REQUEST, "Tên người dùng không được để trống", null);
            }
            if (user.getPassword() == null || user.getPassword().isEmpty()) {
                return new ResponseData<>(HttpStatus.BAD_REQUEST, "Mật khẩu không được để trống", null);
            }
            if (user.getUser_email() == null || user.getUser_email().isEmpty()) {
                return new ResponseData<>(HttpStatus.BAD_REQUEST, "Email không được để trống", null);
            }
            if (user.getUser_phone() == null || user.getUser_phone().isEmpty()) {
                return new ResponseData<>(HttpStatus.BAD_REQUEST, "Số điện thoại không được để trống", null);
            }

            user.setPassword(passwordEncoder.encode(user.getPassword()));

            User savedUser = userRepository.save(user);

            return new ResponseData<>(HttpStatus.CREATED, "Thêm người dùng thành công", savedUser);
        } catch (Exception e) {
            return new ResponseData<>(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi khi thêm: " + e.getMessage(), null);
        }
    }

    public ResponseData<User> getUser(String user_email) {
        Optional<User> optionalUser = userRepository.findByUserEmail(user_email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return new ResponseData<>(HttpStatus.OK, "Thành công", user);
        } else {
            return new ResponseData<>(HttpStatus.NOT_FOUND, "Không tìm thấy người dùng với email: " + user_email, null);
        }
    }

    public ResponseData<User> deleUserbyID(int id) {
        try {
            User userDele = userRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy user với ID: " + id));
            userRepository.deleteById(id);
            return new ResponseData<>(HttpStatus.OK, "Xóa thành công", userDele);
        } catch (Exception e) {
            return new ResponseData<>(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi xóa: " + e.getMessage(), null);
        }
    }

    public ResponseData<User> updateDUser(int id, User user) {
        try {
            User userExisting = userRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy người dùng với ID: " + id));

            if (user.getUser_id() != id) {
                throw new IllegalArgumentException("ID trong đường dẫn không khớp với ID trong body");
            }
            userExisting.setUsername(user.getUsername());
            userExisting.setUser_fullname(user.getUser_fullname());
            userExisting.setUser_birthday(user.getUser_birthday());
            userExisting.setUser_gender(user.isUser_gender());
            userExisting.setUser_email(user.getUser_email());
            userExisting.setUser_phone(user.getUser_phone());
            userExisting.setUser_address(user.getUser_address());

            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                userExisting.setPassword(passwordEncoder.encode(user.getPassword()));
            }

            User updatedUser = userRepository.save(userExisting);

            return new ResponseData<>(HttpStatus.OK, "Cập nhật thành công", updatedUser);
        } catch (IllegalArgumentException e) {
            return new ResponseData<>(HttpStatus.NOT_FOUND, e.getMessage(), null);
        } catch (Exception e) {
            return new ResponseData<>(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi khi cập nhật: " + e.getMessage(), null);
        }
    }
    public void updateAllPasswords() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (!user.getPassword().startsWith("$2a$")) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userRepository.save(user);
            }
        }
    }
}