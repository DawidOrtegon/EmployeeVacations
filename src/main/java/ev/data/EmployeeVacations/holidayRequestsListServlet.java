package ev.data.EmployeeVacations;

import ev.data.EmployeeVacations.Entities.HolidayRequest;
import ev.data.EmployeeVacations.Login.LoginBean;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

// Only to see and check the tables. To ADD and modify will be other servlet.

@WebServlet("/holidayRequestsListServlet")
public class holidayRequestsListServlet extends HttpServlet
{
    private DataSource dataSource;
    private DBUtilClient dbUtilClient;
    private LoginBean loginBean;

    public holidayRequestsListServlet()
    {
        Context initContext =  null;
        try {
            initContext = new InitialContext();
            Context envCtx = (Context) initContext.lookup("java:comp/env");
            // Look up our data source
            dataSource = (DataSource)
                    envCtx.lookup("jdbc/Employee_Vacations_Web_App_B");

        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() throws ServletException
    {
        super.init();
        try
        {
            dbUtilClient = new DBUtilClient(dataSource);
        }
        catch (Exception e)
        {
            throw new ServletException(e);
        }
    }

    // POST information.
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    // GET Information.
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            listHolidayRequests(request,response);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // Send the information to the JSP to show the Employee.
    private void listHolidayRequests(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        // Getting the information from the correct table.
        List<HolidayRequest> holidayRequestList = dbUtilClient.getHolidayRequestsB();

        // Adding the list of request to the correct attribute.
        request.setAttribute("HolidaysRequestsList", holidayRequestList);
        System.out.println(holidayRequestList.size());

        // Sending the information to the JSP file.
        RequestDispatcher dispatcher = request.getRequestDispatcher("/holidayRequestsListB.jsp");
        dispatcher.forward(request,response);

    }
}
