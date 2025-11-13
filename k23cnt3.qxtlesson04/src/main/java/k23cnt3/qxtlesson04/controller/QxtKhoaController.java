package k23cnt3.qxtlesson04.controller;

import k23cnt3.qxtlesson04.entity.QxtKhoa;
import k23cnt3.qxtlesson04.service.QxtKhoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/khoa")
public class QxtKhoaController {
    @Autowired
    private QxtKhoaService khoaService;

    @GetMapping
    public List<QxtKhoa> getAll() {
        return khoaService.getAll();
    }

    @GetMapping("/{id}")
    public QxtKhoa getById(@PathVariable String id) {
        return khoaService.getById(id);
    }

    @PostMapping
    public void create(@RequestBody QxtKhoa khoa) {
        khoaService.add(khoa);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable String id, @RequestBody QxtKhoa khoa) {
        khoaService.update(id, khoa);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        khoaService.delete(id);
    }
}
