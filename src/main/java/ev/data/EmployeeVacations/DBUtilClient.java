package ev.data.EmployeeVacations;

import ev.data.EmployeeVacations.Entities.Employee;
import ev.data.EmployeeVacations.Entities.HolidayRequest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    // GET the employees.
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
            String sql = "SELECT * FROM VacationsDatabaseB.Employee";
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
                // String startDateJob = resultSet.getDate("startDateJob");
                LocalDate startDateJob = resultSet.getDate("startDateJob").toLocalDate();
                Employees.add(new Employee(id, employee_name, employee_lastName,login, passcode,startDateJob));
            }

        }

        finally
        {
            // Close objects JDBC
            close(conn, statement, resultSet);
        }

        return Employees;
    }

    // GET holidays requests.
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
            String sql = "SELECT * FROM VacationsDatabaseB.HolidayRequest";
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

    // Add Employees to the DB.
    public void addEmployee(Employee employee) throws Exception
    {
        Connection conn = null;
        PreparedStatement statement = null;

        try
        {
            // Connection with the database.
            conn = dataSource.getConnection();

            // Command to insert the values into the table.
            String sql = "INSERT INTO Employee(employee_name,employee_lastName,login,passcode,startDateJob)" + "VALUES(?,?,?,?,?,?)";
            statement = conn.prepareStatement(sql);
            statement.setString(1,employee.getEmployee_name());
            statement.setString(2,employee.getEmployee_lastName());
            statement.setString(3,employee.getLogin());
            statement.setString(4,employee.getpassword());
            statement.setDate(5, JDBCUtils.getSQLDate(employee.getStartDateJob()));

            // Do the statement.
            statement.execute();

        }
        finally
        {
            close(conn,statement,null);

        }

    }
}
