/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Expenses;

/**
 *
 * @author nicolas
 *
 * abstracts the access to a db
 */
public interface Storage {

    public Expense get(String name);

    public void set(Expense expense);

    public void setAll(Expense[] expenses);

    public boolean remove(String name);

    public void clear();

    public Expense[] getArray();
}
