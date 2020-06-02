import java.util.Random;

public class Auxiliary {

    public static String returnName(int id) {
        int firstDigit = Integer.parseInt(Integer.toString(id).substring(0, 1));
        int transferForName = 4 + firstDigit;
        int leftLimit = 1067;
        int rightLimit = 1100;
        int targetStringLength = 10;
        Random random = new Random();
        String generatedString = null;

        for (int x = 1; x <= 100; x++) {
            StringBuilder buffer = new StringBuilder(targetStringLength);
            for (int i = 0; i < transferForName; i++) {
                int randomLimitedInt = leftLimit+ i + transferForName;
                buffer.append((char) randomLimitedInt);
            }
            generatedString = buffer.toString();

        }
        return generatedString;
    }


    public static int forZp(int id) {
        String h = String.valueOf(id);
        int r = (int)h.charAt(0);
        int z = 0;
        if (r <= 1) {
            z = r + 500;
        } else if (r  > 1 && r  <= 3) {

            z = r + 300;
        }
        else if(r  > 3 ) {
            z = 300-r;
        }
        return z;
    }

    public static int forTrans(int id) {
        int z = 0;
        if (id % 100 > 40) {
            z = 1 + (int) (Math.random() * 20);
        }
        else  {
            z = 20 + (int) (Math.random()*30);
        }
        return z;
    }
    public static String forDate(int id) {
        int r = id;
        int z = 0;
        int y = 0;
        int x = 0;
        z = id%100 + 1980;
        x = id%12 + 1;
        y = id%30;
        String dateBirthday = new String(z + "." + x + "." + y);
        return dateBirthday;
    }
}