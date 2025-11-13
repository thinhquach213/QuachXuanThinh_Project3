package k23cnt3.qxtlesson04.entity;

public class QxtEmployee {
    private Long id;
    private String fullName;
    private boolean gender;
    private int age;
    private double salary;

    public QxtEmployee() {}

    public QxtEmployee(Long id, String fullName, boolean gender, int age, double salary) {
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.age = age;
        this.salary = salary;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public boolean isGender() { return gender; }
    public void setGender(boolean gender) { this.gender = gender; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
}
