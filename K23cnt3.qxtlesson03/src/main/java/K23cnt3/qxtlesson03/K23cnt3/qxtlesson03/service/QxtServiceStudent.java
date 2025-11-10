package K23cnt3.qxtlesson03.K23cnt3.qxtlesson03.service;



import K23cnt3.qxtlesson03.K23cnt3.qxtlesson03.entity.QxtStudent;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * Service class: QxtServiceStudent
 * <p>Lớp dịch vụ thực hiện các chức năng thao tác với List
 * Object Student</p>
 *
 * @author Chung Trịnh
 * @version 1.0
 * @Date 10/11/2025
 */

@Service
public class QxtServiceStudent {
    private List<QxtStudent> qxtStudents;

    public QxtServiceStudent() {
        qxtStudents = List.of(
                new QxtStudent(1L, "Quach Xuan Thinh", 46, true, "Hà Nội", "0978611889", "chungtrinhj@gmail.com"),
                new QxtStudent(2L, "Trần Thị Bình", 21, false, "Hải Phòng", "0987654321", "binh.tran@example.com"),
                new QxtStudent(3L, "Lê Minh Cường", 22, true, "Đà Nẵng", "0905123456", "cuong.le@example.com"),
                new QxtStudent(4L, "Phạm Thảo Vy", 19, false, "TP. Hồ Chí Minh", "0978123456", "vy.pham@example.com"),
                new QxtStudent(5L, "Võ Đức Hoàng", 23, true, "Cần Thơ", "0932123456", "hoang.vo@example.com"),
                new QxtStudent(6L, "Hoàng Lan Chi", 20, false, "Nam Định", "0945123123", "chi.hoang@example.com"),
                new QxtStudent(7L, "Ngô Văn Tuấn", 24, true, "Thanh Hóa", "0915456789", "tuan.ngo@example.com"),
                new QxtStudent(8L, "Đặng Mai Hương", 22, false, "Nghệ An", "0967345678", "huong.dang@example.com"),
                new QxtStudent(9L, "Lý Quốc Bảo", 21, true, "Bình Dương", "0902345678", "bao.ly@example.com"),
                new QxtStudent(10L, "Đỗ Thị Hạnh", 20, false, "Vĩnh Long", "0923456789", "hanh.do@example.com")
        );
    }

    // Lấy toàn bộ danh sách sinh viên
    public List<QxtStudent> getQxtStudents() {
        return qxtStudents;
    }

    // Lấy sinh viên theo ID
    public QxtStudent getQxtStudent(Long qxtId) {
        return qxtStudents.stream()
                .filter(student -> student.getQxtId() == qxtId)
                .findFirst()
                .orElse(null);
    }
}
