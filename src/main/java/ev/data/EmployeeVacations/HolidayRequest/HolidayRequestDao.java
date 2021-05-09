package ev.data.EmployeeVacations.HolidayRequest;
import ev.data.EmployeeVacations.Entities.HolidayRequest;

import java.sql.SQLException;
import java.util.List;

public interface HolidayRequestDao
{
    void insertHolidayRequest(HolidayRequest holidayRequest) throws SQLException;

    HolidayRequest selectHolidayRequestById(int holidayRequestId);

    List<HolidayRequest> selectAllHolidayRequests();

    boolean deleteHolidayRequest(int holidayRequestId) throws SQLException;

    boolean updateHolidayRequest(HolidayRequest holidayRequest) throws SQLException;

    boolean updateHolidayRequestB(HolidayRequest holidayRequest) throws SQLException;

    boolean updateHolidayRequestADMIN(HolidayRequest holidayRequest) throws SQLException;

    boolean updateHolidayRequestADMIN_B(HolidayRequest holidayRequest) throws SQLException;


}
