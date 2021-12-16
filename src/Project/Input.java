package Project;

import java.util.Scanner;

public class Input {
    private static Scanner readInput = new Scanner(System.in);

    public static String GetString() {
        return ReadInput();
    }

    public static int GetInt() {
        String line = ReadInput();
        int number = -1;
        try {
            number = Integer.parseInt(line);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return number;
    }

    public static double GetDouble() {
        String line = ReadInput();
        double number = -1.0;
        try {
            number = Double.parseDouble(line);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return number;
    }

    private static String ReadInput() {
        String line = "";
        try {
            line = readInput.nextLine();
        } catch(Exception exception) {
            System.out.println(exception.getMessage());
        }
        return line;
    }
}
