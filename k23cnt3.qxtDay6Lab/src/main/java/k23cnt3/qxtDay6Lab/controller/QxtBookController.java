package k23cnt3.qxtDay6Lab.controller;

import k23cnt3.qxtDay6Lab.entity.qxtAuthor;
import k23cnt3.qxtDay6Lab.entity.qxtBook;
import k23cnt3.qxtDay6Lab.service.QxtAuthorService;
import k23cnt3.qxtDay6Lab.service.QxtBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/qxtbooks")
public class QxtBookController {

    @Autowired
    private QxtBookService qxtBookService;

    @Autowired
    private QxtAuthorService qxtAuthorService;

    // Get all books
    @GetMapping
    public String getQxtBooks(Model model) {
        model.addAttribute("qxtBooks", qxtBookService.getAllQxtBooks());
        return "qxtBooks/qxt-books-list";
    }

    // Show create form
    @GetMapping("/new")
    public String showCreateFormQxtBook(Model model) {
        model.addAttribute("qxtBook", new qxtBook());
        model.addAttribute("qxtAuthors", qxtAuthorService.getAllQxtAuthor());
        return "qxtBooks/qxt-book-form";
    }

    // Create new book
    @PostMapping("/new")
    public String createQxtBook(@ModelAttribute qxtBook qxtBook,
                                @RequestParam List<Long> qxtAuthorIds,
                                @RequestParam(value = "imageBook", required = false) MultipartFile imageFile) {

        // Lấy danh sách tác giả và set cho sách
        List<qxtAuthor> authors = new ArrayList<>(qxtAuthorService.findQxtAuthorByIds(qxtAuthorIds));
        qxtBook.setAuthors(authors);

        // Lưu sách vào DB
        qxtBookService.saveQxtBooK(qxtBook);

        return "redirect:/qxtbooks";
    }
}
