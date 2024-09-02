/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Screens;

import Program.ScreenManager;
import Enums.CommandType;
import Enums.ScreenType;
import Expenses.BudgetManager;
import Expenses.Expense;
import Program.Input;

/**
 *
 * @author will & nicolas
 */
public class BudgetScreen extends Screen {

    private BudgetManager budgetManager;

    public BudgetScreen(ScreenType type, ScreenManager screenManager) {
        super(type, screenManager);

        this.budgetManager = new BudgetManager();
    }

    @Override
    protected CommandType[] initialiseCommands() {
        return new CommandType[]{
            CommandType.NEW,
            CommandType.INCOME,
            CommandType.DELETE,
            CommandType.DISPLAY,
            CommandType.SHOW,
            CommandType.BACK
        };
    }

    @Override
    public void displayDescription() {
        System.out.println("");
        System.out.println("Store monthly expenses and income here.");
    }

    @Override
    public boolean router(CommandType command) {
        String name;
        switch (command) {
            case NEW:
                System.out.println("");
                name = Input.captureString("Enter expense name:");
                double value = Input.captureDouble("Enter amount:");

                budgetManager.addExpense(new Expense(name, value));
                break;
            case INCOME:
                System.out.println("");
                int i = Input.captureInt("Enter your income:");
                budgetManager.setIncome(i);
                break;
            case DELETE:
                System.out.println("");
                name = Input.captureString("Enter expense to delete:");
                budgetManager.deleteExpense(name);
                break;
            case DISPLAY:
                System.out.println("");
                System.out.println("Income is:");
                System.out.println(budgetManager.getIncome());
                budgetManager.displayAllExpenses();
                Input.throwAway("Press anything...");
                break;

            case SHOW:
                System.out.println("");
                System.out.println("Budget is...");
                System.out.println(budgetManager.getBudget());
                Input.throwAway("Press anything...");
                break;
            case BACK:
                this.screenManager.changeScreen(ScreenType.MENU);
                break;
            default:
                System.out.println("");
                System.out.println("Invalid command.");
                return false;
        }
        return true;
    }
}
