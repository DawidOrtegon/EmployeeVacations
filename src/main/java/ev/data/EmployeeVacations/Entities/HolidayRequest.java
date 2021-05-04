package ev.data.EmployeeVacations.Entities;

import java.time.LocalDate;
import java.util.Date;

public class HolidayRequest
{
    // Fields in the table.
    private int id;
    private int idEmployeeApplicant;
    private LocalDate startDateHol;
    private LocalDate endDateHol;
    private String status;

    // Empty constructor
    public HolidayRequest()
    {
    }

    // Constructor without ID.
    public HolidayRequest(int idEmployeeApplicant, LocalDate startDateHol, LocalDate endDateHol, String status) {
        this.idEmployeeApplicant = idEmployeeApplicant;
        this.startDateHol = startDateHol;
        this.endDateHol = endDateHol;
        this.status = status;
    }

    // Constructor with ID.
    public HolidayRequest(int id, int idEmployeeApplicant, LocalDate startDateHol, LocalDate endDateHol, String status) {
        this.id = id;
        this.idEmployeeApplicant = idEmployeeApplicant;
        this.startDateHol = startDateHol;
        this.endDateHol = endDateHol;
        this.status = status;
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

    public LocalDate getStartDateHol() {
        return startDateHol;
    }

    public void setStartDateHol(LocalDate startDateHol) {
        this.startDateHol = startDateHol;
    }

    public LocalDate getEndDateHol() {
        return endDateHol;
    }

    public void setEndDateHol(LocalDate endDateHol) {
        this.endDateHol = endDateHol;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // To string method.

    @Override
    public String toString() {
        return "HolidayRequest{" +
                "id=" + id + '\'' +
                ", idEmployeeApplicant=" + idEmployeeApplicant + '\'' +
                ", startDateHol=" + startDateHol + '\'' +
                ", endDateHol=" + endDateHol + '\'' +
                ", state='" + status + '\'' +
                '}';
    }
}
