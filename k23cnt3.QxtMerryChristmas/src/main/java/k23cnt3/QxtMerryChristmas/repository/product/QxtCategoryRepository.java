package k23cnt3.QxtMerryChristmas.repository.product;

import k23cnt3.QxtMerryChristmas.entity.product.QxtCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QxtCategoryRepository extends JpaRepository<QxtCategory, Long> {
}
