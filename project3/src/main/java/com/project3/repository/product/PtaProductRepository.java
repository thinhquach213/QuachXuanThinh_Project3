package com.project3.repository.product;

import com.project3.entity.product.PtaProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PtaProductRepository extends JpaRepository<PtaProduct, Long> {

    List<PtaProduct> findByCategoryId(Long categoryId);

    List<PtaProduct> findByNameContainingIgnoreCase(String name);

    List<PtaProduct> findByTag(String tag);

}