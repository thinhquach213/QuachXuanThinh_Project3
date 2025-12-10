package k23cnt3.qxtWebbansach.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import k23cnt3.qxtWebbansach.entity.News;
import k23cnt3.qxtWebbansach.service.NewsService;

@Controller
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public String listNews(Model model) {
        model.addAttribute("newsList", newsService.getAllNews());
        return "user/news/list";
    }

    @GetMapping("/{id}")
    public String newsDetail(@PathVariable Long id, Model model) {
        News news = newsService.getNewsById(id);
        if (news == null) {
            return "redirect:/news";
        }
        model.addAttribute("news", news);
        return "user/news/detail";
    }
}
