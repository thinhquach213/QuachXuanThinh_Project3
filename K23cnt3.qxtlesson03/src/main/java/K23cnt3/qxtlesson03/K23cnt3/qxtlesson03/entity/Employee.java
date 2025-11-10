package K23cnt3.qxtlesson03.K23cnt3.qxtlesson03.entity;

public class Employee {
    private Long id;
    private String fullName;
    private boolean gender;
    private int age;
    private double salary;

    public Employee(Long id, String fullName, boolean gender, int age, double salary) {
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.age = age;
        this.salary = salary;
    }
}
