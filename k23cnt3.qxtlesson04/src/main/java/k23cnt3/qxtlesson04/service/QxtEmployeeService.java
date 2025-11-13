package k23cnt3.qxtlesson04.service;

import k23cnt3.qxtlesson04.dto.QxtEmployeeDTO;
import org.springframework.stereotype.Service;

@Service
public class QxtEmployeeService {
    public void saveEmployee(QxtEmployeeDTO employee) {
        System.out.println("Lưu nhân viên: " + employee.getFullName());
    }
}
