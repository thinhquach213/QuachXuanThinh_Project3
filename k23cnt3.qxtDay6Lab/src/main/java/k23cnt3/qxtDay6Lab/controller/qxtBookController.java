package k23cnt3.qxtDay6Lab.controller;

import k23cnt3.qxtDay6Lab.entity.qxtAuthor;
import k23cnt3.qxtDay6Lab.entity.qxtBook;
import k23cnt3.qxtDay6Lab.service.qxtAuthorService;
import k23cnt3.qxtDay6Lab.service.qxtBookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/books")
public class qxtBookController {

    @Autowired
    private qxtBookService bookService;

    @Autowired
    private qxtAuthorService authorService;

    private static final String UPLOAD_DIR = "src/main/resources/static/";
    private static final String UPLOAD_PathFile = "images/products/";

    // Hiển thị toàn bộ sách
    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books/book-list";
    }

    // Thêm mới sách
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new qxtBook());
        model.addAttribute("authors", authorService.getAllAuthors());
        return "books/book-form";
    }

    @PostMapping("/new")
    public String saveBook(@ModelAttribute qxtBook book,
                           @RequestParam List<Long> authorIds,
                           @RequestParam("imageBook") MultipartFile imageFile) {

        if (!imageFile.isEmpty()) {
            try {
                // Tạo thư mục upload nếu chưa có
                Path uploadPath = Paths.get(UPLOAD_DIR + UPLOAD_PathFile);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // Xử lý tên file
                String originalFilename = StringUtils.cleanPath(imageFile.getOriginalFilename());
                String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));

                // Đặt tên file mới theo code của book
                String newFileName = book.getCode() + fileExtension;
                Path filePath = uploadPath.resolve(newFileName);

                // Copy file lên server
                Files.copy(imageFile.getInputStream(), filePath);

                // Lưu đường dẫn ảnh
                book.setImgUrl("/" + UPLOAD_PathFile + newFileName);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        List<qxtAuthor> authors = new ArrayList<>(authorService.findAllById(authorIds));
        book.setQxtAuthors(authors);

        bookService.saveBook(book);
        return "redirect:/books";
    }

    // Form sửa thông tin sách
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        qxtBook book = bookService.getBookById(id);
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.getAllAuthors());
        return "books/book-form";
    }

    // Xóa sách
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}
