/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Screens;

import javax.swing.*;
import java.awt.*;

/**
 * @author williamblake
 * 
 * Help screen for the application providing basic
 * instructions on how to use the Student Budget Calculator.
 */
public class HelpPanel extends JPanel {

    public HelpPanel() {
        setLayout(new BorderLayout(UIConstants.PADDING_MEDIUM, UIConstants.PADDING_MEDIUM));
        setBackground(UIConstants.BACKGROUND_COLOR);
        setBorder(BorderFactory.createEmptyBorder(
                UIConstants.PADDING_LARGE,
                UIConstants.PADDING_LARGE,
                UIConstants.PADDING_LARGE,
                UIConstants.PADDING_LARGE
        ));

        // Header
        JLabel headerLabel = new JLabel("Help & Documentation");
        headerLabel.setFont(UIConstants.HEADER_FONT);
        headerLabel.setForeground(UIConstants.PRIMARY_COLOR);

        // Create help content panel
        JPanel helpCard = UIConstants.createCard();
        helpCard.setLayout(new BorderLayout());

        // Create styled help content
        String[][] helpSections = {
            {"Getting Started",
                "Welcome to the Student Budget Calculator! This application helps you track your expenses and manage your budget effectively."},
            {"Adding Income",
                "1. Navigate to the Income tab\n2. Enter your income amount\n3. Click 'Add Income' to record it. After you close the application the incomes will be added. If you wish to clear income, simply press 'Clear Incomes' and after closing the program will have cleared your income."},
            {"Managing Expenses",
                "1. Go to the Expenses tab\n2. Enter expense description and amount\n3. Click 'Submit' to record the expense"},
            {"Viewing Reports",
                "1. Check the Dashboard for an overview\n2. View detailed expense history in the Expenses List\n3. Monitor your budget status"}
        };

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(UIConstants.CARD_COLOR);

        for (String[] section : helpSections) {
            JPanel sectionPanel = new JPanel();
            sectionPanel.setLayout(new BorderLayout());
            sectionPanel.setBackground(UIConstants.CARD_COLOR);
            sectionPanel.setBorder(BorderFactory.createEmptyBorder(
                    UIConstants.PADDING_MEDIUM,
                    UIConstants.PADDING_MEDIUM,
                    UIConstants.PADDING_MEDIUM,
                    UIConstants.PADDING_MEDIUM
            ));

            JLabel sectionTitle = new JLabel(section[0]);
            sectionTitle.setFont(UIConstants.TITLE_FONT);
            sectionTitle.setForeground(UIConstants.PRIMARY_COLOR);

            JTextArea sectionContent = new JTextArea(section[1]);
            sectionContent.setFont(UIConstants.LABEL_FONT);
            sectionContent.setForeground(UIConstants.TEXT_COLOR);
            sectionContent.setLineWrap(true);
            sectionContent.setWrapStyleWord(true);
            sectionContent.setEditable(false);
            sectionContent.setBackground(UIConstants.CARD_COLOR);
            sectionContent.setBorder(BorderFactory.createEmptyBorder(
                    UIConstants.PADDING_SMALL,
                    0,
                    0,
                    0
            ));

            sectionPanel.add(sectionTitle, BorderLayout.NORTH);
            sectionPanel.add(sectionContent, BorderLayout.CENTER);
            contentPanel.add(sectionPanel);
            contentPanel.add(Box.createVerticalStrut(UIConstants.PADDING_MEDIUM));
        }

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        helpCard.add(scrollPane, BorderLayout.CENTER);

        // Add components to main panel
        add(headerLabel, BorderLayout.NORTH);
        add(helpCard, BorderLayout.CENTER);
    }
}
