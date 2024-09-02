/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Program;

import java.util.Scanner;

/**
 *
 * @author nicol
 *
 */
// Class containing static methods for ease of input capturing.
// I just tried covering most cases that may arise regarding capturing input,
// so some methods may be inutilised in the program.
public class Input {

    private final static Scanner scanner = new Scanner(System.in);

    // Double input capturing
    public static double captureDouble() {
        return captureDouble("");
    }

    public static double captureDouble(String message) {
        double value = 0;
        boolean valid = false;
        int count = 0;

        // Ignores the message if it's empty
        if (!message.equalsIgnoreCase("")) {
            System.out.println(message);
        }

        // Loops until a valid input is given
        while (!valid) {
            String input = scanner.nextLine();
            try {
                value = Double.parseDouble(input);
                valid = true;
            } catch (NumberFormatException e) {
                if (count > 0) {
                    System.out.println("Please enter a number:");
                }
                count++;
            }
        }

        return value;
    }

    // Integer input capturing
    public static int captureInt() {
        return captureInt("");
    }

    public static int captureInt(String message) {
        return (int) captureDouble(message);
    }

    // String input capturing
    public static String captureString() {
        return captureString("");
    }

    public static String captureString(String message) {

        // Ignores the message if it's empty
        if (!message.equalsIgnoreCase("")) {
            System.out.println(message);
        }

        System.out.print("");

        return scanner.next();
    }

    // String input capturing
    public static char captureChar() {
        return captureChar("");
    }

    public static char captureChar(String message) {

        // Ignores the message if it's empty
        if (!message.equalsIgnoreCase("")) {
            System.out.println(message);
        }

        String line = scanner.nextLine();
        if (line.length() > 0) {
            return line.charAt(0);
        }

        return '\0';
    }

    // For "press anything" type of situations.
    public static void throwAway() {
        throwAway("");
    }

    public static void throwAway(String message) {
        // Ignores the message if it's empty
        if (!message.equalsIgnoreCase("")) {
            System.out.println(message);
        }

        scanner.nextLine();
    }

    // For commands and whatnot, returns the lower case of input character.
    public static char captureCommand(String message) {
        return Character.toLowerCase(captureChar(message));
    }
}
