package Screens;

import javax.swing.*;
import java.awt.*;
import Expenses.BudgetManager;

public class DashboardPanel extends JPanel {

    private final BudgetManager budgetManager;
    private static final Color BACKGROUND_COLOR = new Color(245, 247, 250);
    private static final Color CARD_COLOR = Color.WHITE;
    private static final Color ACCENT_COLOR = new Color(70, 130, 180);
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 16);
    private static final Font VALUE_FONT = new Font("Arial", Font.BOLD, 24);

    private JPanel cardsPanel;
    private JPanel summaryPanel;

    public DashboardPanel(BudgetManager budgetManager) {
        this.budgetManager = budgetManager;
        setupPanel();
        addComponents();
    }

    private void setupPanel() {
        setLayout(new BorderLayout(20, 20));
        setBackground(BACKGROUND_COLOR);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    private void addComponents() {
        // Header
        JLabel headerLabel = new JLabel("Financial Dashboard");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 28));
        headerLabel.setForeground(ACCENT_COLOR);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        add(headerLabel, BorderLayout.NORTH);

        // Cards Panel
        cardsPanel = new JPanel(new GridLayout(1, 3, 20, 0));
        cardsPanel.setOpaque(false);

        // Add metric cards
        double totalIncome = budgetManager.calculateTotalIncomes();
        double totalExpenses = budgetManager.calculateTotalExpenses();
        double budget = budgetManager.getBudget();

        cardsPanel.add(createMetricCard("Total Income", totalIncome, "↑", new Color(46, 204, 113)));
        cardsPanel.add(createMetricCard("Total Expenses", totalExpenses, "↓", new Color(231, 76, 60)));
        cardsPanel.add(createMetricCard("Budget", budget, "", ACCENT_COLOR));

        add(cardsPanel, BorderLayout.CENTER);

        // Add summary panel
        summaryPanel = createSummaryPanel(totalIncome, totalExpenses);
        add(summaryPanel, BorderLayout.SOUTH);
    }

    private JPanel createMetricCard(String title, double value, String indicator, Color accentColor) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(CARD_COLOR);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        // Title
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(Color.GRAY);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Value
        JLabel valueLabel = new JLabel(String.format("$%.2f %s", value, indicator));
        valueLabel.setFont(VALUE_FONT);
        valueLabel.setForeground(accentColor);
        valueLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(titleLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(valueLabel);

        return card;
    }

    private JPanel createSummaryPanel(double income, double expenses) {
        JPanel summaryPanel = new JPanel();
        summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.Y_AXIS));
        summaryPanel.setBackground(CARD_COLOR);
        summaryPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        // Calculate remaining budget
        double remaining = income - expenses;
        String status = remaining >= 0 ? "Under Budget" : "Over Budget";
        Color statusColor = remaining >= 0 ? new Color(46, 204, 113) : new Color(231, 76, 60);

        JLabel statusLabel = new JLabel("Status: " + status);
        statusLabel.setFont(TITLE_FONT);
        statusLabel.setForeground(statusColor);

        JLabel remainingLabel = new JLabel(String.format("Remaining: $%.2f", Math.abs(remaining)));
        remainingLabel.setFont(VALUE_FONT);
        remainingLabel.setForeground(statusColor);

        summaryPanel.add(statusLabel);
        summaryPanel.add(Box.createVerticalStrut(10));
        summaryPanel.add(remainingLabel);

        return summaryPanel;
    }
    // Update dashboard

    public void updateDashboard() {
        // Remove existing components
        remove(cardsPanel);
        remove(summaryPanel);

        // Recreate components with updated values
        double totalIncome = budgetManager.calculateTotalIncomes();
        double totalExpenses = budgetManager.calculateTotalExpenses();
        double budget = budgetManager.getBudget();

        // Cards Panel
        cardsPanel = new JPanel(new GridLayout(1, 3, 20, 0));
        cardsPanel.setOpaque(false);

        cardsPanel.add(createMetricCard("Total Income", totalIncome, "↑", new Color(46, 204, 113)));
        cardsPanel.add(createMetricCard("Total Expenses", totalExpenses, "↓", new Color(231, 76, 60)));
        cardsPanel.add(createMetricCard("Budget", budget, "", ACCENT_COLOR));

        add(cardsPanel, BorderLayout.CENTER);

        // Add summary panel
        summaryPanel = createSummaryPanel(totalIncome, totalExpenses);
        add(summaryPanel, BorderLayout.SOUTH);

        // Revalidate and repaint
        revalidate();
        repaint();
    }
}
