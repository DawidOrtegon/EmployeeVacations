package ev.data.EmployeeVacations.Registration;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ev.data.EmployeeVacations.Entities.Employee;
import ev.data.EmployeeVacations.JDBCUtils;

public class RegisterDao {

    public int registerEmployee(Employee employee) throws ClassNotFoundException {
        String INSERT_USERS_SQL = "INSERT INTO VacationsDatabaseB.Employee" +
                "  (employee_name, employee_lastName, login, password, startDateJob, manager) VALUES " +
                " (?, ?, ?, ?, ?, ?);";

        int result = 0;
        try (Connection connection = JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, employee.getEmployee_name());
            preparedStatement.setString(2, employee.getEmployee_lastName());
            preparedStatement.setString(3, employee.getLogin());
            preparedStatement.setString(4, employee.getpassword());
            preparedStatement.setDate(5, JDBCUtils.getSQLDate(employee.getStartDateJob()));
            preparedStatement.setBoolean(6,employee.isManager());

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process sql exception
            JDBCUtils.printSQLException(e);
        }
        return result;
    }
}