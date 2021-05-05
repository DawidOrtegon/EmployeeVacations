package ev.data.EmployeeVacations;

import ev.data.EmployeeVacations.DBUtilClient;
import ev.data.EmployeeVacations.Entities.HolidayRequest;
import ev.data.EmployeeVacations.Login.LoginBean;
import ev.data.EmployeeVacations.Login.LoginDao;

import java.io.IOException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet("/loginControllerServlet")
public class LoginControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DataSource dataSource;
    private LoginDao loginDao;
    private DBUtilClient dbUtilClient;

    public LoginControllerServlet()
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


    // Initialize the servlet.
    @Override
    public void init() throws ServletException
    {
        super.init();
        try
        {
            dbUtilClient = new DBUtilClient(dataSource);
            loginDao = new LoginDao();
        }
        catch (Exception e)
        {
            throw new ServletException(e);
        }
    }

    //    public void init()
    //    {
    //        loginDao = new LoginDao();
    //    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("/loginB.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            authenticate(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        LoginBean loginBean = new LoginBean();
        loginBean.setUsername(username);
        loginBean.setPassword(password);

        try {
            if (loginDao.validate(loginBean))
            {
                List<HolidayRequest> holidayRequestList = dbUtilClient.getHolidayRequests(username);

                if(holidayRequestList.size() == 0)
                {
                    List<HolidayRequest> holidayRequestListB = dbUtilClient.getHolidayRequestsB();
                    request.setAttribute("HolidaysRequestsList",holidayRequestListB);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("holidayRequestList.jsp");
                    dispatcher.forward(request, response);
                }

                else
                {
                    // Adding the list of request to the correct attribute.
                    request.setAttribute("HolidaysRequestsList", holidayRequestList);
                    System.out.println(holidayRequestList.size());

                    // Sending the information to the JSP file.
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/holidayRequestsList.jsp");
                    dispatcher.forward(request,response);
                }

            }
            else
            {
                HttpSession session = request.getSession();
                session.setAttribute("user", username);
                response.sendRedirect("loginB.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}