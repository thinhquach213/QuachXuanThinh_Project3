package K23cnt3.qxtlesson03.K23cnt3.qxtlesson03.cotroller;

import K23cnt3.qxtlesson03.K23cnt3.qxtlesson03.entity.QxtStudent;
import K23cnt3.qxtlesson03.K23cnt3.qxtlesson03.service.QxtServiceStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class QxtStudentController {

    @Autowired
    public QxtServiceStudent qxtServiceStudent;

    @GetMapping("/student-list")
    public List<QxtStudent> getAllStudents() {
        return qxtServiceStudent.getQxtStudents();
    }

    @GetMapping("/student/{qxtId}")
    public QxtStudent getQxtStudentById(@PathVariable String qxtId) {
        Long param = Long.parseLong(qxtId);
        return qxtServiceStudent.getQxtStudent(param);
    }
}
