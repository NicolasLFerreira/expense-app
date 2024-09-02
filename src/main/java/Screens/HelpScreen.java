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
 * @author nicolas & will
 */
public class HelpScreen extends Screen {

    public HelpScreen(ScreenType type, ScreenManager screenManager) {
        super(type, screenManager);
    }

    @Override
    protected CommandType[] initialiseCommands() {
        return new CommandType[]{
            CommandType.BACK
        };
    }

    @Override
    public void displayDescription() {
        System.out.println("");
        System.out.println("If you're confused, this page is for you.");
        System.out.println("");
        System.out.println("Different screens will have different functionalities");
        System.out.println("and sets of commands that can be used.");
        System.out.println("");
        System.out.println("Main menu:");
        System.out.println("- Switch between the different screens or exit the program.");
        System.out.println("");
        System.out.println("Help screen:");
        System.out.println("- You are here. Provides a little guide of the program.");
        System.out.println("");
        System.out.println("Budget screen:");
        System.out.println("- Manage and store your expenses and income.");
        System.out.println("- Here are the commands:");
        System.out.println("");
        System.out.println("- (n): Create a new or modify existing expenses.");
        System.out.println("- (i): Set your income.");
        System.out.println("- (d): Delete expenses.");
        System.out.println("- (f): Display income and expenses.");
        System.out.println("- (s): Show your budget (income - all expenses).");
    }

    @Override
    public boolean router(CommandType command) {
        switch (command) {
            case BACK:
                screenManager.changeScreen(ScreenType.MENU);
                break;
            default:
                System.out.println("Invalid command.");
                return false;
        }
        return true;
    }
}
