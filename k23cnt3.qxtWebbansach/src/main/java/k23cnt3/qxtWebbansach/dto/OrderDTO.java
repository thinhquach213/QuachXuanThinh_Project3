package k23cnt3.qxtWebbansach.dto;

import java.math.BigDecimal;
import java.util.List;

public class OrderDTO {
    private Long id;
    private Long userId;
    private String userName;
    private BigDecimal totalAmount;
    private String status;
    private String paymentMethod;
    private List<CartDTO> orderItems;

    public OrderDTO() {}

    // getter & setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public List<CartDTO> getOrderItems() { return orderItems; }
    public void setOrderItems(List<CartDTO> orderItems) { this.orderItems = orderItems; }
}
