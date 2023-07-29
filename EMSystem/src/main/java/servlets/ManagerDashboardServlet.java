package servlets;

import utils.DBConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/managerDashboard")
public class ManagerDashboardServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String managerEmail = (String) request.getSession().getAttribute("user");
        ArrayList<Map<String, Object>> leaveApplications = new ArrayList<>();

        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT la.id, e.name as employee_name, la.from_date, la.to_date, la.status FROM leave_applications la " +
                    "JOIN employees e ON la.employee_id = e.id " +
                    "JOIN managers m ON e.manager_id = m.id WHERE m.email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, managerEmail);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Map<String, Object> application = new HashMap<>();
                application.put("id", rs.getInt("id"));
                application.put("employeeName", rs.getString("employee_name"));
                application.put("fromDate", rs.getDate("from_date"));
                application.put("toDate", rs.getDate("to_date"));
                application.put("status", rs.getString("status"));
                leaveApplications.add(application);
            }

            request.setAttribute("leaveApplications", leaveApplications);

            RequestDispatcher dispatcher = request.getRequestDispatcher("managerDashboard.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
