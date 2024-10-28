/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Program;

import Budget.BudgetManager;
import Enums.Mode;
import Screens.*;

/**
 *
 * @author will & nicolas
 *
 * Main application class to run the Budget Tracker program. This
 * program allows users to manage their budgets by adding expenses, calculating
 * the remaining budget, and saving or loading expenses to/from a file.
 */
public class BudgetApp {

    public static void main(String[] args) {
        // Create BudgetManager to manage expenses
        BudgetManager budgetManager = new BudgetManager(Mode.PRODUCTION);

        // For the sake of logging the state of the database on startup
        ConsoleLogger cl = new ConsoleLogger(budgetManager);
        cl.log();

        // Create MenuScreenGUI and pass the budget manager
        MenuPanel menuScreen = new MenuPanel(budgetManager);

        // Show the MenuScreenGUI
        menuScreen.setVisible(true);
    }
}
