/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Enums;

/**
 * @author nicolas, edited by will
 */
public enum CommandType {
    // HERE IS THE LIST OF COMMANDS AND THEIR RELATED KEYS.
    // TO CHECK IF THE USER INPUT IS A CERTAIN COMMAND, COMPARE IT TO THE ".command" FIELD.
    //
    // >>>DON'T ADD DUPLICATE ONES<<<
    // HOW TO ADD NEW ONES:
    // <NAME>(<Display name>, <key>);
    //
    // Control
    EXIT("Exit", 'x'),
    BACK("Back", 'q'),
    HELP("Help Screen", 'h'),
    BUDGET("Budget Screen", 'b'),
    // BUDGET
    INCOME("Set income", 'i'),
    NEW("New/edit", 'n'),
    DELETE("Delete", 'd'),
    DISPLAY("Display", 'f'),
    SHOW("Show budget", 's'),
    // if needed idk
    INVALID("INVALID", Character.MIN_VALUE);

    private final String name;
    private final char key;

    private CommandType(String name, char key) {
        this.name = name;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public char getKey() {
        return key;
    }

    public boolean isValid() {
        return this != CommandType.INVALID;
    }
}
