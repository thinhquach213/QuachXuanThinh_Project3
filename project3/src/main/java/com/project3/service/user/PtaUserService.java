package com.project3.service.user;

import com.project3.entity.user.PtaUser;

public interface PtaUserService {

    PtaUser registerCustomer(String fullName, String email, String password, String phone);

    PtaUser login(String email, String password);

    PtaUser getById(Long id);
}