package Screens;

import javax.swing.*;
import java.awt.*;
import Expenses.BudgetManager;
import Expenses.FinancialRecord;

/**
 * 
 * @author will
 * 
 * Displays a list of the expenses
 */

public class ExpenseListPanel extends JPanel {

    // Fields for expense list, total label, and BudgetManager reference
    private final DefaultListModel<String> listModel;
    private final JList<String> expenseList;
    private final JLabel totalLabel;
    private final BudgetManager budgetManager;

    // Constructor for initializing the panel with the expense list and total amount
    public ExpenseListPanel(BudgetManager budgetManager) {
        this.budgetManager = budgetManager;
        setLayout(new BorderLayout(10, 10)); // Set layout to BorderLayout with gaps
        setBackground(UIConstants.BACKGROUND_COLOR); // Set background color
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding around the panel

        // Initialize list model and set custom cell renderer
        listModel = new DefaultListModel<>();
        expenseList = new JList<>(listModel);
        expenseList.setCellRenderer(new ExpenseListCellRenderer());
        expenseList.setFixedCellHeight(50); // Set fixed height for each list item

        // Initialize the total label
        totalLabel = new JLabel("Total: $0.00");
        totalLabel.setFont(UIConstants.TOTAL_FONT); // Apply custom font
        totalLabel.setForeground(UIConstants.TEXT_COLOR); // Apply text color
        totalLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0)); // Add padding above and below label

        // Create a header panel containing a title and a clear button
        JPanel headerPanel = new JPanel(new BorderLayout(10, 0));
        headerPanel.setBackground(UIConstants.BACKGROUND_COLOR);

        JLabel headerLabel = new JLabel("Expense History", JLabel.LEFT); // Title for the list
        headerLabel.setFont(UIConstants.LABEL_FONT); // Set font for the header
        headerLabel.setForeground(UIConstants.TEXT_COLOR); // Set color for the header

        JButton clearButton = createClearButton(); // Create the 'Clear All' button

        headerPanel.add(headerLabel, BorderLayout.WEST); // Add title to the left
        headerPanel.add(clearButton, BorderLayout.EAST); // Add the clear button to the right

        // Add the components to the main panel
        JScrollPane scrollPane = new JScrollPane(expenseList); // Add scroll pane for the list
        scrollPane.setBorder(BorderFactory.createLineBorder(UIConstants.BORDER_COLOR)); // Style the scroll pane border

        add(headerPanel, BorderLayout.NORTH); // Add the header panel to the top
        add(scrollPane, BorderLayout.CENTER); // Add the list to the center
        add(totalLabel, BorderLayout.SOUTH); // Add the total label to the bottom

        refreshList(); // Refresh the list to display the initial data
    }

    // Creates a styled 'Clear All' button with hover effects and confirmation dialog
    private JButton createClearButton() {
        JButton clearButton = new JButton("Clear All");
        clearButton.setFont(UIConstants.BUTTON_FONT); // Set button font
        clearButton.setForeground(Color.BLACK); // Set button text color
        clearButton.setBackground(new Color(220, 53, 69)); // Set button background
        clearButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15)); // Add padding inside button
        clearButton.setFocusPainted(false); // Disable focus paint
        clearButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Set hand cursor for hover

        // Add hover effect for changing background color
        clearButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                clearButton.setBackground(new Color(200, 35, 51)); // Darker red on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                clearButton.setBackground(new Color(220, 53, 69)); // Restore original red
            }
        });

        // Attach action listener to the button to clear all expenses
        clearButton.addActionListener(e -> clearAllExpenses());
        return clearButton;
    }
    // Clears all expenses after user confirmation and refreshes the list

    private void clearAllExpenses() {
        // Show a confirmation dialog before clearing all expenses
        int result = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to clear all expenses? This action cannot be undone.",
                "Confirm Clear All",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (result == JOptionPane.YES_OPTION) {  // If user confirms
            budgetManager.clearExpenses();  // Clear all expenses from BudgetManager
            refreshList();  // Refresh the list to update the UI
            JOptionPane.showMessageDialog( // Show success message
                    this,
                    "All expenses have been cleared.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    // Refreshes the list of expenses and updates the total amount displayed
    public void refreshList() {
        listModel.clear();  // Clear the current list
        double total = 0;

        // Loop through expenses in BudgetManager and add them to the list model
        for (FinancialRecord expense : budgetManager.getExpenses()) {
            listModel.addElement(String.format("%s: $%.2f",
                    expense.getName(),
                    expense.getAmount()));  // Add each expense to the list
            total += expense.getAmount();  // Calculate the total expenses
        }

        totalLabel.setText(String.format("Total Expenses: $%.2f", total));  // Update total label
    }

    // Custom cell renderer for the expense list to customize the appearance of each list item
    private class ExpenseListCellRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(
                JList<?> list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {

            // Call the parent method to get default label component
            JLabel label = (JLabel) super.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus);

            label.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));  // Add padding inside the label
            label.setFont(UIConstants.LIST_FONT);  // Set font for list items

            // Change background and foreground based on selection status
            if (isSelected) {
                label.setBackground(UIConstants.PRIMARY_COLOR);  // Set background color for selected item
                label.setForeground(Color.WHITE);  // Set text color for selected item
            } else {
                label.setBackground(Color.WHITE);  // Set background color for unselected items
                label.setForeground(UIConstants.TEXT_COLOR);  // Set text color for unselected items
            }
            return label;
        }
    }
}
