import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GanymedeServlet", urlPatterns = "/ganymede")
public class GanymedeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Hello Ganymede!</h1><hr> <p>yo ganymede,</p><p> you're ok,</p><p> you got this capstone,</p><p> let's do this today!</p>");
    }
}
