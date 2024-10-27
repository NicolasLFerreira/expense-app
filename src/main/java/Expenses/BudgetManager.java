/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Expenses;

import Database.DatabaseManager;
import Enums.FinancialRecordType;

/**
 *
 * @author nicolas, edited by will
 *
 * ExpenseManager class to manage all expenses
 */
public final class BudgetManager {
    
    private final Storage expenses;  // Stores the expenses
    private final Storage incomes; // Stores the incomes

    public BudgetManager() {
        // Configures the storage to be the db one
        this.expenses = new DBStorage(new DatabaseManager(), FinancialRecordType.EXPENSE);
        this.incomes = new DBStorage(new DatabaseManager(), FinancialRecordType.INCOME);
    }

    // Public methods
    public double getBudget() {
        return calculateTotalIncomes() - calculateTotalExpenses();
    }

    // handles the storage of a financial record with the FinancialRecordType
    public void addRecord(FinancialRecord fr, FinancialRecordType type) {
        if (type == FinancialRecordType.EXPENSE) {
            addExpense(fr);
        }
        if (type == FinancialRecordType.INCOME) {
            addIncome(fr);
        }
    }
    
    // internal usage
    private void addIncome(FinancialRecord income) {
        this.incomes.set(income);
    }
    
    private void addExpense(FinancialRecord expense) {
        expenses.set(expense);  // Adds expense to storage
    }

    // get methods
    public FinancialRecord getExpense(String name) {
        return expenses.get(name);
    }
    
    public FinancialRecord getIncome(String name) {
        return incomes.get(name);
    }

    // delete methods
    public void deleteExpense(String name) {
        if (!expenses.remove(name)) {
            System.out.println("Couldn't find \"" + name + "\"");
        }
    }
    
    public void deleteIncome(String name) {
        if (!incomes.remove(name)) {
            System.out.println("Couldn't find \"" + name + "\"");
        }
    }

    // calculate totals
    public double calculateTotalExpenses() {
        double totalExpenses = 0.0;
        for (FinancialRecord expense : expenses.getArray()) {  // Loop through expenses to calculate total
            totalExpenses += expense.getAmount();
        }
        return totalExpenses;
    }
    
    public double calculateTotalIncomes() {
        double totalIncomes = 0.0;
        for (FinancialRecord income : incomes.getArray()) {  // Loop through expenses to calculate total
            totalIncomes += income.getAmount();
        }
        return totalIncomes;
    }
    
    public FinancialRecord[] getExpenses() {
        return expenses.getArray();
    }
    
    public FinancialRecord[] getIncomes() {
        return incomes.getArray();
    }
    
    public void clearExpenses() {
        expenses.clear(); // Assuming you're using a List<Expense> called expenses
    }
    
    public void clearIncomes() {
        incomes.clear();
    }
}
