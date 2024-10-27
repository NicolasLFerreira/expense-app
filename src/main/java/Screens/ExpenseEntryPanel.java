package Screens;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import Expenses.BudgetManager;
import Expenses.FinancialRecord;

public class ExpenseEntryPanel extends JPanel {
    // Fields for user input and BudgetManager reference
    private final JTextField descriptionField; // Field to enter the description of the expense
    private final JTextField amountField; // Field to enter the amount of the expense
    private final BudgetManager budgetManager; // Reference to the BudgetManager class to manage expenses

    // Constructor for initializing the panel and its components
    public ExpenseEntryPanel(BudgetManager budgetManager) {
        this.budgetManager = budgetManager; // Assign the provided BudgetManager to the instance field
        setLayout(new GridBagLayout()); // Use GridBagLayout for flexible component positioning
        setBackground(UIConstants.BACKGROUND_COLOR); // Set background color from UI constants
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Set padding around the panel

        // Initialize the input fields and button
        descriptionField = createStyledTextField(); // Method to create a styled text field for the description
        amountField = createStyledTextField(); // Method to create a styled text field for the amount
        JButton submitButton = createStyledSubmitButton(); // Method to create a styled "Submit Expense" button

        // Configure the layout constraints for adding components
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5); // Set padding between components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make components fill the horizontal space
        gbc.anchor = GridBagConstraints.WEST; // Align components to the left

        // Add input fields to the panel with labels
        addFormRow("Description:", descriptionField, gbc, 0); // Add a row for the description field
        addFormRow("Amount ($):", amountField, gbc, 1); // Add a row for the amount field

        // Add the submit button below the input fields
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 2; // Span button across two columns
        gbc.anchor = GridBagConstraints.CENTER; // Center the button
        gbc.insets = new Insets(20, 5, 10, 5); // Add extra padding for the button
        add(submitButton, gbc); // Add the submit button to the panel

        // Add an empty space below the button to push components upwards
        gbc.gridy = 3;
        gbc.weighty = 1.0; // Allow vertical expansion
        add(Box.createVerticalGlue(), gbc); // Add vertical glue for spacing
    }

    // Creates a styled text field for user input
    private JTextField createStyledTextField() {
        JTextField field = new JTextField(20); // Text field with 20 columns width
        field.setFont(UIConstants.LABEL_FONT); // Set font for the text field
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UIConstants.BORDER_COLOR), // Set border with color
            BorderFactory.createEmptyBorder(8, 10, 8, 10) // Set padding inside the text field
        ));
        return field;
    }

    // Creates a styled "Submit Expense" button with hover effects
    private JButton createStyledSubmitButton() {
        JButton button = new JButton("Submit Expense"); // Button for submitting the expense
        button.setFont(UIConstants.BUTTON_FONT); // Set font for the button
        button.setForeground(Color.BLACK); // Set text color
        button.setBackground(UIConstants.SUCCESS_COLOR); // Set background color
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Set padding for the button
        button.setFocusPainted(false); // Remove focus painting
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor to hand when hovering

        // Add hover effect to change button color
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(UIConstants.SUCCESS_HOVER_COLOR); // Change color on hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(UIConstants.SUCCESS_COLOR); // Revert color when not hovering
            }
        });

        // Add action listener to handle the button click event
        button.addActionListener(e -> submitExpense()); // Call the submitExpense method on click
        return button;
    }

    // Adds a label and input field as a row in the form
    private void addFormRow(String labelText, JComponent component, GridBagConstraints gbc, int row) {
        JLabel label = new JLabel(labelText); // Create a label for the input field
        label.setFont(UIConstants.LABEL_FONT); // Set the font of the label
        label.setForeground(UIConstants.TEXT_COLOR); // Set the text color of the label

        // Add the label to the panel
        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        add(label, gbc);

        // Add the input field to the panel next to the label
        gbc.gridx = 1;
        gbc.weightx = 1.0; // Allow the input field to expand horizontally
        add(component, gbc);
    }

    // Handles the expense submission
    private void submitExpense() {
        try {
            String description = descriptionField.getText().trim(); // Get the description from the input field
            String amountText = amountField.getText().trim(); // Get the amount from the input field

            // Validate that the description is not empty
            if (description.isEmpty()) {
                showErrorMessage("Please enter a description"); // Show an error message if empty
                return;
            }

            // Validate that the amount is not empty
            if (amountText.isEmpty()) {
                showErrorMessage("Please enter an amount"); // Show an error message if empty
                return;
            }

            // Parse the amount and check if it's greater than zero
            double amount = Double.parseDouble(amountText); // Convert amount text to a double
            if (amount <= 0) {
                showErrorMessage("Amount must be greater than zero"); // Show an error if amount is invalid
                return;
            }

            // Create a new Expense object and add it to the BudgetManager
            FinancialRecord expense = new FinancialRecord(description, amount); // Create a new expense
            budgetManager.addExpense(expense); // Add the expense to the budget manager
            clearFields(); // Clear the input fields after successful submission
            showSuccessMessage("Expense added successfully!"); // Show a success message
        } catch (NumberFormatException ex) {
            showErrorMessage("Please enter a valid amount"); // Handle invalid number format
        }
    }

    // Displays an error message in a dialog box
    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this,
            message,
            "Error",
            JOptionPane.ERROR_MESSAGE);
    }

    // Displays a success message in a dialog box
    private void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(this,
            message,
            "Success",
            JOptionPane.INFORMATION_MESSAGE);
    }

    // Clears the input fields and sets focus back to the description field
    private void clearFields() {
        descriptionField.setText(""); // Clear the description field
        amountField.setText(""); // Clear the amount field
        descriptionField.requestFocus(); // Set focus back to the description field
    }
}
