package K23cnt3.qxtlesson03.K23cnt3.qxtlesson03.entity;

public class QxtStudent {
    private long qxtId;
    private String qxtName;
    private int qxtAge;
    private boolean qxtGender;
    private String qxtAddress;
    private String qxtPhone;
    private String qxtEmail;

    public QxtStudent() {
    }

    public QxtStudent(long qxtId, String qxtName, int qxtAge, boolean qxtGender, String qxtAddress, String qxtPhone, String qxtEmail) {
        this.qxtId = qxtId;
        this.qxtName = qxtName;
        this.qxtAge = qxtAge;
        this.qxtGender = qxtGender;
        this.qxtAddress = qxtAddress;
        this.qxtPhone = qxtPhone;
        this.qxtEmail = qxtEmail;
    }

    public long getQxtId() {
        return qxtId;
    }

    public void setQxtId(long qxtId) {
        this.qxtId = qxtId;
    }

    public String getQxtName() {
        return qxtName;
    }

    public void setQxtName(String qxtName) {
        this.qxtName = qxtName;
    }

    public int getQxtAge() {
        return qxtAge;
    }

    public void setQxtAge(int qxtAge) {
        this.qxtAge = qxtAge;
    }

    public boolean isQxtGender() {
        return qxtGender;
    }

    public void setQxtGender(boolean qxtGender) {
        this.qxtGender = qxtGender;
    }

    public String getQxtAddress() {
        return qxtAddress;
    }

    public void setQxtAddress(String qxtAddress) {
        this.qxtAddress = qxtAddress;
    }

    public String getQxtPhone() {
        return qxtPhone;
    }

    public void setQxtPhone(String qxtPhone) {
        this.qxtPhone = qxtPhone;
    }

    public String getQxtEmail() {
        return qxtEmail;
    }

    public void setQxtEmail(String qxtEmail) {
        this.qxtEmail = qxtEmail;
    }
}
