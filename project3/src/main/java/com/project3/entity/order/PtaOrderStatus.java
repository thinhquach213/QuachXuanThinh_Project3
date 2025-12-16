package com.project3.entity.order;

public enum PtaOrderStatus {

    WAIT_CONFIRM("Chờ xác nhận"),
    PREPARING("Đang chuẩn bị"),
    SHIPPING("Đang giao"),
    COMPLETED("Hoàn tất"),
    CANCELLED("Đã hủy");

    private final String viLabel;

    PtaOrderStatus(String viLabel) {
        this.viLabel = viLabel;
    }

    public String getViLabel() {
        return viLabel;
    }
}