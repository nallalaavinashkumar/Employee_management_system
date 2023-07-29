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

@WebServlet("/clockin")
public class ClockInServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = (String) request.getSession().getAttribute("user");

        try {
            Connection connection = null;
            try {
                connection = DBConnection.getConnection();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            String query = "INSERT INTO employee_times (employee_id, clock_in) SELECT id, NOW() FROM employees WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);

            preparedStatement.executeUpdate();

            //response.sendRedirect("dashboard.jsp");
            response.sendRedirect("employeeDashboard");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
