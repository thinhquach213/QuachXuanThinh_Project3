package k23cnt.tvcDay06Lab.service;

import k23cnt.tvcDay06Lab.entity.tvcAuthor;
import k23cnt.tvcDay06Lab.repository.TvcAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TvcAuthorService {

    @Autowired
    private TvcAuthorRepository tvcAuthorRepository;

    // Lấy toàn bộ danh sách tác giả
    public  List<tvcAuthor> getAllTvcAuthors(){
        return tvcAuthorRepository.findAll();
    }
    // lấy ra một tác giả
    public tvcAuthor getTvcAuthorById(long tvcId){
        return  tvcAuthorRepository.findById(tvcId).orElse(null);
    }

    // Cập nhât thông tin
    public tvcAuthor saveTvcAuthor(tvcAuthor tvcAuthor){
        return tvcAuthorRepository.save(tvcAuthor);
    }

    // xóa
    public  void deleteTvcAuthorById(long tvcId){
        tvcAuthorRepository.deleteById(tvcId);
    }

    public List<tvcAuthor> findTvcAuthorById(List<Long> tvcIds){
        return tvcAuthorRepository.findAllById(tvcIds);
    }
}
