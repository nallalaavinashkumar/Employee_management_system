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

@WebServlet("/approveLeave")
public class ApproveLeaveServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String applicationId = request.getParameter("applicationId");

        try {
            Connection connection = DBConnection.getConnection();
            String query = "UPDATE leave_applications SET status = 'APPROVED' WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, applicationId);

            preparedStatement.executeUpdate();

            response.sendRedirect("managerDashboard");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
