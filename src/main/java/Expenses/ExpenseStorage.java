/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Expenses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author will
 *
 * stores expenses. abstracted the actual data structure behind with "Storage"
 */
public class ExpenseStorage implements Storage {

    private final Map<String, Expense> storage;

    public ExpenseStorage() {
        this.storage = new HashMap<>();
    }

    @Override
    public Expense get(String name) {
        return storage.get(name);
    }

    @Override
    public void set(Expense expense) {
        storage.put(expense.getName(), expense);
    }

    @Override
    public void setAll(Expense[] expenses) {
        for (Expense expense : expenses) {
            set(expense);
        }
    }

    @Override
    public boolean remove(String name) {
        return storage.remove(name) != null;
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Expense[] getArray() {
        List<Expense> array = new ArrayList<>();
        for (String name : storage.keySet()) {
            array.add(storage.get(name));
        }

        return array.toArray(new Expense[array.size()]);
    }
}
