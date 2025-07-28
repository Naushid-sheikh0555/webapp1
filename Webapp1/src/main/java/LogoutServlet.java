import jakarta.servlet.http.*;

public class LogoutServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        try {
            HttpSession session = req.getSession();
            session.setAttribute("userId", null);
            session.setAttribute("userName", null);
            session.invalidate();
            Cookie[] cookies = req.getCookies();
            for (Cookie c: cookies) {
                if(c.getName().equals("JSESSIONID")) {
                    c.setMaxAge(0);
                    res.addCookie(c);
                }
            }
            res.sendRedirect("/Webapp1/index.jsp");
        } catch (Exception e) {

        }
    }
}
