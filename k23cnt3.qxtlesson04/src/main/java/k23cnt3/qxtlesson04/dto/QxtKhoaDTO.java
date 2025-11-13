package k23cnt3.qxtlesson04.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class QxtKhoaDTO {
    @NotBlank(message = "Mã khoa không được để trống")
    @Size(min = 2, message = "Mã khoa phải có ít nhất 2 ký tự")
    private String makh;

    @NotBlank(message = "Tên khoa không được để trống")
    private String tenkh;

    public String getMakh() { return makh; }
    public void setMakh(String makh) { this.makh = makh; }

    public String getTenkh() { return tenkh; }
    public void setTenkh(String tenkh) { this.tenkh = tenkh; }
}

