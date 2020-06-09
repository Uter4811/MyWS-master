import jdk.internal.org.xml.sax.InputSource;
import org.w3c.dom.Node;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;

public class Request {

    /*public static void main(String[] args) {


    }*/

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
       /* body  = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap12:Envelope xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                "  <soap12:Body>\n" +
                "    <NumberToWords xmlns=\"http://www.dataaccess.com/webservicesserver/\">\n" +
                "      <ubiNum>54</ubiNum>\n" +
                "    </NumberToWords>\n" +
                "  </soap12:Body>\n" +
                "</soap12:Envelope>\n";*/

    }

    public void action() {
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
        httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
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

       // System.out.println(Request.parseXmlFile(Request.formatXML(outputString)));
        System.out.println(outputString);
    }
    public void end(){
    }
    public static String formatXML(String unformattedXml) {
        try {
            Document document = parseXmlFile(unformattedXml);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", 3);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource((Node) document);
            StreamResult xmlOutput = new StreamResult(new StringWriter());
            transformer.transform(source, xmlOutput);
            return xmlOutput.getWriter().toString();
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    // parse XML
    public static Document parseXmlFile(String in) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(in));
            return (Document) db.parse(String.valueOf(is));
        } catch (IOException | ParserConfigurationException | org.xml.sax.SAXException e) {
            throw new RuntimeException(e);
        }

    }
}