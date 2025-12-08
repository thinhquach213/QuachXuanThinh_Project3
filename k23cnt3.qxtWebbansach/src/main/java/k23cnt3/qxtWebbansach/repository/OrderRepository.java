package k23cnt3.qxtWebbansach.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import k23cnt3.qxtWebbansach.entity.Order;
import k23cnt3.qxtWebbansach.entity.User;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);

    @Query("SELECT SUM(o.totalPrice) FROM Order o")
    Double getTotalRevenue();
}
