package ev.data.EmployeeVacations.HolidayRequest;

import ev.data.EmployeeVacations.Entities.HolidayRequest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/HolidayRequestController")
public class HolidayRequestController extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    private HolidayRequestDao holidayRequestDao;

    public void init()
    {
        // Since the CRUD implements Dao we can access and call the methods correctly.
        holidayRequestDao = new HolidayRequestCRUD();
    }

    // POST method.
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request,response);
    }

    // GET method, where all the method of CRUD will be call for the employee.
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // To divide all the actions according to the path.
        String command =  request.getParameter("command");

        try {


            switch (command)
            {
                case "NEW":
                    showNewForm(request, response);
                    break;

                case "INSERT":
                    insertHolidayRequest(request, response);
                    break;

                case "DELETE":
                    deleteHolidayRequest(request, response);
                    break;

                case "EDIT":
                    showEditForm(request, response);
                    break;

                case "UPDATE":
                    updateHolidayRequest(request, response);
                    break;

                case "LIST":
                    listHolidayRequests(request, response);
                    break;

                default:
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/loginB.jsp");
                    dispatcher.forward(request, response);
                    break;
            }
        }

        catch (SQLException ex) {
            throw new ServletException(ex);
        }

    }

    // ALL THE METHODS DEFINED PREVIOUSLY.

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/submitHolidayRequest.jsp");
        dispatcher.forward(request,response);
    }

    private void insertHolidayRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        // Format for the dated indicated.
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        int idEmployeeApplicant = Integer.parseInt(request.getParameter("idEmployeeApplicant"));
        LocalDate startDateHol = LocalDate.parse(request.getParameter("startDateHol"),dateFormat);
        LocalDate endDateHol = LocalDate.parse(request.getParameter("endDateHol"),dateFormat);
        String status = "Sent";

        // Making the request.
        HolidayRequest holidayRequest = new HolidayRequest(idEmployeeApplicant,startDateHol,endDateHol,status);
        holidayRequestDao.insertHolidayRequest(holidayRequest);
        response.sendRedirect("LIST");

    }

    private void deleteHolidayRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException
    {
        int id = Integer.parseInt(request.getParameter("id"));
        holidayRequestDao.deleteHolidayRequest(id);
        response.sendRedirect("list");

    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int id = Integer.parseInt("id");
        HolidayRequest existingHolidayRequest = holidayRequestDao.selectHolidayRequestById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/submitHolidayRequest.jsp");
        request.setAttribute("holidayRequest",existingHolidayRequest);
        dispatcher.forward(request,response);
    }

    private void updateHolidayRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        // Format for the dated indicated.
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        int id = Integer.parseInt(request.getParameter("id"));
        int idEmployeeApplicant = Integer.parseInt(request.getParameter("idEmployeeApplicant"));
        LocalDate startDateHol = LocalDate.parse(request.getParameter("startDateHol"), dateFormat);
        LocalDate endDateHol = LocalDate.parse(request.getParameter("endDateHol"), dateFormat);
        String status =  request.getParameter("status");

        HolidayRequest holidayRequest = new HolidayRequest(id, idEmployeeApplicant,startDateHol, endDateHol, status);
        holidayRequestDao.updateHolidayRequest(holidayRequest);

        // List again the requests made.
        response.sendRedirect("LIST");

    }

    private void listHolidayRequests(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException
    {
        List<HolidayRequest> holidayRequestList = holidayRequestDao.selectAllHolidayRequests();
        request.setAttribute("HolidaysRequestsList", holidayRequestList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("holidaysRequestsList.jsp");
        dispatcher.forward(request,response);
    }

}
