package Screens;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import Expenses.BudgetManager;

// BudgetScreenGUI class defines the graphical user interface (GUI) for managing the student budget application.
public class BudgetScreenGUI extends JPanel {
    
    // Color constants to define the visual theme of the GUI.
    private static final Color PRIMARY_COLOR = new Color(51, 122, 183);        // Blue theme color.
    private static final Color BACKGROUND_COLOR = new Color(245, 245, 245);    // Light background color.
    private static final Color BUTTON_HOVER_COLOR = new Color(40, 96, 144);    // Darker blue for hover effect.

    // Font constants to standardize the appearance of labels, buttons, and titles.
    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 24);  // Font for the main title.
    private static final Font LABEL_FONT = new Font("Segoe UI", Font.PLAIN, 14); // Font for general labels.
    private static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 14); // Font for button text.

    // Class members (instance variables) representing key components of the GUI.
    private final BudgetManager budgetManager;         // Manages the budget-related operations.
    private final ExpenseEntryPanel entryPanel;        // Panel for adding new expenses.
    private final ExpenseListPanel listPanel;          // Panel for viewing the list of expenses.
    private final CardLayout cardLayout;               // CardLayout for switching between views.
    private final JPanel cardPanel;                    // Main container for holding the panels controlled by the CardLayout.

    // Constructor: initializes the GUI with BudgetManager and sets up the layout and components.
    public BudgetScreenGUI(BudgetManager budgetManager) {
        this.budgetManager = budgetManager;  // Assign the provided BudgetManager to the instance.
        
        // Set layout and styling for the main panel.
        setLayout(new BorderLayout(10, 10));  // BorderLayout with 10px padding.
        setBackground(BACKGROUND_COLOR);      // Set the background color.
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));  // Empty border for padding around the panel.

        // Create CardLayout to enable view switching between different panels (Expense Entry and Expense List).
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);  // Main container panel using CardLayout.
        cardPanel.setBackground(BACKGROUND_COLOR);

        // Initialize the entry and list panels, passing in the budgetManager for expense handling.
        entryPanel = new ExpenseEntryPanel(budgetManager);
        listPanel = new ExpenseListPanel(budgetManager);

        // Add the two panels (entryPanel and listPanel) to the cardPanel, associated with unique identifiers ("Entry" and "List").
        cardPanel.add(entryPanel, "Entry");  // Add entry panel for adding new expenses.
        cardPanel.add(listPanel, "List");    // Add list panel for viewing expenses.

        // Create a navigation panel (sidebar) with buttons for switching between views.
        JPanel navPanel = createNavigationPanel();

        // Add components to the main layout.
        add(createHeaderPanel(), BorderLayout.NORTH);  // Add header at the top of the layout.
        add(navPanel, BorderLayout.WEST);              // Add navigation panel to the left.
        add(cardPanel, BorderLayout.CENTER);           // Add card panel to the center for dynamic content switching.

        // Set the default view to the "Entry" panel (where users can add new expenses).
        cardLayout.show(cardPanel, "Entry");
    }

    // Creates the header panel at the top of the screen, containing the title.
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());  // Header panel using BorderLayout for title alignment.
        headerPanel.setBackground(BACKGROUND_COLOR);          // Set background color.
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));  // Bottom padding for spacing.

        JLabel titleLabel = new JLabel("Student Budget Calculator");  // Create title label.
        titleLabel.setFont(TITLE_FONT);                              // Set title font.
        titleLabel.setForeground(PRIMARY_COLOR);                     // Set title color to the primary theme color.
        headerPanel.add(titleLabel, BorderLayout.CENTER);            // Add title label to the center of the header.

        return headerPanel;
    }

    // Creates the navigation panel on the left side of the screen with buttons to switch between views.
    private JPanel createNavigationPanel() {
        JPanel navPanel = new JPanel(new GridLayout(2, 1, 0, 10));  // Navigation panel with two buttons arranged vertically.
        navPanel.setBackground(BACKGROUND_COLOR);                   // Set background color.
        navPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));  // Right-side padding for button spacing.

        // Create buttons for adding expenses and viewing the list of expenses.
        JButton addButton = createStyledButton("Add Expense", "Entry");    // Button to switch to the "Entry" panel.
        JButton viewButton = createStyledButton("View Expenses", "List");  // Button to switch to the "List" panel.

        // Add buttons to the navigation panel.
        navPanel.add(addButton);
        navPanel.add(viewButton);

        return navPanel;
    }

    // Creates and styles a button, with text and an action to switch the card layout when clicked.
    private JButton createStyledButton(String text, String cardName) {
        JButton button = new JButton(text);  // Create a new button with the given text.
        button.setFont(BUTTON_FONT);         // Set the button's font.
        button.setForeground(Color.BLACK);   // Set the text color to black.
        button.setBackground(PRIMARY_COLOR); // Set the button's background color.
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));  // Add padding inside the button.
        button.setFocusPainted(false);       // Remove focus painting (highlight) on click.
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));  // Change the cursor to a hand pointer when hovering.

        // Add a hover effect to the button (change background color when hovered).
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(BUTTON_HOVER_COLOR);  // Set hover color when mouse enters.
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(PRIMARY_COLOR);  // Revert to the original color when mouse exits.
            }
        });

        // Add action listener to switch the view when the button is clicked.
        button.addActionListener(e -> {
            if (cardName.equals("List")) {   // If switching to the "List" view, refresh the listPanel.
                listPanel.refreshList();     // Update the displayed list of expenses.
            }
            cardLayout.show(cardPanel, cardName);  // Switch to the specified card (view).
        });

        return button;  // Return the styled button.
    }
}
