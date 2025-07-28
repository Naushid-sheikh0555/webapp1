import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpdateStudent extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        try {
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            int id = Integer.parseInt(req.getParameter("id"));
            Connection connection = DbConnection.getConnection();
            String sql = "SELECT * FROM student WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String name = rs.getString(2);
            String course = rs.getString(3);
            int semester = rs.getInt(4);
            String division = rs.getString(5);

            out.println("<form action='/Webapp1/update_student' method='post'>");
            out.println("    <input type='text' name='name' placeholder='Enter Name' value='" + name + "'>");
            out.println("    <select name='course'>");
            out.println("        <option value='BCA' " + (course.equals("BCA")?"SELECTED":"") + " >BCA</option>");
            out.println("        <option value='BBA'  " + (course.equals("BBA")?"selected":"") + " >BBA</option>");
            out.println("        <option value='BCom' " + (course.equals("BCom")?"selected":"") + " >BCom</option>");
            out.println("    </select>");
            out.println("    <input type='text' name='semester' placeholder='Enter Semester' value='" + semester + "'>");
            out.println("    <input type='text' name='division' placeholder='Enter Division' value='" + division + "'>");
            out.println("    <input type='hidden' name='id' value='" + id + "' >");
            out.println("    <input type='submit' value='Add Student'>");
            out.println("</form>");

        } catch (Exception e) {

        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) {
        try {
            String name = req.getParameter("name");
            String course = req.getParameter("course");
            int semester = Integer.parseInt(req.getParameter("semester"));
            int id = Integer.parseInt(req.getParameter("id"));
            String division = req.getParameter("division");
            Connection connection = DbConnection.getConnection();
            String sql = "UPDATE student SET name=?,course=?,semester=?,division=? WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, course);
            ps.setInt(3, semester);
            ps.setString(4, division);
            ps.setInt(5, id);

            ps.executeUpdate();

            res.sendRedirect("/Webapp1/student");
        } catch (Exception e) {

        }
    }
}
