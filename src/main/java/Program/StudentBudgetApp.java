/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Program;

import Database.DatabaseManager;
import Expenses.*;
import Screens.*;
import javax.swing.JFrame;

/**
 *
 * @author will & nicolas
 *
 * Main application class to run the Student Budget Calculator program. This
 * program allows users to manage their budgets by adding expenses, calculating
 * the remaining budget, and saving or loading expenses to/from a file.
 */
public class StudentBudgetApp {

    public static void main(String[] args) {
        // Create BudgetManager to manage expenses
        BudgetManager budgetManager = new BudgetManager(new DBExpenseStorage(new DatabaseManager()));

        // Create JFrame for the application window
        JFrame frame = new JFrame("Student Budget Calculator");
        BudgetScreenGUI budgetScreen = new BudgetScreenGUI(budgetManager);

        // Set frame properties
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(budgetScreen); // Add the BudgetScreenGUI to the frame
        frame.setSize(1000, 600); // Set the frame size
        frame.setVisible(true); // Make the frame visible
    }
}