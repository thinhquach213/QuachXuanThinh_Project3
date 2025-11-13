package k23cnt3.qxtlesson04.dto;

import jakarta.validation.constraints.*;

public class QxtEmployeeDTO {
    private Long id;

    @NotBlank(message = "Họ tên không được để trống")
    private String fullName;

    private boolean gender;

    @Min(value = 18, message = "Tuổi tối thiểu là 18")
    @Max(value = 60, message = "Tuổi tối đa là 60")
    private int age;

    @Min(value = 3000000, message = "Lương tối thiểu là 3 triệu")
    private double salary;

    // Getter + Setter
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
