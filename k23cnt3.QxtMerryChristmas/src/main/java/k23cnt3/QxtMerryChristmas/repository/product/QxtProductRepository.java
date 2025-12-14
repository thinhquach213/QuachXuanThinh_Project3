package k23cnt3.QxtMerryChristmas.repository.product;

import k23cnt3.QxtMerryChristmas.entity.product.QxtProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QxtProductRepository extends JpaRepository<QxtProduct, Long> {

    List<QxtProduct> findByCategoryId(Long categoryId);

    List<QxtProduct> findByNameContainingIgnoreCase(String name);

    List<QxtProduct> findByTag(String tag);

}
