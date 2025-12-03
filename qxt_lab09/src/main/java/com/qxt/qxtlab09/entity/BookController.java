package com.qxt.qxtlab09.controller;

import com.qxt.qxtlab09.entity.*;
import com.qxt.qxtlab09.repository.*;
import com.qxt.qxtlab09.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookAuthorRepository bookAuthorRepo;

    private static final String UPLOAD_DIR = "src/main/resources/static/images/";

    @GetMapping
    public String list(Model model) {
        model.addAttribute("books", bookService.getAll());
        return "books/book-list";
    }

    @GetMapping("/new")
    public String showCreate(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.getAll());
        return "books/book-form";
    }

    @PostMapping("/new")
    public String save(@ModelAttribute Book book,
                       @RequestParam(value = "authorIds", required = false) List<Long> authorIds,
                       @RequestParam(value = "editorId", required = false) Long editorId,
                       @RequestParam("imageFile") MultipartFile imageFile) {

        // save book first to get id
        Book saved = bookService.save(book);

        // handle file upload (optional)
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                Path uploadPath = Paths.get(UPLOAD_DIR);
                if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);
                String original = StringUtils.cleanPath(Objects.requireNonNull(imageFile.getOriginalFilename()));
                String ext = original.contains(".") ? original.substring(original.lastIndexOf('.')) : "";
                String newName = "book_" + saved.getId() + ext;
                Path filePath = uploadPath.resolve(newName);
                Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                saved.setImgUrl("/images/" + newName);
                bookService.save(saved);
            } catch (IOException e) { e.printStackTrace(); }
        }

        // save BookAuthor relations
        if (authorIds != null) {
            for (Long aid : authorIds) {
                BookAuthorId id = new BookAuthorId(aid == null ? null : saved.getId(), aid);
                id.setBookId(saved.getId());
                id.setAuthorId(aid);

                BookAuthor ba = BookAuthor.builder()
                        .id(id)
                        .book(saved)
                        .author(authorService.findById(aid))
                        .isEditor(aid.equals(editorId))
                        .build();
                bookAuthorRepo.save(ba);
            }
        }
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Book b = bookService.findById(id);
        model.addAttribute("book", b);
        model.addAttribute("authors", authorService.getAll());
        return "books/book-form";
    }
}
