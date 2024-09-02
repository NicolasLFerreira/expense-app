/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Expenses;

import File.ExpenseParser;
import File.FileManager;

/**
 *
 * @author will, edited by nicolas
 *
 * ExpenseManager class to manage all expenses
 */
public final class BudgetManager {

    private final Storage expenses;  // Stores the expenses
    private double income;

    public BudgetManager() {
        this.expenses = new ExpenseStorage();

        loadExpenses();
        loadIncome();
    }

    // Public methods
    public double getBudget() {
        return income - calculateTotalExpenses();
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
        saveIncome();
    }

    public void addExpense(Expense expense) {
        expenses.set(expense);  // Adds expense to storage
        saveExpenses();
    }

    public void deleteExpense(String name) {
        if (!expenses.remove(name)) {
            System.out.println("Couldn't find \"" + name + "\"");
        }
        saveExpenses();
    }

    public void displayAllExpenses() {
        System.out.println("All Expenses:");
        for (Expense expense : expenses.getArray()) {  // Loop through expenses and display each
            expense.displayExpense();
        }
    }

    // Private methods
    private double calculateTotalExpenses() {
        double totalExpenses = 0.0;
        for (Expense expense : expenses.getArray()) {  // Loop through expenses to calculate total
            totalExpenses += expense.getAmount();
        }
        return totalExpenses;
    }

    // File methods (calls nicolas' methods)
    private void saveExpenses() {
        FileManager.saveExpenses(ExpenseParser.serialise(expenses));
    }

    private void loadExpenses() {
        expenses.setAll(ExpenseParser.parse(FileManager.loadExpenses()));
    }

    private void saveIncome() {
        FileManager.saveIncome(income);
    }

    private void loadIncome() {
        income = FileManager.loadIncome();
    }
}
