package ev.data.EmployeeVacations;

import ev.data.EmployeeVacations.Entities.Employee;

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
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/EmpSignUpServlet")
public class EmpSignUpServlet extends HttpServlet
{
    private DataSource dataSource;
    private DBUtilClient dbUtilClient;

    // Establish the connection correctly.
    public EmpSignUpServlet()
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
        }
        catch (Exception e)
        {
            throw new ServletException(e);
        }
    }

    // POST information.
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    // Do get to add the new employee to the table correctly.
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
        // From here the command is validated and then make the proper action according to the switch case made below.
        try
        {
            String command = request.getParameter("command");
            if (command == null)
                command = "LIST";

            switch (command)
            {

                case "LIST":
                    listEmployees(request, response);
                    break;

                case "ADD":
                    addEmployee(request, response);
                    break;
            }
        }
        catch(Exception e)
            {
                throw new ServletException(e);
            }


    }

    // Method to add the Employee based in the information given by the user. Call the method from DBUTIL client.
    private void addEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        String employee_name =  request.getParameter("name");
        String employee_lastName = request.getParameter("lastName");
        String login = request.getParameter("username");
        String passcode = request.getParameter("passcode");
        // Check after the types for this.
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDateJob = LocalDate.parse(request.getParameter("startDate"),dateFormat);

        // Opening the object to show.
        Employee employee = new Employee(employee_name,employee_lastName,login,passcode,startDateJob);

        // Adding the new employee to table in MySQL and ther list already declared.
        dbUtilClient.addEmployee(employee);

        // List of the employees.
        listEmployees(request,response);
    }

    // Send the information to the JSP to show the Employee.
    private void listEmployees(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        // Getting the information from the correct table.
        List<Employee> employeesList = dbUtilClient.getEmployees();

        // Adding the list of request to the correct attribute.
        request.setAttribute("EmployeeList", employeesList);
        System.out.println(employeesList.size());

        // Sending the information to the JSP file.
        RequestDispatcher dispatcher = request.getRequestDispatcher("/initial_view_Employee.jsp");
        dispatcher.forward(request,response);

    }

}


