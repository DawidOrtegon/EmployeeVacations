package ev.data.EmployeeVacations;

import ev.data.EmployeeVacations.Entities.Employee;
import ev.data.EmployeeVacations.Entities.HolidayRequest;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBUtilClient extends DBUtil
{
    private final DataSource dataSource;

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
                String password = resultSet.getString("password");
                // String startDateJob = resultSet.getDate("startDateJob");
                LocalDate startDateJob = resultSet.getDate("startDateJob").toLocalDate();
                Boolean manager = resultSet.getBoolean("manager");
                Employees.add(new Employee(id, employee_name, employee_lastName,login, password,startDateJob,manager));
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
    List<HolidayRequest> getHolidayRequests(String loginEmployeeApplicant) throws Exception
    {
        List<HolidayRequest> HolidayRequests = new ArrayList<>();

        Connection conn = null;

        try
        {
            // Connection with the DataBase VacationsDatabase
            conn = dataSource.getConnection();

            // Order to see the table holiday request by the user log in, where username and passcode equal.
            String sql = "SELECT * FROM VacationsDatabaseB.HolidayRequest WHERE loginEmployeeApplicant = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,loginEmployeeApplicant);
            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();

            // Results.
            while(resultSet.next())
            {
                // To set the date with the correct format of MySQL.
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                int id = resultSet.getInt("id");
                int idEmployeeApplicant = resultSet.getInt("idEmployeeApplicant");
                LocalDate startDateHol = LocalDate.parse(resultSet.getString("startDateHol"),dateFormat);
                LocalDate endDateHol = LocalDate.parse(resultSet.getString("endDateHol"),dateFormat);
                String state = resultSet.getString("status");

                HolidayRequests.add(new HolidayRequest(id,idEmployeeApplicant, loginEmployeeApplicant, startDateHol,endDateHol,state));
            }

        }
        catch (SQLException e)
        {
            JDBCUtils.printSQLException(e);
        }

        return HolidayRequests;
    }

    @Override
    List<HolidayRequest> getHolidayRequestsB() throws Exception
    {
        List<HolidayRequest> HolidayRequests = new ArrayList<>();

        Connection conn = null;

        try
        {
            // Connection with the DataBase VacationsDatabase
            conn = dataSource.getConnection();

            // Order to see the table holiday request by the user log in, where username and passcode equal.
            String sql = "SELECT * FROM VacationsDatabaseB.HolidayRequest";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();

            // Results.
            while(resultSet.next())
            {
                // To set the date with the correct format of MySQL.
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                int id = resultSet.getInt("id");
                int idEmployeeApplicant = resultSet.getInt("idEmployeeApplicant");
                String loginEmployeeApplicant = resultSet.getString("loginEmployeeApplicant");
                LocalDate startDateHol = LocalDate.parse(resultSet.getString("startDateHol"),dateFormat);
                LocalDate endDateHol = LocalDate.parse(resultSet.getString("endDateHol"),dateFormat);
                String state = "Not Available";

                HolidayRequests.add(new HolidayRequest(id,idEmployeeApplicant, loginEmployeeApplicant, startDateHol,endDateHol,state));
            }

        }
        catch (SQLException e)
        {
            JDBCUtils.printSQLException(e);
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
