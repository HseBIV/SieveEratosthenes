package edu.BIV123.SieveEratosthenes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ConsoleHelper {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String readString()
    {
        String message = "";
        try {
            message = reader.readLine();
        }
        catch (IOException ignored) {
        }
        return message;
    }

    public static void writeMessage(String message)
    {
        System.out.println(message);
    }

    public static void writeNumbers(List<Integer> numbers) {
        System.out.println(numbers.toString());
    }

    public static int askNumberN(){
        writeMessage("Input N: ");
        while (true)
        {
            int number;
            try {
                return number = Integer.parseInt(readString());
            }
            catch (Exception e) {
                writeMessage("Input N(integer): ");
            }

        }
    }
}
