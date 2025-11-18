package k23cnt3.qxtDay6Lab.service;

import k23cnt3.qxtDay6Lab.entity.qxtBook;
import k23cnt3.qxtDay6Lab.repository.QxtBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class QxtBookService {
    @Autowired
    private QxtBookRepository qxtBookRepository;

    public List<qxtBook> getAllQxtBooks(){
        return qxtBookRepository.findAll();}
    public qxtBook getQxtBooKById(Long qxtId){
        return qxtBookRepository.findById(qxtId).orElse(null);
    }
    // cap nhat thong tin
    public qxtBook saveQxtBooK(qxtBook qxtBook){
        return qxtBookRepository.save(qxtBook);
    }
    // xoa
    public void deleteQxtBooK(Long qxtId){
      qxtBookRepository.deleteById(qxtId);
    }
}
