package com.project3.service.product;


import com.project3.entity.product.PtaProduct;
import com.project3.repository.product.PtaProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PtaProductService {

    private final PtaProductRepository productRepository;

    public List<PtaProduct> getAll() {
        return productRepository.findAll();
    }

    public List<PtaProduct> getByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    public PtaProduct getById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
    public List<PtaProduct> searchByKeyword(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }

    public List<PtaProduct> findByTag(String tag) {
        return productRepository.findByTag(tag);
    }
}