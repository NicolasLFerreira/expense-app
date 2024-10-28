/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import Enums.FinancialRecordType;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nicol
 *
 * This is the class that will be responsible for all the configuration and init
 * of the database.
 *
 * It implements the Manager class for the sake of abstracting away the actual
 * implementation in case I want to change how it's done in the future.
 */
public class DatabaseManager implements Manager {

    // connection string to the database
    private final String URL;
    private Connection connection;

    // default constructor
    public DatabaseManager(String URL) {
        // Gets the URL through the constructor.
        // For the sake of being able to reuse the class for testing and production
        // environments
        this.URL = URL;

        // Setups the embedded derby
        try {
            // use driver to get connection
            connection = DriverManager.getConnection(URL);
            // calls the initialiser function which attemps to create the necessary
            // tables if they don't already exist
            initTable(FinancialRecordType.EXPENSE);
            initTable(FinancialRecordType.INCOME);
        } catch (SQLException ex) {
            System.err.println("Failed to connect to the database.");
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initTable(FinancialRecordType type) {
        try ( Statement statement = connection.createStatement()) {
            // sql code to generate the table for a table
            String sql = "CREATE TABLE " + type.getValue() + " ("
                    + SqlQueryParameters.NAME + " VARCHAR(255) NOT NULL PRIMARY KEY, "
                    + SqlQueryParameters.AMOUNT + " DOUBLE NOT NULL)";
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            // an error is given if the table already exists
            // so I found this solution online to deal with it

            if (!ex.getSQLState().equals("X0Y32")) { // X0Y32 is the code for when a table already exists
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // for other classes to be able to use the connection
    @Override
    public Connection getConnection() {
        return connection;
    }

    // when the program ends use this to close connection
    @Override
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
