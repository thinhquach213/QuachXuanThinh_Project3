package k23cnt3.qxtlesson04.entity;

public class QxtKhoa {
    private String makh;
    private String tenkh;

    public QxtKhoa() {}

    public QxtKhoa(String makh, String tenkh) {
        this.makh = makh;
        this.tenkh = tenkh;
    }

    public String getMakh() { return makh; }
    public void setMakh(String makh) { this.makh = makh; }

    public String getTenkh() { return tenkh; }
    public void setTenkh(String tenkh) { this.tenkh = tenkh; }
}
