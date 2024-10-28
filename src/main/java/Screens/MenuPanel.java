package Screens;

import Enums.FinancialRecordType;
import Budget.BudgetManager;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author will, refactored by nicolas (added the update related code)
 *
 * Main application window for the Student Budget Calculator. This class manages
 * the main interface using JTabbedPane for navigation.
 */
public class MenuPanel extends JFrame {

    private final DashboardPanel dashboardPanel; // Panel for managing budget and expenses
    private final HelpPanel helpPanel;     // Panel displaying the help information
    private final EntryPanel incomeEntryPanel;       // Panel for entering income
    private final EntryPanel expenseEntryPanel; // Panel for entering expenses
    private final ListPanel expenseListPanel;   // Panel for displaying the list of expenses
    private final ListPanel incomeListPanel;   // Panel for displaying the list of income sources

    /**
     * Constructor that initializes the main application window.
     *
     * @param budgetManager
     */
    public MenuPanel(BudgetManager budgetManager) {
        super("Student Budget Calculator");  // Set title of the window
        setSize(800, 600);  // Set the size of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Close the application when the window is closed
        setLocationRelativeTo(null);  // Center the window on the screen

        // Update manager
        UpdateManager updateManager = new UpdateManager();

        // Dashboard panel
        dashboardPanel = new DashboardPanel(budgetManager);  // Budget screen for adding and viewing expenses

        // List panels
        expenseListPanel = new ListPanel(budgetManager, FinancialRecordType.EXPENSE, updateManager);
        incomeListPanel = new ListPanel(budgetManager, FinancialRecordType.INCOME, updateManager);

        // Entry panels
        expenseEntryPanel = new EntryPanel(budgetManager, FinancialRecordType.EXPENSE, updateManager); // Panel for entering expenses
        incomeEntryPanel = new EntryPanel(budgetManager, FinancialRecordType.INCOME, updateManager); // New panel for entering income

        // Help panel
        helpPanel = new HelpPanel();  // Help screen with user instructions

        // Configures update manager with the instances of the created panels
        updateManager.setPanels(new Updateable[]{dashboardPanel, expenseListPanel, incomeListPanel});

        // Create JTabbedPane for navigation
        JTabbedPane tabbedPane = new JTabbedPane();
        // Dashboard panel
        tabbedPane.addTab("Dashboard", dashboardPanel);
        // Entry panels
        tabbedPane.addTab("Income Entry", incomeEntryPanel);
        tabbedPane.addTab("Expense Entry", expenseEntryPanel);
        // List panels
        tabbedPane.addTab("Income List", incomeListPanel);
        tabbedPane.addTab("Expense List", expenseListPanel);
        // Help panel
        tabbedPane.addTab("Help", helpPanel);

        // Add the tabbed pane to the frame (window)
        add(tabbedPane);
    }
}
