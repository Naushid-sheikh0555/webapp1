import jakarta.servlet.http.*;
import java.io.PrintWriter;

public class CookieDemo extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        try {
            PrintWriter out = res.getWriter();
            res.setContentType("text/html");
            Cookie[] cookies = req.getCookies();
            out.println(cookies.length);
            if(cookies.length == 0) {
                Cookie countCookie = new Cookie("count", "1");
                res.addCookie(countCookie);
                out.println("<h1>You have visited this page first time</h1>");
            } else {
                boolean flag = true;
                for(Cookie c: cookies) {
                    if(c.getName().equals("count")) {
                        flag = false;
                        String val = c.getValue();
                        c.setValue((Integer.parseInt(val) + 1) + "");
                        res.addCookie(c);
                        out.println("You have visited this page " + c.getValue() + " times");
                    }
                }
                if(flag) {
                    Cookie countCookie = new Cookie("count", "1");
                    res.addCookie(countCookie);
                    out.println("<h1>You have visited this page first time</h1>");
                }
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) {

    }
}
