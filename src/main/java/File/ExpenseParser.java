/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package File;

import Expenses.Expense;
import Expenses.Storage;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author nicolas
 */
// Parses and serialises data. 
// Also static like the FileManager for same reasons.
public class ExpenseParser {

    // parses the serialised file to a hashmap
    public static Expense[] parse(List<String> lines) {
        String name;
        double value;

        List<Expense> expenses = new ArrayList<>();
        StringTokenizer st;

        for (String line : lines) {
            // Sets tokenizer to current line
            st = new StringTokenizer(line);
            // gets item 0
            name = st.nextToken();

            // Tries parsing the double vvalue, ignores the current line if not.
            try {
                value = Double.parseDouble(st.nextToken());
            } catch (Exception e) {
                continue;
            }

            // Saves to the hashmap
            expenses.add(new Expense(name, value));
        }

        return expenses.toArray(new Expense[expenses.size()]);
    }

    // Serialises the hashmap to the data format of the file
    public static List<String> serialise(Storage expenses) {
        List<String> lines = new ArrayList<>();

        for (Expense expense : expenses.getArray()) {
            lines.add(expense.toString());
        }

        return lines;
    }
}
