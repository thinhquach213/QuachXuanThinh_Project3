package com.project3.service.product;

import com.project3.entity.product.PtaProductComment;
import com.project3.repository.product.PtaProductCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PtaProductCommentService {

    private final PtaProductCommentRepository commentRepository;

    public List<PtaProductComment> getByProduct(Long productId) {
        return commentRepository
                .findByProductIdOrderByCreatedAtDesc(productId);
    }

    public void save(PtaProductComment comment) {
        comment.setCreatedAt(LocalDateTime.now());
        commentRepository.save(comment);
    }
}