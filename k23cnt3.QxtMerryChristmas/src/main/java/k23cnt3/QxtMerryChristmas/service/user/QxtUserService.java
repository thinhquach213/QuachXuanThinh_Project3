package k23cnt3.QxtMerryChristmas.service.user;

import k23cnt3.QxtMerryChristmas.entity.user.QxtUser;

public interface QxtUserService {

    QxtUser registerCustomer(String fullName, String email, String password, String phone);

    QxtUser login(String email, String password);

    QxtUser getById(Long id);
}
