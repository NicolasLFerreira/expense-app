/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Program;

import Enums.CommandType;

/**
 *
 * @author nicolas
 */
public class CommandProcessor {

    // Stores the commands available for a specific screen.
    private final CommandType[] commands;

    public CommandProcessor(CommandType[] commands) {
        this.commands = commands;
    }
    
    public CommandType[] getCommands(){
        return commands;
    }

    // Awaits a command
    public CommandType listener() {
        char input = Input.captureCommand("-> ");
        //System.out.println("Got " + input);
        return validate(input);
    }

    // Returns the character's respective CommandType
    private CommandType validate(char input) {
        for (CommandType command : commands) {
            if (input == command.getKey()) {
                return command;
            }
        }

        return CommandType.INVALID;
    }
}
