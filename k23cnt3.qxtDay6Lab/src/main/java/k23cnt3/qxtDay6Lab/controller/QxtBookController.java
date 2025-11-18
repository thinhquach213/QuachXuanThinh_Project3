package k23cnt3.qxtDay6Lab.controller;

import k23cnt3.qxtDay6Lab.entity.qxtBook;
import k23cnt3.qxtDay6Lab.service.QxtBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "qxtBooks/create-book";
    }
}