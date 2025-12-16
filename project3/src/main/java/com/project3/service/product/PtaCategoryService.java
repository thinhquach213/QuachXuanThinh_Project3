package com.project3.service.product;

import com.project3.entity.product.PtaCategory;
import com.project3.repository.product.PtaCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PtaCategoryService {

    private final PtaCategoryRepository categoryRepository;

    public List<PtaCategory> getAll() {
        return categoryRepository.findAll();
    }

    public PtaCategory getById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
}