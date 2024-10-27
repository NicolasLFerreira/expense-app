package Screens;

import Enums.FinancialRecordType;
import javax.swing.*;
import java.awt.*;
import Expenses.BudgetManager;
import Expenses.FinancialRecord;

/**
 *
 * @author will's UI and code as base, refactored by nicolas
 *
 * EntryPanel for general use for both the expense and income entry panels.
 * To be configured via the constructor using a FinancialRecordType
 */
public class EntryPanel extends JPanel {

    // User input fields
    private final JTextField descriptionField;
    private final JTextField amountField;

    // BudgetManager and UpdateTrigger refereneces.
    private final BudgetManager budgetManager;
    private final UpdateTrigger updateTrigger;

    // For configuration
    private final FinancialRecordType frType;

    // Constructor for initializing the panel and its components
    public EntryPanel(BudgetManager budgetManager, UpdateTrigger updateTrigger, FinancialRecordType frType) {
        // used for sending out signals to update screens
        this.updateTrigger = updateTrigger;

        // sets the type of record this panel will deal with
        this.frType = frType;

        this.budgetManager = budgetManager;
        setLayout(new GridBagLayout());
        setBackground(UIConstants.BACKGROUND_COLOR);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Initialize the input fields and button
        descriptionField = createStyledTextField();
        amountField = createStyledTextField();
        JButton submitButton = createStyledSubmitButton();

        // Configure the layout constraints for adding components
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Add heading
        JLabel heading = new JLabel(frType.getValue() + " Entry");
        heading.setFont(UIConstants.HEADING_FONT);
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        add(heading, gbc);

        // Add input fields to the panel with labels
        addFormRow("Description:", descriptionField, gbc, 1);
        addFormRow("Amount ($):", amountField, gbc, 2);

        // Add the submit button below the input fields
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 5, 10, 5);
        add(submitButton, gbc);

        // Add an empty space below the button to push components upwards
        gbc.gridy = 4;
        gbc.weighty = 1.0;
        add(Box.createVerticalGlue(), gbc);
    }

    // Creates a styled text field for user input
    private JTextField createStyledTextField() {
        JTextField field = new JTextField(20);
        field.setFont(UIConstants.LABEL_FONT);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UIConstants.BORDER_COLOR),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        return field;
    }

    // Creates a styled "Submit Income" button with hover effects
    private JButton createStyledSubmitButton() {
        JButton button = new JButton("Submit " + frType.getValue());
        button.setFont(UIConstants.BUTTON_FONT);
        button.setForeground(Color.BLACK);
        button.setBackground(UIConstants.SUCCESS_COLOR);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add hover effect to change button color
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(UIConstants.SUCCESS_HOVER_COLOR);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(UIConstants.SUCCESS_COLOR);
            }
        });

        // Add action listener to handle the button click event
        button.addActionListener(e -> submitRecord());
        return button;
    }

    // Adds a label and input field as a row in the form
    private void addFormRow(String labelText, JComponent component, GridBagConstraints gbc, int row) {
        JLabel label = new JLabel(labelText);
        label.setFont(UIConstants.LABEL_FONT);
        label.setForeground(UIConstants.TEXT_COLOR);

        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;
        add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        add(component, gbc);
    }

    // Handles the record submission
    private void submitRecord() {
        try {
            String description = descriptionField.getText().trim();
            String amountText = amountField.getText().trim();

            // Validate that the description is not empty
            if (description.isEmpty()) {
                showErrorMessage("Please enter a description");
                return;
            }

            // Validate that the amount is not empty
            if (amountText.isEmpty()) {
                showErrorMessage("Please enter an amount");
                return;
            }

            // Parse the amount and check if it's greater than zero
            double amount = Double.parseDouble(amountText);
            if (amount <= 0) {
                showErrorMessage("Amount must be greater than zero");
                return;
            }

            // Create a new FinancialRecord object and add it to the BudgetManager
            FinancialRecord fr = new FinancialRecord(description, amount);
            // Create a new FinancialRecord object and add it to the BudgetManager
            budgetManager.addRecord(fr, frType);
            // triggers the update
            updateTrigger.update();

            clearFields();
            showSuccessMessage(frType.getValue() + " added successfully!");
        } catch (NumberFormatException ex) {
            showErrorMessage("Please enter a valid amount");
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
        descriptionField.setText("");
        amountField.setText("");
        descriptionField.requestFocus();
    }
}
