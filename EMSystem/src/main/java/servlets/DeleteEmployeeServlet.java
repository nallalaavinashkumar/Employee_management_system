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

@WebServlet("/deleteEmployee")
public class DeleteEmployeeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String managerEmail = (String) request.getSession().getAttribute("user");
        String email = request.getParameter("email");

        try {
            Connection connection = null;
            try {
                connection = DBConnection.getConnection();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            String query = "DELETE e.* FROM employees e JOIN managers m ON e.manager_id = m.id WHERE e.email = ? AND m.email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, managerEmail);

            preparedStatement.executeUpdate();

            response.sendRedirect("managerDashboard.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
