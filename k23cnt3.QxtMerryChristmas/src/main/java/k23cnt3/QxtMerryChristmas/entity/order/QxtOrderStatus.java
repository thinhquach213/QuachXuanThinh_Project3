package k23cnt3.QxtMerryChristmas.entity.order;

public enum QxtOrderStatus {

    WAIT_CONFIRM("Chờ xác nhận"),
    PREPARING("Đang chuẩn bị"),
    SHIPPING("Đang giao"),
    COMPLETED("Hoàn tất"),
    CANCELLED("Đã hủy");

    private final String viLabel;

    QxtOrderStatus(String viLabel) {
        this.viLabel = viLabel;
    }

    public String getViLabel() {
        return viLabel;
    }
}
