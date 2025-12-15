package k23cnt3.QxtMerryChristmas.repository.product;

import k23cnt3.QxtMerryChristmas.entity.product.QxtProductComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QxtProductCommentRepository
        extends JpaRepository<QxtProductComment, Long> {

    List<QxtProductComment>
    findByProductIdOrderByCreatedAtDesc(Long productId);
}