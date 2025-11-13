package k23cnt3.qxtlesson04.dto;

import jakarta.validation.constraints.*;


public class QxtMonHocDTO {
    @NotBlank(message = "Mã môn học không được để trống")
    @Size(min = 2, max = 7, message = "Mã môn học từ 2-7 ký tự")
    private String mamh;

    @NotBlank(message = "Tên môn học không được để trống")
    @Size(min = 5, max = 50, message = "Tên môn học từ 5-50 ký tự")
    private String tenmh;

    @Min(value = 45, message = "Số tiết tối thiểu là 45")
    @Max(value = 75, message = "Số tiết tối đa là 75")
    private int sotiet;

    public QxtMonHocDTO() {}

    public QxtMonHocDTO(String mamh, String tenmh, int sotiet) {
        this.mamh = mamh;
        this.tenmh = tenmh;
        this.sotiet = sotiet;
    }

    public String getMamh() {
        return mamh;
    }

    public void setMamh(String mamh) {
        this.mamh = mamh;
    }

    public String getTenmh() {
        return tenmh;
    }

    public void setTenmh(String tenmh) {
        this.tenmh = tenmh;
    }

    public int getSotiet() {
        return sotiet;
    }

    public void setSotiet(int sotiet) {
        this.sotiet = sotiet;
    }
}
