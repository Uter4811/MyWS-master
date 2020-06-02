import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class XMLReader {
    public static String read(String bodyPath) {
        String dirname = "C:\\Users\\Антон\\IdeaProjects\\TestData.xml";
        File f1 = new File(dirname);
        if (f1.isDirectory()) {
            String s[] = f1.list();
            for (int i = 0; i < s.length; i++) {
                try {
                    FileReader fr1 = new FileReader(s[i]);
                    BufferedReader br1 = new BufferedReader(fr1);
                    try {
                        bodyPath = br1.readLine();
                        fr1.close();
                    } catch (IOException ex1) {
                        System.err.println("файл не открывается");
                        ex1.printStackTrace();
                    }
                } catch (FileNotFoundException ex) {
                    System.err.println("Файл не найден!!!");
                    ex.printStackTrace();
                }
            }
        } else {
            System.out.println("Каталога " + dirname + " не существует!");
        }
        return bodyPath;
    }
}

