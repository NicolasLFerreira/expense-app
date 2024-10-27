package Screens;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

/**
 * 
 * @author will
 */

public class UIConstants {
    // Colors

    public static final Color BACKGROUND_COLOR = new Color(245, 247, 250);
    public static final Color CARD_COLOR = Color.WHITE;
    public static final Color PRIMARY_COLOR = new Color(70, 130, 180);
    public static final Color SUCCESS_COLOR = new Color(46, 204, 113);
    public static final Color DANGER_COLOR = new Color(231, 76, 60);
    public static final Color BORDER_COLOR = new Color(230, 230, 230);
    public static final Color TEXT_COLOR = new Color(51, 51, 51);
    public static final Color MUTED_TEXT_COLOR = new Color(119, 119, 119);

    // Fonts
    public static final Font HEADER_FONT = new Font("Arial", Font.BOLD, 24);
    public static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 18);
    public static final Font LABEL_FONT = new Font("Arial", Font.PLAIN, 14);
    public static final Font VALUE_FONT = new Font("Arial", Font.BOLD, 16);
    public static final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 14);
    public static final Font LIST_FONT = new Font("Arial", Font.PLAIN, 14);
    public static final Font TOTAL_FONT = new Font("Arial", Font.BOLD, 18); // New declaration

    // Padding
    public static final int PADDING_SMALL = 5;
    public static final int PADDING_MEDIUM = 10;
    public static final int PADDING_LARGE = 20;

    // Borders
    public static final Border DEFAULT_BORDER = BorderFactory.createLineBorder(BORDER_COLOR); // Default border
    public static final Border CARD_BORDER = BorderFactory.createCompoundBorder(DEFAULT_BORDER, BorderFactory.createEmptyBorder(PADDING_MEDIUM, PADDING_MEDIUM, PADDING_MEDIUM, PADDING_MEDIUM)); // Card border

    public static final Font HEADING_FONT = new Font("Arial", Font.BOLD, 24);
    public static final Color SUCCESS_HOVER_COLOR = new Color(80, 200, 120);

    // Create a method for consistent card styling
    public static JPanel createCard() {
        JPanel card = new JPanel();
        card.setBackground(CARD_COLOR);
        card.setBorder(CARD_BORDER);
        return card;
    }

    // Create a method for consistent button styling
    public static void styleButton(JButton button, Color bgColor) {
        button.setFont(BUTTON_FONT);
        button.setForeground(Color.WHITE);
        button.setBackground(bgColor);
        button.setBorder(BorderFactory.createEmptyBorder(PADDING_SMALL, PADDING_MEDIUM, PADDING_SMALL, PADDING_MEDIUM));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(darken(bgColor, 0.1f));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
    }

    // Utility method to darken colors for hover effects
    private static Color darken(Color color, float factor) {
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        return Color.getHSBColor(hsb[0], hsb[1], Math.max(0f, hsb[2] - factor));
    }

    // Create a method for consistent label styling
    public static JLabel createLabel(String text, Font font, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        return label;
    }

    // Create a method for consistent text field styling
    public static JTextField createTextField(int columns) {
        JTextField textField = new JTextField(columns);
        textField.setFont(LABEL_FONT);
        textField.setBackground(Color.WHITE);
        textField.setBorder(BorderFactory.createCompoundBorder(DEFAULT_BORDER, BorderFactory.createEmptyBorder(PADDING_SMALL, PADDING_MEDIUM, PADDING_SMALL, PADDING_MEDIUM)));
        return textField;
    }
}
