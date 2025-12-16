package com.project3.service.user.impl;

import com.project3.entity.user.PtaUser;
import com.project3.entity.user.PtaUserRole;
import com.project3.repository.user.PtaUserRepository;
import com.project3.service.user.PtaUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PtaUserServiceImpl implements PtaUserService {

    private final PtaUserRepository userRepository;

    @Override
    public PtaUser registerCustomer(String fullName, String email, String password, String phone) {
        // check trùng email
        if (userRepository.findByEmail(email).isPresent()) {
            return null;
        }

        PtaUser u = new PtaUser();
        u.setFullName(fullName);
        u.setEmail(email);
        u.setPassword(password);  // thực tế nên mã hoá
        u.setPhone(phone);
        u.setRole(PtaUserRole.CUSTOMER);
        return userRepository.save(u);
    }

    @Override
    public PtaUser login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password).orElse(null);
    }

    @Override
    public PtaUser getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}