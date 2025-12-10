package k23cnt3.qxtWebbansach.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "qxt_news")   // Đổi tên bảng
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qxt_id")     // Đổi tên cột id
    private Long id;

    @Column(name = "qxt_title")  // Tiêu đề
    private String title;

    @Column(name = "qxt_summary")  // Tóm tắt
    private String summary;

    @Column(name = "qxt_content", columnDefinition = "TEXT")  // Nội dung
    private String content;

    @Column(name = "qxt_image_url") // Link ảnh
    private String imageUrl;

    @Column(name = "qxt_created_date") // Ngày tạo
    private LocalDateTime createdDate;

    @PrePersist
    public void prePersist() {
        if (createdDate == null) {
            createdDate = LocalDateTime.now();
        }
        if (imageUrl == null || imageUrl.isEmpty()) {
            imageUrl = "/images/demo-news.jpg"; // ảnh mặc định
        }
    }

    // Getter – Setter thủ công nếu bạn muốn giữ nguyên
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public LocalDateTime getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }
}
