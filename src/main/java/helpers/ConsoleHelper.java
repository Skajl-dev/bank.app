package helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        String text = reader.readLine();
        return text;
    }

    public static int readInt() {
        int number = 0;
        try {
            number = Integer.parseInt(readString());
        } catch (IOException e) {
            System.out.println("Please enter the number correctly: ");
            readInt();
        }
        return number;
    }

    public static double readDouble() {
        double number = 0;
        try {
            number = Double.parseDouble(readString());
        } catch (IOException e) {
            System.out.println("Please enter the number correctly: ");
            readDouble();
        }
        return number;
    }


}
