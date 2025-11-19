package k23cnt3.qxtDay6Lab.service;


import k23cnt3.qxtDay6Lab.entity.qxtAuthor;
import k23cnt3.qxtDay6Lab.repository.QxtAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
        @Service
public class QxtAuthorService {
            @Autowired
            private QxtAuthorRepository qxtAuthorRepository;
            public  List<qxtAuthor> getAllQxtAuthor(){
                return qxtAuthorRepository.findAll();
            }
            public qxtAuthor getQxtAuthorById(Long qxtId) {
                return qxtAuthorRepository.findById(qxtId).orElse(null);
            }
            public qxtAuthor saveQxtAuthor(qxtAuthor qxtAuthor) {
                return qxtAuthorRepository.save(qxtAuthor);
            }

            public void deleteQxtAuthorById(Long qxtId) {
                qxtAuthorRepository.deleteById(qxtId);
            }

            public List<qxtAuthor> findQxtAuthorByIds(List<Long> qxtIds) {
                return qxtAuthorRepository.findAllById(qxtIds);
            }

            public List<qxtAuthor> getActiveQxtAuthors() {
                return qxtAuthorRepository.findByQxtActiveTrue();
            }

            public boolean existsByQxtCode(String qxtCode) {
                return qxtAuthorRepository.existsByQxtCode((qxtCode));
            }

}
