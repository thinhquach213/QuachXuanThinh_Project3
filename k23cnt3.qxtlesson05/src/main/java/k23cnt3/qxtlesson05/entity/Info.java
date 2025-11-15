package k23cnt3.qxtlesson05.entity;

public class Info {

    private String name;
    private String code;
    private String email;
    private String website;

    public Info() {
    }

    public Info(String name, String code, String email, String website) {
        this.name = name;
        this.code = code;
        this.email = email;
        this.website = website;
    }

    // Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
