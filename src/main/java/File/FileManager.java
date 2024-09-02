/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package File;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nicolas
 */
// Reads and writes a list of strings from/to a specified file.
// Methods are static since there's no need to mantain state between operations.
public class FileManager {

    public static final String EXPENSE_PATH = "Expenses.txt";
    public static final String INCOME_PATH = "Income.txt";

    // Ensures the existance of these two files.
    public static final void ensure() {
        File[] files = new File[]{new File(EXPENSE_PATH), new File(INCOME_PATH)};

        for (File file : files) {
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
            } catch (IOException e) {
            }
        }
    }

    // Reads everything from the specified file.
    public static final List<String> loadExpenses() {
        BufferedReader reader;

        List<String> lineBuffer = new ArrayList<>();
        String line;

        try {
            reader = new BufferedReader(new FileReader(EXPENSE_PATH));

            while ((line = reader.readLine()) != null) {
                lineBuffer.add(line);
            }

            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lineBuffer;
    }

    // Appends text to the file.
    public static final void saveExpenses(List<String> text) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(EXPENSE_PATH, false));
            for (String line : text) {
                writer.write(line + '\n');
            }
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static final double loadIncome() {
        BufferedReader reader;
        String text = "0";
        double amount = 0;

        try {
            reader = new BufferedReader(new FileReader(INCOME_PATH));
            text = reader.readLine();
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (text == null || text.equals("")) {
            return 0;
        }

        try {
            amount = Double.parseDouble(text);
        } catch (NumberFormatException e) {

        }

        return amount;
    }

    public static final void saveIncome(double amount) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(INCOME_PATH, false));
            writer.write(Double.toString(amount));
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
