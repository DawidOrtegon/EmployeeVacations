package ev.data.EmployeeVacations.Entities;

import java.time.LocalDate;
import java.util.Date;

public class HolidayRequest
{
    // Fields in the table.
    private int id;
    private int idEmployeeApplicant;
    private Date startDateHol;
    private Date endDateHol;
    private int totalDays;
    private String state;

    // Constructor without ID.
    public HolidayRequest(int idEmployeeApplicant, Date startDateHol, Date endDateHol, int totalDays, String state) {
        this.idEmployeeApplicant = idEmployeeApplicant;
        this.startDateHol = startDateHol;
        this.endDateHol = endDateHol;
        this.totalDays = totalDays;
        this.state = state;
    }


    // Constructor with ID.
    public HolidayRequest(int id, int idEmployeeApplicant, Date startDateHol, Date endDateHol, int totalDays, String state) {
        this.id = id;
        this.idEmployeeApplicant = idEmployeeApplicant;
        this.startDateHol = startDateHol;
        this.endDateHol = endDateHol;
        this.totalDays = totalDays;
        this.state = state;
    }

    // Getter and Setter Methods.
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEmployeeApplicant() {
        return idEmployeeApplicant;
    }

    public void setIdEmployeeApplicant(int idEmployeeApplicant) {
        this.idEmployeeApplicant = idEmployeeApplicant;
    }

    public Date getStartDateHol() {
        return startDateHol;
    }

    public void setStartDateHol(Date startDateHol) {
        this.startDateHol = startDateHol;
    }

    public Date getEndDateHol() {
        return endDateHol;
    }

    public void setEndDateHol(Date endDateHol) {
        this.endDateHol = endDateHol;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    // To string method.

    @Override
    public String toString() {
        return "HolidayRequest{" +
                "id=" + id + '\'' +
                ", idEmployeeApplicant=" + idEmployeeApplicant + '\'' +
                ", startDateHol=" + startDateHol + '\'' +
                ", endDateHol=" + endDateHol + '\'' +
                ", totalDays=" + totalDays + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
