package ev.data.EmployeeVacations;

import ev.data.EmployeeVacations.Entities.Employee;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registerServlet")
public class RegisterControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DataAccessObject dataAccessObject;

    public void init() {
        dataAccessObject = new DataAccessObject();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            register(request, response);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("/register.jsp");
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
//      String startDateJobReal = request.getParameter("startDate");
//      Date startDateJob = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(startDateJobReal);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(request.getParameter("startDate"));
        LocalDate startDateJob = LocalDate.parse(request.getParameter("startDate"),dateFormat);

        Employee employee = new Employee();
        employee.setEmployee_name(firstName);
        employee.setEmployee_lastName(lastName);
        employee.setLogin(username);
        employee.setpassword(password);
        employee.setStartDateJob(startDateJob);

        try {
            int result = dataAccessObject.registerEmployee(employee);
            if (result == 1) {
                request.setAttribute("NOTIFICATION", "User Registered Successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
        dispatcher.forward(request, response);
    }
}