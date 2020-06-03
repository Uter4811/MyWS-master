import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;

public class Request {
    public static void main(String[] args) {
        Request request = new Request("C:\\Users\\Антон\\IdeaProjects\\TestData.xml" ,"https://www.dataaccess.com/webservicesserver/NumberConversion.wso");
        request.init();
        request.action();
    }
    private String bodyPath;
    private String endPoint;
    private String body;

    public Request(String bodyPath, String endPoint) {
        this.bodyPath = bodyPath;
        this.endPoint = endPoint;
    }

    //будет считываться шаблон сообщения.
    public void init() {
        body = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap12:Envelope xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                "  <soap12:Body>\n" +
                "    <NumberToWords xmlns=\"http://www.dataaccess.com/webservicesserver/\">\n" +
                "      <ubiNum>"+c+"</ubiNum>\n" +
                "    </NumberToWords>\n" +
                "  </soap12:Body>\n" +
                "</soap12:Envelope>";
    }
    public void action() {
        Client cl1  = new Client(999);
        int number = cl1.transfer;

        String responseString = "";
        String outputString = "";
        System.out.println("Отправляем следующее сообщение");
        System.out.println(body);
        HttpURLConnection httpConn = null;
        try {
            httpConn = (HttpURLConnection) new URL(endPoint).openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] buffer = body.getBytes();
        httpConn.setRequestProperty("Content-Length", String.valueOf(buffer.length));
        httpConn.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8");
        try {
            httpConn.setRequestMethod("POST");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        httpConn.setDoOutput(true);
        httpConn.setDoInput(true);
        OutputStream out = null;
        try {
            out = httpConn.getOutputStream();
            out.write(buffer);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(httpConn.getInputStream(), Charset.forName("UTF-8"));
            BufferedReader in = new BufferedReader(isr);
            while ((responseString = in.readLine()) != null) {
                outputString = outputString + responseString;
            }
            System.out.println("Получаем следующий ответ:");
            System.out.println(outputString);
            isr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void end() {

    }
}