package k23cnt3.QxtMerryChristmas.repository.order;

import k23cnt3.QxtMerryChristmas.entity.order.QxtOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QxtOrderRepository extends JpaRepository<QxtOrder, Long> {

    // Tìm 1 đơn hàng khớp ID + SĐT
    Optional<QxtOrder> findByIdAndPhone(Long id, String phone);

    // Tìm tất cả đơn theo số điện thoại
    List<QxtOrder> findByPhone(String phone);

    List<QxtOrder> findByUser_IdOrderByCreatedAtDesc(Long userId);

}
