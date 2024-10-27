package Screens;

import java.awt.Color;
import java.awt.Font;

/**
 * @author williamblake
 * UIConstants class holds constant values for UI design elements such as colors and fonts.
 * This class centralizes the configuration of visual components to ensure consistency across the application.
 */
public class UIConstants {
    // Color constants
    public static final Color PRIMARY_COLOR = new Color(51, 122, 183);      // Primary color used for main UI elements
    public static final Color BACKGROUND_COLOR = new Color(245, 245, 245);   // Light background color for panels
    public static final Color BUTTON_HOVER_COLOR = new Color(40, 96, 144);   // Color for buttons on hover
    public static final Color SUCCESS_COLOR = new Color(92, 184, 92);        // Color indicating success actions (e.g., confirmations)
    public static final Color SUCCESS_HOVER_COLOR = new Color(68, 157, 68);  // Hover color for success buttons
    public static final Color BORDER_COLOR = new Color(200, 200, 200);        // Color for borders around components
    public static final Color TEXT_COLOR = new Color(51, 51, 51);            // Default text color used throughout the UI
    
    // Font constants
    public static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 24); // Font for titles (larger and bold)
    public static final Font LABEL_FONT = new Font("Segoe UI", Font.PLAIN, 14); // Font for labels (standard size)
    public static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 14); // Font for buttons (bold for emphasis)
    public static final Font LIST_FONT = new Font("Segoe UI", Font.PLAIN, 14);  // Font for lists (standard size)
    public static final Font TOTAL_FONT = new Font("Segoe UI", Font.BOLD, 18);  // Font for total amounts (larger for visibility)
}
