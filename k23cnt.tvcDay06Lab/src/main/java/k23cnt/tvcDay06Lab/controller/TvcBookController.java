package k23cnt.tvcDay06Lab.controller;

import k23cnt.tvcDay06Lab.entity.tvcAuthor;
import k23cnt.tvcDay06Lab.entity.tvcBook;
import k23cnt.tvcDay06Lab.service.TvcAuthorService;
import k23cnt.tvcDay06Lab.service.TvcBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tvcbooks")
public class TvcBookController {
    @Autowired
    private TvcBookService tvcBookService;
    @Autowired
    private TvcAuthorService tvcAuthorService;

    // Get all
    @GetMapping
    public String getTvcBooks(Model model) {
        model.addAttribute("tvcBooks", tvcBookService.getAllTvcBooks());
        return "tvcbooks/tvc-book-list";
    }

    // Thêm mới sách
    @GetMapping("/new")
    public String showCreateFormTvcBook(Model model) {
        model.addAttribute("tvcBook", new tvcBook());
        model.addAttribute("tvcAuthors", tvcAuthorService.getAllTvcAuthors());

        return "tvcbooks/tvc-book-form";
    }
    @PostMapping("/new")
    public String createTvcBook(@ModelAttribute tvcBook tvcBook ,
                                @RequestParam List<Long> tvcAuthorIds,
                                @RequestParam("imageBook") MultipartFile imageFile) {
        // luu vao bang tvc_book_author
        List<tvcAuthor> authors =  new ArrayList<>(tvcAuthorService.findTvcAuthorById(tvcAuthorIds));
        tvcBook.setTvcAuthor(authors);
        tvcBookService.saveTvcBook(tvcBook);

        return "redirect:/tvcbooks";
    }
}
