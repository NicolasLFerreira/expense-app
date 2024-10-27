package Screens;

import Enums.FinancialRecordType;
import Expenses.BudgetManager;
import javax.swing.*;
import java.awt.*;

/**
 * 
 * @author will, edited by nicolas
 * Main application window for the Student Budget Calculator. This class manages
 * the main interface using JTabbedPane for navigation.
 */
public class MenuPanel extends JFrame {

    private final DashboardPanel dashboardPanel; // Panel for managing budget and expenses
    private final HelpPanel helpPanel;     // Panel displaying the help information
    private final EntryPanel incomeEntryPanel;       // Panel for entering income
    private final EntryPanel expenseEntryPanel; // Panel for entering expenses
    private final ExpenseListPanel expenseListPanel;   // Panel for displaying the list of expenses

    /**
     * Constructor that initializes the main application window.
     * @param budgetManager
     */
    public MenuPanel(BudgetManager budgetManager) {
        super("Student Budget Calculator");  // Set title of the window
        setSize(800, 600);  // Set the size of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Close the application when the window is closed
        setLocationRelativeTo(null);  // Center the window on the screen

        // Display screens
        dashboardPanel = new DashboardPanel(budgetManager);  // Budget screen for adding and viewing expenses
        helpPanel = new HelpPanel();  // Help screen with user instructions
        expenseListPanel = new ExpenseListPanel(budgetManager);   // Panel for displaying expenses

        // object used for updating other panels
        UpdateTrigger updateTrigger = new UpdateTrigger(dashboardPanel, expenseListPanel);

        // Instances of EntryPanel configured with FinancialRecordType
        expenseEntryPanel = new EntryPanel(budgetManager, updateTrigger, FinancialRecordType.EXPENSE); // Panel for entering expenses
        incomeEntryPanel = new EntryPanel(budgetManager, updateTrigger, FinancialRecordType.INCOME); // New panel for entering income

        // Create JTabbedPane for navigation
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Dashboard", dashboardPanel);
        tabbedPane.addTab("Income Entry", incomeEntryPanel);
        tabbedPane.addTab("Expense Entry", expenseEntryPanel);
        tabbedPane.addTab("Expense List", expenseListPanel);
        tabbedPane.addTab("Help", helpPanel);

        // Add the tabbed pane to the frame (window)
        add(tabbedPane);
    }

    public void updateDashboard() {
        dashboardPanel.revalidate();
        dashboardPanel.repaint();
    }
}
