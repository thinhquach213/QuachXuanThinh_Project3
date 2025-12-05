package k23cnt3.qxtWebbansach.dto;

import java.math.BigDecimal;

public class CartDTO {
    private Long id;
    private Long bookId;
    private String bookTitle;
    private int quantity;
    private BigDecimal price;
    private BigDecimal total;

    public CartDTO() {}

    // getter & setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getBookId() { return bookId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }

    public String getBookTitle() { return bookTitle; }
    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
}
