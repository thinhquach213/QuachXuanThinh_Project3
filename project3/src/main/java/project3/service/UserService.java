package project3.service;

import k23cnt3.qxtWebbansach.model.User;

import java.util.List;

public interface UserService {
    User login(String email, String password);
    void register(String fullName, String email, String password);
    boolean emailExists(String email);
    void updateProfile(Long userId, String fullName, String phone);
    User getUserById(Long id);
    List<User> getAllUsers();
    void deleteUser(Long id);
}
