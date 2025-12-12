package NhtK23cnt2.prj3.repository.product;

import NhtK23cnt2.prj3.entity.product.NhtCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NhtCategoryRepository extends JpaRepository<NhtCategory, Long> {
}
