import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DeleteStudent extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Connection connection = DbConnection.getConnection();
            String sql = "DELETE FROM student WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

            res.sendRedirect("/Webapp1/student");
        } catch (Exception e) {

        }
    }
}
