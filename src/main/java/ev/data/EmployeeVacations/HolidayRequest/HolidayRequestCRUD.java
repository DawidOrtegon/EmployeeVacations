package ev.data.EmployeeVacations.HolidayRequest;

import ev.data.EmployeeVacations.Entities.HolidayRequest;
import ev.data.EmployeeVacations.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HolidayRequestCRUD implements HolidayRequestDao
{
    // Declaration of the queries as FINAL to have easy access to them.
    private static final String INSERT_HOLIDAY_REQUEST_SQL = "INSERT INTO VacationsDatabaseB.HolidayRequest" +
            "  (idEmployeeApplicant, loginEmployeeApplicant, startDateHol, endDateHol, status) VALUES " + " (?, ?, ?, ?, ?);";

    private static final String SELECT_HOLIDAY_REQUEST_BY_ID =  "SELECT id,idEmployeeApplicant,startDateHol,endDateHol,status FROM VacationsDatabaseB.HolidayRequest where id =?";
    private static final String SELECT_ALL_HOLIDAY_REQUESTS = "SELECT * FROM VacationsDatabaseB.HolidayRequest;";
    private static final String DELETE_HOLIDAY_REQUEST_BY_ID = "DELETE FROM VacationsDatabaseB.HolidayRequest WHERE id = ?;";
    private static final String UPDATE_HOLIDAY_REQUEST = "UPDATE VacationsDatabaseB.HolidayRequest SET idEmployeeApplicant = ?, loginEmployeeApplicant = ?, startDateHol= ?, endDateHol =?, status =? WHERE id = ?;";

    // FOR THE ADMIN
    private static final String UPDATE_HOLIDAY_REQUEST_ADMIN = "UPDATE VacationsDatabaseB.HolidayRequest SET status =? WHERE id = ?;";



    // Constructor of the class.
    public HolidayRequestCRUD() {
    }

    @Override
    public void insertHolidayRequest(HolidayRequest holidayRequest) throws SQLException
    {
        // See the query in the console.
        System.out.println(INSERT_HOLIDAY_REQUEST_SQL);

        try(Connection connection = JDBCUtils.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_HOLIDAY_REQUEST_SQL))
        {
            preparedStatement.setInt(1, holidayRequest.getIdEmployeeApplicant());
            preparedStatement.setString(2, holidayRequest.getLoginEmployeeApplicant());
            preparedStatement.setDate(3, JDBCUtils.getSQLDate(holidayRequest.getStartDateHol()));
            preparedStatement.setDate(4, JDBCUtils.getSQLDate(holidayRequest.getEndDateHol()));
            preparedStatement.setString(5, holidayRequest.getStatus());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        }

        catch (SQLException exception)
        {
            JDBCUtils.printSQLException(exception);
        }

    }

    @Override
    public HolidayRequest selectHolidayRequestById(int holidayRequestId)
    {
        HolidayRequest holidayRequest = null;

        // 1. Establishing the connection.
        try(Connection connection = JDBCUtils.getConnection())
        {
            // 2. Create the statement using the connection already created.
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_HOLIDAY_REQUEST_BY_ID);
            preparedStatement.setInt(1,holidayRequestId);
            System.out.println(preparedStatement);

            // 3. Execute the Query and update.
            ResultSet resultSet = preparedStatement.executeQuery();

            // 4. Process the result object.
            while(resultSet.next())
            {
                int id = resultSet.getInt("id");
                int idEmployeeApplicant = resultSet.getInt("idEmployeeApplicant");
                String loginEmployeeApplicant = resultSet.getString("loginEmployeeApplicant");
                LocalDate startDateHol = resultSet.getDate("startDateHol").toLocalDate();
                LocalDate endDateHol = resultSet.getDate("endDateHol").toLocalDate();
                String status = resultSet.getString("status");

                // Creation of the request to show.
                holidayRequest = new HolidayRequest(id,idEmployeeApplicant,loginEmployeeApplicant, startDateHol,endDateHol,status);
            }

        }
        catch (SQLException e)
        {
            JDBCUtils.printSQLException(e);
        }

        return holidayRequest;
    }

    @Override
    public List<HolidayRequest> selectAllHolidayRequests() {
        List<HolidayRequest> holidayRequestsList = new ArrayList<>();

        // 1. Making the connection.

        try(Connection connection = JDBCUtils.getConnection())
        {
            // 2. Create  the statement using the connection object created before.
            PreparedStatement preparedStatement =  connection.prepareStatement(SELECT_ALL_HOLIDAY_REQUESTS);
            System.out.println(preparedStatement);

            // 3. Execute and  update the query.
            ResultSet resultSet = preparedStatement.executeQuery();

            // 4. Process and show the results object.
            while(resultSet.next())
            {
                int id = resultSet.getInt("id");
                int idEmployeeApplicant = resultSet.getInt("idEmployeeApplicant");
                String loginEmployeeApplicant = resultSet.getString("loginEmployeeApplicant");
                LocalDate startDateHol = resultSet.getDate("startDateHol").toLocalDate();
                LocalDate endDateHol = resultSet.getDate("endDateHol").toLocalDate();
                String status = resultSet.getString("status");

                // Adding to the list of requests.
                holidayRequestsList.add(new HolidayRequest(id, idEmployeeApplicant, loginEmployeeApplicant, startDateHol, endDateHol,status));
            }


        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }

        return holidayRequestsList;
    }

    @Override
    public boolean deleteHolidayRequest(int holidayRequestId) throws SQLException
    {
        boolean holidayRequestDeleted;
        try(Connection connection = JDBCUtils.getConnection())
        {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_HOLIDAY_REQUEST_BY_ID);
            preparedStatement.setInt(1,holidayRequestId);
            holidayRequestDeleted = preparedStatement.executeUpdate() > 0;
        }
        return holidayRequestDeleted;
    }

    @Override
    public boolean updateHolidayRequest(HolidayRequest holidayRequest) throws SQLException
    {
        boolean holidayRequestUpdated;
        try(Connection connection = JDBCUtils.getConnection())
        {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_HOLIDAY_REQUEST);
            preparedStatement.setInt(1,holidayRequest.getId());
            preparedStatement.setInt(2,holidayRequest.getIdEmployeeApplicant());
            preparedStatement.setString(3,holidayRequest.getLoginEmployeeApplicant());
            preparedStatement.setDate(4,JDBCUtils.getSQLDate(holidayRequest.getStartDateHol()));
            preparedStatement.setDate(5,JDBCUtils.getSQLDate(holidayRequest.getEndDateHol()));
            preparedStatement.setString(6,holidayRequest.getStatus());

            holidayRequestUpdated = preparedStatement.executeUpdate() > 0;
        }
        return holidayRequestUpdated;
    }

    @Override
    public boolean updateHolidayRequestADMIN(HolidayRequest holidayRequest) throws SQLException {
        boolean holidayRequestUpdated;
        try(Connection connection = JDBCUtils.getConnection())
        {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_HOLIDAY_REQUEST_ADMIN);
            preparedStatement.setInt(1,holidayRequest.getId());
            preparedStatement.setString(2,holidayRequest.getStatus());

            holidayRequestUpdated = preparedStatement.executeUpdate() > 0;
        }
        return holidayRequestUpdated;
    }

}
