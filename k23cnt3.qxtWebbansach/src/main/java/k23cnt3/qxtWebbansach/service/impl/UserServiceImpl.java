package k23cnt3.qxtWebbansach.service.impl;

import k23cnt3.qxtWebbansach.dto.UserDTO;
import k23cnt3.qxtWebbansach.model.User;
import k23cnt3.qxtWebbansach.repository.UserRepository;
import k23cnt3.qxtWebbansach.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(user -> {
            UserDTO dto = new UserDTO();
            dto.setId(user.getId());
            dto.setUsername(user.getUsername());
            dto.setEmail(user.getEmail());
            dto.setPhone(user.getPhone());
            dto.setRole(user.getRole().name());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id).map(user -> {
            UserDTO dto = new UserDTO();
            dto.setId(user.getId());
            dto.setUsername(user.getUsername());
            dto.setEmail(user.getEmail());
            dto.setPhone(user.getPhone());
            dto.setRole(user.getRole().name());
            return dto;
        }).orElse(null);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        return userRepository.findById(id).map(existing -> {
            existing.setUsername(user.getUsername());
            existing.setEmail(user.getEmail());
            existing.setPhone(user.getPhone());
            existing.setRole(user.getRole());
            return userRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
