/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Screens;

import Program.ScreenManager;
import Enums.CommandType;
import Enums.ScreenType;

/**
 *
 * @author nicolas
 */
public class MenuScreen extends Screen {

    public MenuScreen(ScreenType type, ScreenManager screenManager) {
        super(type, screenManager);
    }

    @Override
    protected CommandType[] initialiseCommands() {
        return new CommandType[]{
            CommandType.BUDGET,
            CommandType.HELP,
            CommandType.EXIT
        };
    }

    @Override
    public void displayDescription() {
        System.out.println("");
        System.out.println("This is the main menu for the app.");
        System.out.println("What would you like to do?");
    }

    @Override
    public boolean router(CommandType command) {
        switch (command) {
            case BUDGET:
                screenManager.changeScreen(ScreenType.BUDGET);
                break;
            case HELP:
                screenManager.changeScreen(ScreenType.HELP);
                break;
            case EXIT:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid command.");
                return false;
        }

        return true;
    }
}
