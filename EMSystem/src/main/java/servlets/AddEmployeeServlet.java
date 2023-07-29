package servlets;

import utils.DBConnection;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/addEmployee")
public class AddEmployeeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String managerEmail = (String) request.getSession().getAttribute("user");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String role = request.getParameter("role");
        String salary = request.getParameter("salary");

        try {
            Connection connection = null;
            try {
                connection = DBConnection.getConnection();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            String query = "INSERT INTO employees (email, password, name, role, salary, manager_id) SELECT ?, ?, ?, ?, ?, id FROM managers WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, role);
            preparedStatement.setString(5, salary);
            preparedStatement.setString(6, managerEmail);

            preparedStatement.executeUpdate();

            response.sendRedirect("managerDashboard.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
