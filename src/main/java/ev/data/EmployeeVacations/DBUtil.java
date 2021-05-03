package ev.data.EmployeeVacations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public abstract class DBUtil
{
    abstract List<Employee> getEmployees() throws Exception;
    abstract List<HolidayRequest> getHolidayRequests() throws Exception;

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
