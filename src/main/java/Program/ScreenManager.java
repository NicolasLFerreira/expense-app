/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Program;

import Enums.ScreenType;
import Screens.BudgetScreen;
import Screens.HelpScreen;
import Screens.MenuScreen;
import Screens.Screen;
import java.util.HashMap;

/**
 *
 * @author nicolas
 */
// Manages which screen is currently active
public class ScreenManager {

    private final HashMap<ScreenType, Screen> screens;
    private Screen active;

    public ScreenManager() {
        this.screens = new HashMap<>();

        // I'll just add the screens by hand
        this.screens.put(ScreenType.MENU, new MenuScreen(ScreenType.MENU, this));
        this.screens.put(ScreenType.HELP, new HelpScreen(ScreenType.HELP, this));
        this.screens.put(ScreenType.BUDGET, new BudgetScreen(ScreenType.BUDGET, this));

        // Default screen is the menu
        active = this.screens.get(ScreenType.MENU);
    }

    // Switches the currently shown screen.
    public void changeScreen(ScreenType screen) {
        this.active = this.screens.get(screen);
        display();
    }

    public void display() {
        space();
        this.active.displayScreen();
    }

    // I COULDN'T FIGURE OUT A WAY TO CLEAR THE CONSOLE THAT WORKS MULTI-PLATFORM.
    // THIS WILL HAVE TO DO.
    private void space() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n");
    }

    // Returns currently active screen
    public Screen getActive() {
        return this.active;
    }
}
