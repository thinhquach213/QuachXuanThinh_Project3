package K23cnt3.qxtlesson03.K23cnt3.qxtlesson03.cotroller;

import K23cnt3.qxtlesson03.K23cnt3.qxtlesson03.entity.Khoa;
import K23cnt3.qxtlesson03.K23cnt3.qxtlesson03.service.KhoaService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/khoa")
public class KhoaController {
    private final KhoaService khoaService;

    public KhoaController(KhoaService khoaService) {
        this.khoaService = khoaService;
    }

    @GetMapping
    public List<Khoa> getAll() {
        return khoaService.getAll();
    }

    @GetMapping("/{makh}")
    public Khoa getById(@PathVariable String makh) {
        return khoaService.getById(makh);
    }

    @PostMapping
    public void add(@RequestBody Khoa khoa) {
        khoaService.add(khoa);
    }

    @PutMapping("/{makh}")
    public void update(@PathVariable String makh, @RequestBody Khoa newKhoa) {
        khoaService.update(makh, newKhoa);
    }

    @DeleteMapping("/{makh}")
    public void delete(@PathVariable String makh) {
        khoaService.delete(makh);
    }
}
