package servlets;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import utils.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/employeeDashboard")
public class EmployeeDashboardServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employeeEmail = (String) request.getSession().getAttribute("user");

        try {
            Connection connection = DBConnection.getConnection();
            String query = "SELECT name, email, role, salary FROM employees WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employeeEmail);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Map<String, Object> employee = new HashMap<>();
                employee.put("name", rs.getString("name"));
                employee.put("email", rs.getString("email"));
                employee.put("role", rs.getString("role"));
                employee.put("salary", rs.getBigDecimal("salary"));

                request.setAttribute("employee", employee);
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

