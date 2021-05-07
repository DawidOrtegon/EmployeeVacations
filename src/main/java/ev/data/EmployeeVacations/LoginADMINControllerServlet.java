package ev.data.EmployeeVacations;

import ev.data.EmployeeVacations.Entities.HolidayRequest;
import ev.data.EmployeeVacations.Login.LoginBean;
import ev.data.EmployeeVacations.Login.LoginDao;

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
import java.io.IOException;
import java.util.List;

@WebServlet("/LoginADMINControllerServlet")
public class LoginADMINControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DataSource dataSource;
    private LoginDao loginDao;
    private DBUtilClient dbUtilClient;

    public LoginADMINControllerServlet()
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


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("/loginAdmin.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            authenticateB(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // For the Manager
    private void authenticateB(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean isManager = Boolean.parseBoolean(request.getParameter("isManager"));
        LoginBean loginBean = new LoginBean();
        loginBean.setUsername(username);
        loginBean.setPassword(password);
        loginBean.setManager(isManager);

        try {
            if (loginDao.validateB(loginBean))
            {
                List<HolidayRequest> holidayRequestListADMIN = dbUtilClient.getHolidayRequestsB();
                request.setAttribute("HolidaysRequestsList",holidayRequestListADMIN);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/holidayRequestsListADMIN.jsp");
                dispatcher.forward(request, response);
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