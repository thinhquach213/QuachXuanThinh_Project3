package k23cnt3.QxtMerryChristmas.service.product;

import k23cnt3.QxtMerryChristmas.entity.product.QxtProductComment;
import k23cnt3.QxtMerryChristmas.repository.product.QxtProductCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QxtProductCommentService {

    private final QxtProductCommentRepository commentRepository;

    public List<QxtProductComment> getByProduct(Long productId) {
        return commentRepository
                .findByProductIdOrderByCreatedAtDesc(productId);
    }

    public void save(QxtProductComment comment) {
        comment.setCreatedAt(LocalDateTime.now());
        commentRepository.save(comment);
    }
}
