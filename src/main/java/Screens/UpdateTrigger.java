/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Screens;

/**
 *
 * @author nicol
 *
 * Class handling updates of dashboard and list panels
 */
public class UpdateTrigger {

    private final DashboardPanel dashboardPanel;
    private final ListPanel listPanel;

    public UpdateTrigger(DashboardPanel dashboardPanel, ListPanel listPanel) {
        this.dashboardPanel = dashboardPanel;
        this.listPanel = listPanel;
    }

    public void update() {
        dashboardPanel.updateDashboard();
        listPanel.refreshList();
    }
}
