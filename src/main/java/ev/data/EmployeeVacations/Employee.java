package ev.data.EmployeeVacations;

import java.time.LocalDate;
import java.util.Date;

public class Employee
{
    // Attributes
    private int id;
    private String employee_name;
    private String employee_lastName;
    private String login;
    private String passcode;
    private Date startDateJob;
    private int daysHolAvailable;

    // Constructor without ID.
    public Employee(String employee_name, String employee_lastName, String login, String passcode, Date startDateJob, int daysHolAvailable) {
        this.employee_name = employee_name;
        this.employee_lastName = employee_lastName;
        this.login = login;
        this.passcode = passcode;
        this.startDateJob = startDateJob;
        this.daysHolAvailable = daysHolAvailable;
    }

    // Constructor with ID

    public Employee(int id, String employee_name, String employee_lastName, String login, String passcode, Date startDateJob, int daysHolAvailable) {
        this.id = id;
        this.employee_name = employee_name;
        this.employee_lastName = employee_lastName;
        this.login = login;
        this.passcode = passcode;
        this.startDateJob = startDateJob;
        this.daysHolAvailable = daysHolAvailable;
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

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public Date getStartDateJob() {
        return startDateJob;
    }

    public void setStartDateJob(Date startDateJob) {
        this.startDateJob = startDateJob;
    }

    public int getDaysHolAvailable() {
        return daysHolAvailable;
    }

    public void setDaysHolAvailable(int daysHolAvailable) {
        this.daysHolAvailable = daysHolAvailable;
    }


    // To string method.

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", employee_name='" + employee_name + '\'' +
                ", employee_lastName='" + employee_lastName + '\'' +
                ", login='" + login + '\'' +
                ", passcode='" + passcode + '\'' +
                ", startDateJob=" + startDateJob + '\'' +
                ", daysHolAvailable=" + daysHolAvailable + '\'' +
                '}';
    }
}
