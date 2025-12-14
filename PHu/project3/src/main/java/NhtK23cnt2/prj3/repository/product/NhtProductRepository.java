package NhtK23cnt2.prj3.repository.product;

import NhtK23cnt2.prj3.entity.product.NhtProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NhtProductRepository extends JpaRepository<NhtProduct, Long> {

    List<NhtProduct> findByCategoryId(Long categoryId);

    List<NhtProduct> findByNameContainingIgnoreCase(String name);

    List<NhtProduct> findByTag(String tag);

}
