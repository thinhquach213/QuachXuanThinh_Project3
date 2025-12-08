package k23cnt3.qxtWebbansach.service;

import org.springframework.stereotype.Service;
import k23cnt3.qxtWebbansach.entity.News;
import k23cnt3.qxtWebbansach.repository.NewsRepository;

import java.util.List;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    // Lấy tất cả tin tức
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    // Lấy chi tiết 1 tin theo id
    public News getNewsById(Long id) {
        return newsRepository.findById(id).orElse(null);
    }

    // Lưu tin mới hoặc cập nhật tin
    public News saveNews(News news) {
        if (news.getImageUrl() == null || news.getImageUrl().isEmpty()) {
            news.setImageUrl("/images/demo-news.jpg"); // ảnh mặc định
        }
        return newsRepository.save(news);
    }

    // Xóa tin
    public void deleteNews(Long id) {
        newsRepository.deleteById(id);
    }
}
