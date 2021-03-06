package ev.data.EmployeeVacations;

import ev.data.EmployeeVacations.Entities.Employee;
import ev.data.EmployeeVacations.Entities.HolidayRequest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public abstract class DBUtil
{
    abstract List<Employee> getEmployees() throws Exception;
    abstract List<HolidayRequest> getHolidayRequests(String loginEmployeeApplicant) throws Exception;
    abstract List<HolidayRequest> getHolidayRequestsB() throws Exception;

    protected static void close(Connection conn, Statement statement, ResultSet resultSet) {

        try {

            if (resultSet != null)
                resultSet.close();

            if (statement != null)
                statement.close();

            if (conn != null)
                conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
