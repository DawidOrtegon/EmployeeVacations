package ev.data.EmployeeVacations.Entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Employee implements Serializable
{
    // Attributes
    private int id;
    private String employee_name;
    private String employee_lastName;
    private String login;
    private String password;
    private LocalDate startDateJob;

    // Empty constructor.
    public Employee() {
    }

    // Constructor without ID.
    public Employee(String employee_name, String employee_lastName, String login, String password, LocalDate startDateJob) {
        this.employee_name = employee_name;
        this.employee_lastName = employee_lastName;
        this.login = login;
        this.password = password;
        this.startDateJob = startDateJob;

    }

    // Constructor with ID

    public Employee(int id, String employee_name, String employee_lastName, String login, String password, LocalDate startDateJob) {
        this.id = id;
        this.employee_name = employee_name;
        this.employee_lastName = employee_lastName;
        this.login = login;
        this.password = password;
        this.startDateJob = startDateJob;
    }

    // Getter and Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmployee_lastName() {
        return employee_lastName;
    }

    public void setEmployee_lastName(String employee_lastName) {
        this.employee_lastName = employee_lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public LocalDate getStartDateJob() {
        return startDateJob;
    }

    public void setStartDateJob(LocalDate startDateJob) {
        this.startDateJob = startDateJob;
    }

    // To string method.
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", employee_name='" + employee_name + '\'' +
                ", employee_lastName='" + employee_lastName + '\'' +
                ", login='" + login + '\'' +
                ", passcode='" + password + '\'' +
                ", startDateJob=" + startDateJob + '\'' +
                '}';
    }
}
