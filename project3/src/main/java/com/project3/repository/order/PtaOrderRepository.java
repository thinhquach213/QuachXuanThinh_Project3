package com.project3.repository.order;

import com.project3.entity.order.PtaOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PtaOrderRepository extends JpaRepository<PtaOrder, Long> {

    // Tìm 1 đơn hàng khớp ID + SĐT
    Optional<PtaOrder> findByIdAndPhone(Long id, String phone);

    // Tìm tất cả đơn theo số điện thoại
    List<PtaOrder> findByPhone(String phone);

    List<PtaOrder> findByUser_IdOrderByCreatedAtDesc(Long userId);

}