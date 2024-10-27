/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Expenses;

import Database.TableType;

/**
 *
 * @author nicolas
 *
 * abstracts the access to a db
 */
public abstract class Storage {
    
    private final String table;
    
    public Storage(TableType type){
        this.table = type.getValue();
    }
    
    public abstract FinancialRecord get(String name);

    public abstract void set(FinancialRecord record);

    public abstract void setAll(FinancialRecord[] records);

    public abstract boolean remove(String name);

    public abstract void clear();

    public abstract FinancialRecord[] getArray();
}
