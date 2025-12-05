package k23cnt3.qxtWebbansach.controller.user;

import k23cnt3.qxtWebbansach.model.Book;
import k23cnt3.qxtWebbansach.model.Category;
import k23cnt3.qxtWebbansach.service.BookService;
import k23cnt3.qxtWebbansach.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user/books")
public class BookController {

    private final BookService bookService;
    private final CategoryService categoryService;

    public BookController(BookService bookService, CategoryService categoryService){
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    // Danh sách sách
    @GetMapping
    public String listBooks(@RequestParam(value="categoryId", required=false) Long categoryId,
                            @RequestParam(value="keyword", required=false) String keyword,
                            Model model){
        List<Book> books;

        if(categoryId != null){
            books = bookService.getBooksByCategory(categoryId);
        } else if(keyword != null && !keyword.isEmpty()){
            books = bookService.searchBooks(keyword);
        } else {
            books = bookService.getAllBooks();
        }

        List<Category> categories = categoryService.getAllCategories();

        model.addAttribute("books", books);
        model.addAttribute("categories", categories);
        model.addAttribute("selectedCategoryId", categoryId);
        model.addAttribute("keyword", keyword);

        return "user/book-list";
    }

    // Chi tiết sách
    @GetMapping("/{id}")
    public String bookDetail(@PathVariable Long id, Model model){
        Book book = bookService.getBookById(id);
        if(book == null){
            return "redirect:/user/books";
        }
        model.addAttribute("book", book);
        return "user/book-detail";
    }
}
