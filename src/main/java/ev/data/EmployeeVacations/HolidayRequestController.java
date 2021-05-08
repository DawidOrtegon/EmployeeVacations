package ev.data.EmployeeVacations;

import ev.data.EmployeeVacations.DBUtilClient;
import ev.data.EmployeeVacations.Entities.HolidayRequest;
import ev.data.EmployeeVacations.HolidayRequest.HolidayRequestCRUD;
import ev.data.EmployeeVacations.HolidayRequest.HolidayRequestDao;
import ev.data.EmployeeVacations.JDBCUtils;
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
import java.sql.Array;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/HolidayRequestController")
public class HolidayRequestController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private HolidayRequestDao holidayRequestDao;
    private DataSource dataSource;
    private LoginDao loginDao;
    private DBUtilClient dbUtilClient;

    public HolidayRequestController() {
        Context initContext = null;
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

    public void init() throws ServletException {
        super.init();
        try {
            dbUtilClient = new DBUtilClient(dataSource);
            loginDao = new LoginDao();
            holidayRequestDao = new HolidayRequestCRUD();
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

//    public void init() {
//        // Since the CRUD implements Dao we can access and call the methods correctly.
//        holidayRequestDao = new HolidayRequestCRUD();
//    }

    // POST method.
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    // GET method, where all the method of CRUD will be call for the employee.
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // To divide all the actions according to the path.
        try {
            String command = request.getParameter("command");

            if (command == null) {
                command = "LIST";
            }

            switch (command) {
                case "NEW":
                    showNewForm(request, response);
                    break;

                case "INSERT":
                    insertHolidayRequest(request, response);
                    break;

                case "DELETE":
                    deleteHolidayRequest(request, response);
                    break;

                case "DELETE_ADMIN":
                    deleteHolidayRequestADMIN(request, response);
                    break;

                case "EDIT":
                    showEditForm(request, response);
                    break;

                case "UPDATE":
                    updateHolidayRequest(request, response);
                    break;

                case "UPDATE_ADMIN":
                    updateHolidayRequestADMIN(request, response);
                    break;

                case "LIST":
                    listHolidayRequests(request, response);
                    break;

                case "LOAD":
                    loadRequest(request, response);
                    break;

                default:
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/loginB.jsp");
                    dispatcher.forward(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // ALL THE METHODS DEFINED PREVIOUSLY.

    // SHOW FORMAT
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/submitHolidayRequest.jsp");
        dispatcher.forward(request, response);
    }

    // INSERT REQUEST
    private void insertHolidayRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        // Format for the dated indicated.
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        int idEmployeeApplicant = Integer.parseInt(request.getParameter("idEmployeeApplicant"));
        String loginEmployeeApplicant = request.getParameter("loginEmployeeApplicant");
        LocalDate startDateHol = LocalDate.parse(request.getParameter("startDateHol"), dateFormat);
        LocalDate endDateHol = LocalDate.parse(request.getParameter("endDateHol"), dateFormat);
        String status = "Sent";

        // Making the request.
        HolidayRequest holidayRequest = new HolidayRequest(idEmployeeApplicant, loginEmployeeApplicant, startDateHol, endDateHol, status);
        holidayRequestDao.insertHolidayRequest(holidayRequest);
        System.out.println(holidayRequest);

        response.sendRedirect("holidayRequestsListServlet");

    }

    // DELETE REQUEST ADMIN
    private void deleteHolidayRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        holidayRequestDao.deleteHolidayRequest(id);

        // ADDING to check if we can call the list of all requests.
        String username = request.getParameter("username");
        LoginBean loginBean = new LoginBean();
        loginBean.setUsername(username);

        List<HolidayRequest> holidayRequestList = dbUtilClient.getHolidayRequests(username);

        if (holidayRequestList.size() == 0) {
            List<HolidayRequest> holidayRequestListB = dbUtilClient.getHolidayRequestsB();
            request.setAttribute("HolidaysRequestsList", holidayRequestListB);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/holidayRequestsListB.jsp");
            dispatcher.forward(request, response);
        } else {
            // Adding the list of request to the correct attribute.
            holidayRequestDao.deleteHolidayRequest(id);
            List<HolidayRequest> holidayRequestListC = holidayRequestDao.selectAllHolidayRequests();
            request.setAttribute("HolidaysRequestsList", holidayRequestListC);
            System.out.println(holidayRequestList.size());

            // Sending the information to the JSP file.
            RequestDispatcher dispatcher = request.getRequestDispatcher("/holidayRequestsListB.jsp");
            dispatcher.forward(request, response);
        }

    }

    // DELETE REQUEST BY ADMIN
    private void deleteHolidayRequestADMIN(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        holidayRequestDao.deleteHolidayRequest(id);
        List<HolidayRequest> holidayRequestList = holidayRequestDao.selectAllHolidayRequests();

        // ADDING to check if we can call the list of all requests.
        request.setAttribute("HolidaysRequestsList", holidayRequestList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("holidayRequestsListADMIN.jsp");
        dispatcher.forward(request, response);
    }


    // SHOW EDIT FORM
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt("id");
        HolidayRequest existingHolidayRequest = holidayRequestDao.selectHolidayRequestById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/submitHolidayRequest.jsp");
        request.setAttribute("holidayRequest", existingHolidayRequest);
        dispatcher.forward(request, response);
    }


    // UPDATE HOLIDAY REQUEST
    private void updateHolidayRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        // Format for the dated indicated.
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("id" + id);

        int idEmployeeApplicant = Integer.parseInt(request.getParameter("idEmployeeApplicant"));
        System.out.println("idEmployeeApplicant" + idEmployeeApplicant);

        String loginEmployeeApplicant = request.getParameter("loginEmployeeApplicant");
        LocalDate startDateHol = LocalDate.parse(request.getParameter("startDateHol"), dateFormat);
        LocalDate endDateHol = LocalDate.parse(request.getParameter("endDateHol"), dateFormat);
        String status = request.getParameter("status");

        HolidayRequest holidayRequest = new HolidayRequest(id, idEmployeeApplicant, loginEmployeeApplicant, startDateHol, endDateHol, status);
        holidayRequestDao.updateHolidayRequest(holidayRequest);

        List<HolidayRequest> holidayRequestList = holidayRequestDao.selectAllHolidayRequests();
        request.setAttribute("HolidaysRequestsList", holidayRequestList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("holidayRequestsList.jsp");
        dispatcher.forward(request, response);

        // List again the requests made.
        // response.sendRedirect("LIST");

    }

    private void loadRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // odczytanie id telefonu z formularza
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("id 1" + id);

        // pobranie  danych telefonu z BD
        HolidayRequest holidayRequest = holidayRequestDao.selectHolidayRequestById(id);
        System.out.println(holidayRequest + "1");

        // przekazanie telefonu do obiektu request
        // request.setAttribute("HolidaysRequestsList", holidayRequest);

        // wyslanie danych do formmularza JSP (update_phone_form)
        RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateRequestADMIN.jsp");
        dispatcher.forward(request, response);

    }


    // UPDATE HOLIDAY REQUEST ADMIN
    private void updateHolidayRequestADMIN(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        // Format for the dated indicated.
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("id 2" + id);

        HolidayRequest holidayRequest = holidayRequestDao.selectHolidayRequestById(id);


//        int idEmployeeApplicant = Integer.parseInt(request.getParameter("idEmployeeApplicant"));
//        System.out.println("idEmployeeApplicant " + idEmployeeApplicant);
//        String loginEmployeeApplicant = request.getParameter("loginEmployeeApplicant");
//        LocalDate startDateHol = LocalDate.parse(request.getParameter("startDateHol"), dateFormat);
//        LocalDate endDateHol = LocalDate.parse(request.getParameter("endDateHol"), dateFormat);
//        String status = request.getParameter("status");
//        HolidayRequest holidayRequest = new HolidayRequest(id, idEmployeeApplicant, loginEmployeeApplicant, startDateHol, endDateHol, status);



        holidayRequestDao.updateHolidayRequestADMIN(holidayRequest);
        System.out.println(holidayRequest + "4");

        List<HolidayRequest> holidayRequestListADMIN = holidayRequestDao.selectAllHolidayRequests();
        System.out.println(holidayRequestListADMIN + "5");
        request.setAttribute("HolidaysRequestsList", holidayRequestListADMIN);
        RequestDispatcher dispatcher = request.getRequestDispatcher("holidayRequestsListADMIN.jsp");
        dispatcher.forward(request, response);

        // List again the requests made.
//        response.sendRedirect("LIST");

    }

    // LIST ALL HOLIDAY REQUESTS
    private void listHolidayRequests(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<HolidayRequest> holidayRequestList = holidayRequestDao.selectAllHolidayRequests();
        request.setAttribute("HolidaysRequestsList", holidayRequestList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("holidayRequestsList.jsp");
        dispatcher.forward(request, response);
    }

}
