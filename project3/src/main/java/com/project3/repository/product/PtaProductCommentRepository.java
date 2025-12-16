package com.project3.repository.product;

import com.project3.entity.product.PtaProductComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PtaProductCommentRepository
        extends JpaRepository<PtaProductComment, Long> {

    List<PtaProductComment>
    findByProductIdOrderByCreatedAtDesc(Long productId);
}