import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;

public class Request {

    private String bodyPath;
    private String endPoint;
    private String body;

    public Request(String bodyPath, String endPoint) {
        this.bodyPath = bodyPath;
        this.endPoint = endPoint;
    }

    //будет считываться шаблон сообщения.
    public void init() {
        body = XMLReader.read(bodyPath);

    }

    public String action() {
        int random_number = 100 + (int) (Math.random() * 999);
        Client cl1 = new Client(random_number);
        int number = cl1.transfer;
        String b = String.valueOf(number);
        body = body.replace("TANSFER", b);

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
            isr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputString;
    }
    public void end(){
    }
}