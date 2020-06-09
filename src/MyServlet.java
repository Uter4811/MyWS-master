/*
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
        String requiredString = request.toString();
        String requiredString2 = requiredString.substring(requiredString.indexOf("<ubiNum>") + 2, requiredString.indexOf("</ubiNum>"));
        int num = Integer.parseInt(requiredString2);

        response.setContentType ("text/html; charset=UTF-8");
        PrintWriter pw = response.getWriter();

        String s = "";
        if (num > 10){
            s = "ok";
        }else s = "fail";
        String line = String.format("<status> %s </status>", s);
        String s1 = response.toString();
        System.out.println(s1);
        pw.println(line);


    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String requiredString = request.toString();
        String requiredString2 = requiredString.substring(requiredString.indexOf("<ubiNum>") + 2, requiredString.indexOf("</ubiNum>"));
        int num = Integer.parseInt(requiredString2);



        response.setContentType ("text/html; charset=UTF-8");
        PrintWriter pw = response.getWriter();

        String s = "";
        if (num > 10){
            s = "ok";
        }else s = "fail";
        String line = String.format("<status> %s </status>", s);
        String s1 = response.toString();
        System.out.println(s1);
        pw.println(line);

    }

}*/
