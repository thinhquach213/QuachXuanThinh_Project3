package k23cnt3.qxtWebbansach.controller.admin;

import k23cnt3.qxtWebbansach.model.Book;
import k23cnt3.qxtWebbansach.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin/books")
public class AdminBookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "admin/book-list";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        return "admin/book-form";
    }

    @PostMapping("/update")
    public String updateBook(@ModelAttribute Book book, @RequestParam("image") MultipartFile image) {
        bookService.saveOrUpdate(book, image);
        return "redirect:/admin/books";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book, @RequestParam("image") MultipartFile image) {
        bookService.saveOrUpdate(book, image);
        return "redirect:/admin/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/admin/books";
    }
}
