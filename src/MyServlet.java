import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(value = "/test")
public class MyServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Client client = new Client(999);
        response.setContentType ("text/html; charset=UTF-8");
        PrintWriter pw = response.getWriter();
        String s = "";
        if (client.transfer > 10){
            s = "ok";
        }else s = "fail";
        String line = String.format("<status> %s </status>", s);
        pw.write(line);


    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Client client = new Client(888);
        response.setContentType ("text/html; charset=UTF-8");
        PrintWriter pw = response.getWriter();
        String s = "";
        if (client.transfer > 10){
            s = "ok";
        }else s = "fail";
        String line = String.format("<status> %s </status>", s);
        pw.println(line);
    }

}