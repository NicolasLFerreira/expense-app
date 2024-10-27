/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Screens;

import javax.swing.*;
import java.awt.*;

/**
 * @author williamblake
 * Help screen for the application providing basic instructions on how to use the Student Budget Calculator.
 */
class HelpScreenGUI extends JPanel {

    /**
     * Constructor that sets up the help screen with instructions.
     */
    public HelpScreenGUI() {
        setLayout(new BorderLayout());  // Set layout manager to BorderLayout
        
        // Create a text area for displaying the help content
        JTextArea helpText = new JTextArea();
        helpText.setText(
            "Student Budget Calculator Help\n\n" +
            "Add Expense:\n" +
            "- Enter description and amount\n" +
            "- Click Submit to save\n\n" +
            "View Expenses:\n" +
            "- See all expenses and total\n" +
            "- Use menu to switch views"
        );
        
       helpText.setFont(new Font("Arial", Font.PLAIN, 14));  // Example font customization
       helpText.setForeground(Color.DARK_GRAY);  // Set text color
        
        // Make the text area non-editable, so the user can't modify the help content
        helpText.setEditable(false);
        helpText.setLineWrap(true);  // Enable line wrapping
        helpText.setWrapStyleWord(true);  // Wrap lines at word boundaries

        // Add the text area inside a scroll pane to allow scrolling if the content is too long
        add(new JScrollPane(helpText), BorderLayout.CENTER);
    }
}
