package k23cnt3.qxtWebbansach.service;

import k23cnt3.qxtWebbansach.dto.UserDTO;
import k23cnt3.qxtWebbansach.model.User;
import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    User createUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    User findByEmail(String email);
}
