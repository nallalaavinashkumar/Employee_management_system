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

@WebServlet("/applyLeave")
public class LeaveApplicationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = (String) request.getSession().getAttribute("user");
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");

        try {
            Connection connection = null;
            try {
                connection = DBConnection.getConnection();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            String query = "INSERT INTO leave_applications (employee_id, from_date, to_date) SELECT id, ?, ? FROM employees WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, fromDate);
            preparedStatement.setString(2, toDate);
            preparedStatement.setString(3, email);

            preparedStatement.executeUpdate();

            response.sendRedirect("dashboard.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
