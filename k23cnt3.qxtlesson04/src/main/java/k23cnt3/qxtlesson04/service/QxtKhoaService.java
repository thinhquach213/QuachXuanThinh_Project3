package k23cnt3.qxtlesson04.service;

import k23cnt3.qxtlesson04.entity.QxtKhoa;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class QxtKhoaService {
    private List<QxtKhoa> khoas = new ArrayList<>();

    public QxtKhoaService() {
        khoas.add(new QxtKhoa("KH01", "Công nghệ thông tin"));
        khoas.add(new QxtKhoa("KH02", "Kế toán"));
        khoas.add(new QxtKhoa("KH03", "Ngôn ngữ Anh"));
        khoas.add(new QxtKhoa("KH04", "Quản trị kinh doanh"));
        khoas.add(new QxtKhoa("KH05", "Luật học"));
    }

    public List<QxtKhoa> getAll() { return khoas; }

    public QxtKhoa getById(String id) {
        return khoas.stream().filter(k -> k.getMakh().equals(id)).findFirst().orElse(null);
    }

    public void add(QxtKhoa khoa) {
        khoas.add(khoa);
    }

    public void update(String id, QxtKhoa khoaNew) {
        QxtKhoa khoa = getById(id);
        if (khoa != null) {
            khoa.setTenkh(khoaNew.getTenkh());
        }
    }

    public void delete(String id) {
        khoas.removeIf(k -> k.getMakh().equals(id));
    }
}
