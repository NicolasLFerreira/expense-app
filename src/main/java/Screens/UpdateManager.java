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
public class UpdateManager {

    private Updateable[] panels;

    public UpdateManager() {
        this.panels = new Updateable[0];
    }

    public void setPanels(Updateable[] panels){
        this.panels = panels;
    }
    
    public void triggerUpdate() {
        for (Updateable u : panels){
            u.update();
        }
    }
}
