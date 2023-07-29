package servlets;

import utils.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    /*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {




        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email != null && password != null) {
            PrintWriter out = response.getWriter();

            try {
                Connection connection = DBConnection.getConnection();

                String query = "SELECT * FROM employees WHERE email = ? AND password = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    request.getSession().setAttribute("user", email);
                    response.setContentType("text/html");
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.sendRedirect("dashboard.jsp");
                } else {
                    response.setContentType("text/html");
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.sendRedirect("index.jsp");
                }
            }

            catch (Exception e) {
                out.println(e.getMessage());
                out.flush();
                e.printStackTrace();
            }
        }
    } */

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String userType = req.getParameter("userType");

        try {
            Connection connection = null;
            try {
                connection = DBConnection.getConnection();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            PreparedStatement preparedStatement;

            if ("employee".equals(userType)) {
                preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE email = ? AND password = ?");
            } else {
                preparedStatement = connection.prepareStatement("SELECT * FROM managers WHERE email = ? AND password = ?");
            }

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                req.getSession().setAttribute("user", email);
                req.getSession().setAttribute("userType", userType);

                if ("employee".equals(userType)) {
                    resp.sendRedirect("employeeDashboard");
                } else {
                    //resp.sendRedirect("managerDashboard.jsp");
                    resp.sendRedirect("managerDashboard");
                }
            } else {
                resp.sendRedirect("index.jsp");
            }

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

}
