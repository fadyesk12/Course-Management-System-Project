//package com.example.demo.Services;
//
//import com.example.demo.Model.User;
//import com.example.demo.Repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import java.util.List;
//
//@Service
//public class UserService {
//    private final UserRepository userRepository;
//    @Autowired
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    public User getUserByEmail(String email) {
//        return userRepository.findByEmail(email)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//    }
//
//    public boolean checkIfEmailExists(String email) {
//        return userRepository.existsByEmail(email);
//    }
//
//    public User createUser(User user) {
//        return userRepository.save(user);
//    }
//
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//    public User getUserById(Long id) {
//        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
//    }
//}
