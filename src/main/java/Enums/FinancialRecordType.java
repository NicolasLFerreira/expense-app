/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Enums;

import Database.SqlQueryParameters;

/**
 *
 * @author nicol
 * 
 * The two types of FinancialRecord
 */
public enum FinancialRecordType {
    EXPENSE(SqlQueryParameters.EXPENSE),
    INCOME(SqlQueryParameters.INCOME);

    private final String value;

    FinancialRecordType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

