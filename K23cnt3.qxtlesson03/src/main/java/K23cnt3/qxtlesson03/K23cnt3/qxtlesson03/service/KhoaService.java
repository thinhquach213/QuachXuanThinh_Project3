package K23cnt3.qxtlesson03.K23cnt3.qxtlesson03.service;

import K23cnt3.qxtlesson03.K23cnt3.qxtlesson03.entity.Khoa;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class KhoaService {
    private List<Khoa> khoas = new ArrayList<>();

    public KhoaService() {
        khoas.add(new Khoa("CNTT", "Công nghệ thông tin"));
        khoas.add(new Khoa("QTKD", "Quản trị kinh doanh"));
        khoas.add(new Khoa("KT", "Kế toán"));
        khoas.add(new Khoa("NN", "Ngoại ngữ"));
        khoas.add(new Khoa("DL", "Du lịch"));
    }

    public List<Khoa> getAll() {
        return khoas;
    }

    public Khoa getById(String makh) {
        return khoas.stream().filter(k -> k.getMakh().equals(makh)).findFirst().orElse(null);
    }

    public void add(Khoa khoa) {
        khoas.add(khoa);
    }

    public void update(String makh, Khoa newKhoa) {
        for (int i = 0; i < khoas.size(); i++) {
            if (khoas.get(i).getMakh().equals(makh)) {
                khoas.set(i, newKhoa);
                return;
            }
        }
    }

    public void delete(String makh) {
        khoas.removeIf(k -> k.getMakh().equals(makh));
    }
}
