/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Screens;

/**
 *
 * @author nicol
 */
public class UpdateTrigger {
    private final DashboardPanel dashboardPanel;
    private final ExpenseListPanel expenseListPanel;
    
    public UpdateTrigger(DashboardPanel dashboardPanel, ExpenseListPanel expenseListPanel){
        this.dashboardPanel = dashboardPanel;
        this.expenseListPanel = expenseListPanel;
    }
    
    public void update(){
        dashboardPanel.updateDashboard();
        expenseListPanel.refreshList();
    }
}
