package k23cnt3.qxtWebbansach.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import k23cnt3.qxtWebbansach.entity.Review;
import k23cnt3.qxtWebbansach.entity.Product;
import k23cnt3.qxtWebbansach.entity.User;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByUser(User user);

    @Query("SELECT AVG(r.rating) FROM Review r")
    Double getAverageRating();

    List<Review> findByProduct(Product product);
}
