package k23cnt3.qxtDay6Lab.service;

import k23cnt3.qxtDay6Lab.entity.qxtAuthor;
import k23cnt3.qxtDay6Lab.repository.qxtAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class qxtAuthorService {

    @Autowired
    private qxtAuthorRepository authorRepository;

    public List<qxtAuthor> getAllAuthors() {
        return authorRepository.findAll();
    }

    public qxtAuthor saveAuthor(qxtAuthor author) {
        return authorRepository.save(author);
    }

    public qxtAuthor getAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    public List<qxtAuthor> findAllById(List<Long> ids) {
        return authorRepository.findAllById(ids);
    }
}
