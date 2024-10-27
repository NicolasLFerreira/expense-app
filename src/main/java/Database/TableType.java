/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

/**
 *
 * @author nicol
 * 
 * Enum used for configuring the dbstorage
 * 
 */
public enum TableType {
    EXPENSE(SqlQueryParameters.EXPENSE_TABLE),
    INCOME(SqlQueryParameters.INCOME_TABLE);

    private final String value;

    TableType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
