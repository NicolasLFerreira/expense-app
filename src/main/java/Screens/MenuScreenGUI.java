/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Screens;

import Database.DatabaseManager;
import javax.swing.*;
import java.awt.*;
import Expenses.BudgetManager;
import Expenses.DBExpenseStorage;

/**
 * @author williamblake, edited nicolas
 * Main application window for the Student Budget Calculator.
 * This class manages the main interface, switching between different screens using a CardLayout.
 */
public class MenuScreenGUI extends JFrame {
    private final CardLayout cardLayout;  // CardLayout to switch between different panels
    private final JPanel mainPanel;       // Main panel containing all screens
    private final BudgetManager budgetManager;  // Manages expenses in the application
    private final BudgetScreenGUI budgetScreen; // Panel for managing budget and expenses
    private final HelpScreenGUI helpScreen;     // Panel displaying the help information

    /**
     * Constructor that initializes the main application window.
     */
    public MenuScreenGUI() {
        super("Student Budget Calculatorssss");  // Set title of the window
        setSize(600, 400);  // Set the size of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Close the application when the window is closed
        setLocationRelativeTo(null);  // Center the window on the screen

        budgetManager = new BudgetManager(new DBExpenseStorage(new DatabaseManager()));  // Initialize the BudgetManager for managing expenses
        cardLayout = new CardLayout();  // Initialize CardLayout to switch between different panels
        mainPanel = new JPanel(cardLayout);  // Create the main panel that uses CardLayout

        // Initialize screens
        budgetScreen = new BudgetScreenGUI(budgetManager);  // Budget screen for adding and viewing expenses
        helpScreen = new HelpScreenGUI();  // Help screen with user instructions

        // Add screens to the card layout with their corresponding names
        mainPanel.add(budgetScreen, "Budget");
        mainPanel.add(helpScreen, "Help");

        // Setup the menu bar for navigation between screens
        setupMenuBar();

        // Add the main panel to the frame (window)
        add(mainPanel);
    }

    /**
     * Sets up the menu bar with options to switch between Budget and Help screens.
     */
    private void setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();  // Create a new menu bar

        // Create File and View menus
        JMenu fileMenu = new JMenu("File");
        JMenu viewMenu = new JMenu("View");

        // Create menu items for the "View" menu to switch between screens
        JMenuItem budgetItem = new JMenuItem("Budget");
        budgetItem.addActionListener(e -> cardLayout.show(mainPanel, "Budget"));  // Switch to Budget screen

        JMenuItem helpItem = new JMenuItem("Help");
        helpItem.addActionListener(e -> cardLayout.show(mainPanel, "Help"));  // Switch to Help screen

        // Create an exit option in the "File" menu
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));  // Exit the application

        // Add menu items to their respective menus
        fileMenu.add(exitItem);  // Add exit option to the File menu
        viewMenu.add(budgetItem);  // Add option to view the Budget screen
        viewMenu.add(helpItem);  // Add option to view the Help screen

        // Add the menus to the menu bar
        menuBar.add(fileMenu);  // Add File menu to the menu bar
        menuBar.add(viewMenu);  // Add View menu to the menu bar

        // Set the menu bar for this frame
        setJMenuBar(menuBar);
    }
}
