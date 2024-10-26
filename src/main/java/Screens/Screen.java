/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Screens;

import Program.ScreenManager;
import Program.CommandProcessor;
import Enums.CommandType;
import Enums.ScreenType;

/**
 *
 * @author nicolas
 */
// A "screen" is the different modes of the console,
// such as when modifying expenses or in the main menu.
public abstract class Screen {

    // Name and id for managing what is what.
    protected final String title;
    protected final int id;

    // Stores the available commands for a specific screen.
    protected CommandProcessor processor;

    // Outside reference to the manager so that screens can call
    // screen changes themselves.
    protected final ScreenManager screenManager;

    protected Screen(ScreenType type, ScreenManager screenManager) {

        this.title = type.getTitle();
        this.id = type.ordinal();

        // Each child will have its own set of commands.
        this.processor = new CommandProcessor(initialiseCommands());

        // Initialises the manager;
        this.screenManager = screenManager;
    }

    // Display methods.
    public final void displayScreen() {
        displayHeader();
        displayDescription(); // To be overriden
        displayCommands();
    }

    private void displayHeader() {
        StringBuilder buildLine = new StringBuilder();
        String line;
        for (int i = 0; i < this.title.length(); i++) {
            buildLine.append("=");
        }
        line = buildLine.append("==").toString();

        System.out.println(line);
        System.out.println("=" + this.title + "=");
        System.out.println(line);
    }

    // Access methods.
    public final String getName() {
        return title;
    }

    public final int getId() {
        return id;
    }

    // Displays and gets the command to run
    @SuppressWarnings("empty-statement")
    private void displayCommands() {
        System.out.println("\nCommands:");
        for (CommandType command : processor.getCommands()) {
            System.out.println("> " + command.getName() + " (" + command.getKey() + ")");
        }
        // Loops until valid
        while (!router(this.processor.listener())) ;
    }

    // To be overriden in subclasses with their own set of commands.
    protected abstract CommandType[] initialiseCommands();

    // This method contains the custom output of the specific screen;
    protected abstract void displayDescription();

    // Function for routing the different commands of this screen.
    protected abstract boolean router(CommandType command);
}
