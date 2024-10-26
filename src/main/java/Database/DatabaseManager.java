/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

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
    private static final String URL = "jdbc:derby:ExpensesDB;create=true";
    private Connection connection;

    // default constructor
    public DatabaseManager() {
        // Setups the embedded derby
        try {
            // use driver to get connection
            connection = DriverManager.getConnection(URL);
            // calls the initialiser function which attemps to create the necessary
            // tables if they don't already exist
            initializeDatabase();
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database.");
            e.printStackTrace();
        }
    }

    private void initializeDatabase() {
        try ( Statement stmt = connection.createStatement()) {
            // sql code to generate the table for expenses
            String sql = "CREATE TABLE EXPENSE ("
                    + "NAME VARCHAR(255) NOT NULL PRIMARY KEY, "
                    + "AMOUNT DOUBLE NOT NULL)";
            stmt.executeUpdate(sql);
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
