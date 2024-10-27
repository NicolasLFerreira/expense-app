package Screens;

import Expenses.BudgetManager;
import javax.swing.*;
import java.awt.*;

/**
 * Main application window for the Student Budget Calculator. This class manages
 * the main interface using JTabbedPane for navigation.
 */
public class MenuScreenGUI extends JFrame {

    private final BudgetManager budgetManager;  // Manages expenses in the application
    private final DashboardPanel dashBoardPanel; // Panel for managing budget and expenses
    private final HelpScreenGUI helpScreenPanel;     // Panel displaying the help information
    private final IncomePanel incomeEntryPanel;       // Panel for entering income
    private final ExpenseEntryPanel expenseEntryPanel; // Panel for entering expenses
    private final ExpenseListPanel expenseListPanel;   // Panel for displaying the list of expenses
    private final MenuScreenGUI menuScreen = null;

    /**
     * Constructor that initializes the main application window.
     */
    public MenuScreenGUI(BudgetManager budgetManager) {
        super("Student Budget Calculator");  // Set title of the window
        setSize(800, 600);  // Set the size of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Close the application when the window is closed
        setLocationRelativeTo(null);  // Center the window on the screen

        this.budgetManager = budgetManager;  // Use the provided BudgetManager

        // Display screens
        dashBoardPanel = new DashboardPanel(budgetManager);  // Budget screen for adding and viewing expenses
        helpScreenPanel = new HelpScreenGUI();  // Help screen with user instructions
        expenseListPanel = new ExpenseListPanel(budgetManager);   // Panel for displaying expenses

        // object used for updating other panels
        UpdateTrigger updateTrigger = new UpdateTrigger(dashBoardPanel, expenseListPanel);

        // Input screens// New panel for entering income
        expenseEntryPanel = new ExpenseEntryPanel(budgetManager, updateTrigger); // Panel for entering expenses
        incomeEntryPanel = new IncomePanel(budgetManager, updateTrigger); // New panel for entering income

        // Create JTabbedPane for navigation
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Dashboard", dashBoardPanel);
        tabbedPane.addTab("Income", incomeEntryPanel);
        tabbedPane.addTab("Expense Entry", expenseEntryPanel);
        tabbedPane.addTab("Expense List", expenseListPanel);
        tabbedPane.addTab("Help", helpScreenPanel);

        // Add the tabbed pane to the frame (window)
        add(tabbedPane);
    }

    public void updateDashboard() {
        dashBoardPanel.revalidate();
        dashBoardPanel.repaint();
    }
}
