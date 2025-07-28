import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) {
        try {
            String userName = req.getParameter("username");
            String password = req.getParameter("password");

            String sql = "SELECT * FROM user WHERE username=? and password=?";
            Connection connection = DbConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, userName);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                HttpSession session = req.getSession();
                session.setAttribute("userId", rs.getInt(1));
                session.setAttribute("userName", rs.getString(3));
                res.sendRedirect("/Webapp1/student");
            } else {
                res.sendRedirect("/Webapp1/index.jsp");
            }



        } catch (Exception e) {

        }
    }
}
