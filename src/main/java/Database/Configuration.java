/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

/**
 *
 * @author nicol
 *
 * This class contains some configuration variables for the database.
 * 
 * I know it's a bad thing to keep connection strings in source code and have it be 
 * committed but for this assignment I'm doing it this way.
 *
 */
public class Configuration {

    // Connection URLs
    public static final String prodURL = "jdbc:derby:ExpensesDB;create=true";
    public static final String testURL = "jdbc:derby:memory:TestExpensesDB;create=true"; // the test db is kept in memory
}
