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

    // retrieves budget
    public double getBudget() {
        return calculateTotalIncomes() - calculateTotalExpenses();
    }

    // sets a single record via the type
    public void addRecord(FinancialRecord fr, FinancialRecordType type) {
        if (type == FinancialRecordType.EXPENSE) {
            addExpense(fr);
        }
        if (type == FinancialRecordType.INCOME) {
            addIncome(fr);
        }
    }

    // retrieves a single record configured via the type
    public FinancialRecord getRecord(String name, FinancialRecordType type) {
        if (type == FinancialRecordType.EXPENSE) {
            return getExpense(name);
        }
        if (type == FinancialRecordType.INCOME) {
            return getIncome(name);
        }

        // in case something goes wrong
        return null;
    }

    // retrieves all records of a certain type
    public FinancialRecord[] getRecords(FinancialRecordType type) {
        if (type == FinancialRecordType.EXPENSE) {
            return getExpenses();
        }
        if (type == FinancialRecordType.INCOME) {
            return getIncomes();
        }

        // in case something goes wrong
        return null;
    }

    // deletes a record by name based on type
    public boolean deleteRecord(String name, FinancialRecordType type) {
        if (type == FinancialRecordType.EXPENSE) {
            return deleteExpense(name);
        }
        if (type == FinancialRecordType.INCOME) {
            return deleteIncome(name);
        }

        // in case something goes wrong
        return false;
    }

    // clears all records of a certain type
    public void clearStorage(FinancialRecordType type) {
        if (type == FinancialRecordType.EXPENSE) {
            clearExpenses();
        }
        if (type == FinancialRecordType.INCOME) {
            clearIncomes();
        }
    }

    // retrieves the total amount of a certain record type
    public double getTotal(FinancialRecordType type) {
        if (type == FinancialRecordType.EXPENSE) {
            return calculateTotalExpenses();
        }
        if (type == FinancialRecordType.INCOME) {
            return calculateTotalIncomes();
        }

        return 0;
    }

    // internal set
    private void addIncome(FinancialRecord income) {
        this.incomes.set(income);
    }

    private void addExpense(FinancialRecord expense) {
        expenses.set(expense);  // Adds expense to storage
    }

    // get methods
    private FinancialRecord getExpense(String name) {
        return expenses.get(name);
    }

    private FinancialRecord getIncome(String name) {
        return incomes.get(name);
    }

    // returns arrays
    public FinancialRecord[] getExpenses() {
        return expenses.getArray();
    }

    public FinancialRecord[] getIncomes() {
        return incomes.getArray();
    }

    // delete methods
    private boolean deleteExpense(String name) {
        return expenses.remove(name);
    }

    private boolean deleteIncome(String name) {
        return incomes.remove(name);
    }

    // calculate totals
    private double calculateTotalExpenses() {
        double totalExpenses = 0.0;
        for (FinancialRecord expense : expenses.getArray()) {  // Loop through expenses to calculate total
            totalExpenses += expense.getAmount();
        }
        return totalExpenses;
    }

    private double calculateTotalIncomes() {
        double totalIncomes = 0.0;
        for (FinancialRecord income : incomes.getArray()) {  // Loop through expenses to calculate total
            totalIncomes += income.getAmount();
        }
        return totalIncomes;
    }

    // clears
    public void clearExpenses() {
        expenses.clear(); // Assuming you're using a List<Expense> called expenses
    }

    public void clearIncomes() {
        incomes.clear();
    }
}
