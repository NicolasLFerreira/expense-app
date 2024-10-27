/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Program;

import Expenses.*;

/**
 *
 * @author nicol
 */
public class ConsoleDebugger {

    public static void main(String[] args) {
        BudgetManager bm = new BudgetManager();
        
        double budget = bm.getBudget();
        
        FinancialRecord[] expenses = bm.getExpenses();
        FinancialRecord[] incomes = bm.getIncomes();
        
        System.out.println("Budget: " + budget);
        
        System.out.println("");
        
        for (int i = 0; i < expenses.length; i++){
            System.out.println("Expense " + i + ": " + expenses[i]);
        }
        
        System.out.println("");
        
        for (int i = 0; i < incomes.length; i++){
            System.out.println("Income " + i + ": " + incomes[i]);
        }
    }
}
