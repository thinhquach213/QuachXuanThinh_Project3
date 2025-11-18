package k23cnt.tvcDay06Lab.service;

import k23cnt.tvcDay06Lab.entity.tvcBook;
import k23cnt.tvcDay06Lab.repository.TvcBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TvcBookService {
    @Autowired
    private TvcBookRepository tvcBookRepository;

    // Lấy toàn bộ danh sách các book
    public List<tvcBook> getAllTvcBooks(){
        return tvcBookRepository.findAll();
    }

    // Lấy 1 cuốn sách theo id
    public  tvcBook getTvcBookById(Long tvcId){
        return tvcBookRepository.findById(tvcId).orElse(null);
    }

    // Cập nhật thông tin sách
    public  tvcBook saveTvcBook(tvcBook tvcBook){
        return tvcBookRepository.save(tvcBook);
    }

    // xóa
    public  void  deleteTvcBook(Long tvcId){
        tvcBookRepository.deleteById(tvcId);
    }
}
