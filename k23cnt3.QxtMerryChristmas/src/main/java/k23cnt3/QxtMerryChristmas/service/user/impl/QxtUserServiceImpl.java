package k23cnt3.QxtMerryChristmas.service.user.impl;

import k23cnt3.QxtMerryChristmas.entity.user.QxtUser;
import k23cnt3.QxtMerryChristmas.entity.user.QxtUserRole;
import k23cnt3.QxtMerryChristmas.repository.user.QxtUserRepository;
import k23cnt3.QxtMerryChristmas.service.user.QxtUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QxtUserServiceImpl implements QxtUserService {

    private final QxtUserRepository userRepository;

    @Override
    public QxtUser registerCustomer(String fullName, String email, String password, String phone) {
        // check trùng email
        if (userRepository.findByEmail(email).isPresent()) {
            return null;
        }

        QxtUser u = new QxtUser();
        u.setFullName(fullName);
        u.setEmail(email);
        u.setPassword(password);  // thực tế nên mã hoá
        u.setPhone(phone);
        u.setRole(QxtUserRole.CUSTOMER);
        return userRepository.save(u);
    }

    @Override
    public QxtUser login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password).orElse(null);
    }

    @Override
    public QxtUser getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
