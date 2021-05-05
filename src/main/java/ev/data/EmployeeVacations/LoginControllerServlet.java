package ev.data.EmployeeVacations;

import ev.data.EmployeeVacations.DBUtilClient;
import ev.data.EmployeeVacations.Entities.HolidayRequest;
import ev.data.EmployeeVacations.Login.LoginBean;
import ev.data.EmployeeVacations.Login.LoginDao;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/loginControllerServlet")
public class LoginControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginDao loginDao;
    private DBUtilClient dbUtilClient;

    public void init()
    {
        loginDao = new LoginDao();
    }

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
                int id = Integer.parseInt("id");
                List<HolidayRequest> holidayRequestList = dbUtilClient.getHolidayRequests(id);

                // Adding the list of request to the correct attribute.
                request.setAttribute("HolidaysRequestsList", holidayRequestList);
                System.out.println(holidayRequestList.size());

                // Sending the information to the JSP file.
                RequestDispatcher dispatcher = request.getRequestDispatcher("/holidayRequestsListB.jsp");
                dispatcher.forward(request,response);
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

    // Send the information to the JSP to show the Employee.
//    private void listHolidayRequests(HttpServletRequest request, HttpServletResponse response) throws Exception
//    {
//        // Getting the information from the correct table.
//        int id = Integer.parseInt("id");
//        List<HolidayRequest> holidayRequestList = dbUtilClient.getHolidayRequests(id);
//
//        // Adding the list of request to the correct attribute.
//        request.setAttribute("HolidaysRequestsList", holidayRequestList);
//        System.out.println(holidayRequestList.size());
//
//        // Sending the information to the JSP file.
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/holidayRequestsListB.jsp");
//        dispatcher.forward(request,response);
//
//    }
}