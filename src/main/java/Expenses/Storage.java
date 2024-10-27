/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Expenses;

import Enums.FinancialRecordType;

/**
 *
 * @author nicolas
 *
 * abstracts the access to the DB
 */
public abstract class Storage {
    
    // Holds the name of which table a specific storage instance will be modifying
    protected final String table;
    
    public Storage(FinancialRecordType type){
        this.table = type.getValue();
    }
    
    public abstract FinancialRecord get(String name);

    public abstract void set(FinancialRecord record);

    public abstract void setAll(FinancialRecord[] records);

    public abstract boolean remove(String name);

    public abstract void clear();

    public abstract FinancialRecord[] getArray();
}
