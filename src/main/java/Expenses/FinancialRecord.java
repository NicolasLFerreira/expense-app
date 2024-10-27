/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Expenses;

/**
 *
 * @author nicolas
 *
 * Stores a financial record (expense or income);
 */
public class FinancialRecord {

    private String name;
    private double amount;

    public FinancialRecord(String name, double amount) {
        this.name = name;

        if (amount < 0) {
            System.out.println("Negative amount being set to zero.");
            this.amount = 0;
        } else {
            this.amount = amount;
        }
    }

    // get methods
    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    // set methods
    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // other
    public void displayExpense() {
        System.out.println(" - \"" + name + "\": $" + amount);
    }

    @Override
    public String toString() {
        return name + " " + amount;
    }
}
