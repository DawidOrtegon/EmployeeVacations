package ev.data.EmployeeVacations;

import ev.data.EmployeeVacations.Entities.Employee;

import java.io.IOException;
import java.text.ParseException;
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
    private RegisterDao registerDao;

    public void init() {
        registerDao = new RegisterDao();
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
        // For the date, take in mind the transforming functon.
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(request.getParameter("startDate"));
        LocalDate startDateJob = LocalDate.parse(request.getParameter("startDate"),dateFormat);
        Boolean manager = Boolean.parseBoolean(request.getParameter("manager"));


        Employee employee = new Employee();
        employee.setEmployee_name(firstName);
        employee.setEmployee_lastName(lastName);
        employee.setLogin(username);
        employee.setpassword(password);
        employee.setStartDateJob(startDateJob);
        employee.setManager(manager);

        try {
            int result = registerDao.registerEmployee(employee);
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