package ev.data.EmployeeVacations;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBUtilClient extends DBUtil
{
    private DataSource dataSource;

    public DBUtilClient(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    @Override
    List<Employee> getEmployees() throws Exception {

        List<Employee> Employees = new ArrayList<>();

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet =  null;

        try
        {
            // Connection with the DataBase VacationsDatabase
            conn = dataSource.getConnection();

            // Order to see the table Employee.
            String sql = "SELECT * FROM VacationsDatabase.Employee";
            statement = conn.createStatement();

            // Results.
            resultSet = statement.executeQuery(sql);

            while(resultSet.next())
            {
                int id = resultSet.getInt("id");
                String employee_name = resultSet.getString("employee_name");
                String employee_lastName = resultSet.getString("employee_lastName");
                String login = resultSet.getString("login");
                String passcode = resultSet.getString("passcode");
                Date startDateJob = resultSet.getDate("startDateJob");
                int daysHolAvailable = resultSet.getInt("daysHolAvailable");

                Employees.add(new Employee(id, employee_name, employee_lastName,login, passcode,startDateJob,daysHolAvailable));
            }

        }

        finally
        {
            // Close objects JDBC
            close(conn, statement, resultSet);
        }

        return Employees;
    }

    @Override
    List<HolidayRequest> getHolidayRequests() throws Exception
    {
        List<HolidayRequest> HolidayRequests = new ArrayList<>();

        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet =  null;

        try
        {
            // Connection with the DataBase VacationsDatabase
            conn = dataSource.getConnection();

            // Order to see the table Employee.
            String sql = "SELECT * FROM VacationsDatabase.HolidayRequest";
            statement = conn.createStatement();

            // Results.
            resultSet = statement.executeQuery(sql);

            while(resultSet.next())
            {
                int id = resultSet.getInt("id");
                int idEmployeeApplicant = resultSet.getInt("idEmployeeApplicant");
                Date startDateHol = resultSet.getDate("startDateHol");
                Date endDateHol = resultSet.getDate("endDateHol");
                int totalDays = resultSet.getInt("totalDays");
                String state = resultSet.getString("state");

                HolidayRequests.add(new HolidayRequest(id,idEmployeeApplicant,startDateHol,endDateHol,totalDays,state));
            }

        }
        finally
        {
            // Close objects JDBC
            close(conn, statement, resultSet);
        }

        return HolidayRequests;
    }
}
