/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Program;

import Budget.BudgetManager;
import Budget.FinancialRecord;

/**
 *
 * @author nicol
 */
public class ConsoleLogger {

    private final BudgetManager budgetManager;
    
    public ConsoleLogger(BudgetManager budgetManager){
        this.budgetManager = budgetManager;
    }
    
    public void log() {
        double budget = budgetManager.getBudget();

        FinancialRecord[] expenses = budgetManager.getExpenses();
        FinancialRecord[] incomes = budgetManager.getIncomes();

        System.out.println("Budget: " + budget);

        System.out.println("");

        for (int i = 0; i < expenses.length; i++) {
            System.out.println("Expense " + i + ": " + expenses[i]);
        }

        System.out.println("");

        for (int i = 0; i < incomes.length; i++) {
            System.out.println("Income " + i + ": " + incomes[i]);
        }
    }
}
