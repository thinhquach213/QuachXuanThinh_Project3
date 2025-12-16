package com.project3.repository.product;

import com.project3.entity.product.PtaCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PtaCategoryRepository extends JpaRepository<PtaCategory, Long> {
}