package ev.data.EmployeeVacations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet("/")
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
        String action =  request.getServletPath();

        switch (action)
        {
            case "/new": showNewForm(request,response);
            break;

            case "/insert": insertHolidayRequest(request,response);
            break;

            case "/delete": deleteHolidayRequest(request,response);
            break;

            case "/edit": showEditForm(request,response);
            break;

            case "/update": updateHolidayRequest(request,response);
            break;

            case "/list": listHolidayRequests(request,response);
            break;

            default:
                RequestDispatcher dispatcher = request.getRequestDispatcher("/loginB.jsp");
                dispatcher.forward(request,response);
                break;
        }

    }

    // ALL THE METHODS DEFINED PREVIOUSLY.

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/");
        dispatcher.forward(request,response);
    }

    private void insertHolidayRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    private void deleteHolidayRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    private void updateHolidayRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    private void listHolidayRequests(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

}
