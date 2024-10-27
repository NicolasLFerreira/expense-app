/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Expenses;

import Database.Manager;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nicol
 *
 * The new storage implementation. It implements the Storage interface so that
 * the application logic doesn't change when we change the implementation.
 *
 * Implements autoclosable to deal with the db connection when the object goes
 * unused
 */
public class DBExpenseStorage implements Storage, AutoCloseable {

    private final Manager dbManager;
    // i'm making the constructor handle the connection creation instead doing it in each method like before
    private final Connection connection;

    // dependency injection of the dbmanager
    public DBExpenseStorage(Manager dbManager) {
        this.dbManager = dbManager;
        this.connection = dbManager.getConnection();
    }

    // Note for self: ? is where the parameters will be inserted in a query
    @Override
    public Expense get(String name) {

        String query = "SELECT * FROM EXPENSE WHERE NAME = ?";

        // setups the statement
        try ( PreparedStatement statement = connection.prepareStatement(query)) {

            // adds the name to the statement
            statement.setString(1, name);

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                double amount = result.getDouble("AMOUNT");
                return new Expense(name, amount);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBExpenseStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
        // if failed. to be handled wherever it's used.
        return null;
    }

    @Override
    public void set(Expense expense) {
        
        // update command
        String queryU = "UPDATE EXPENSE SET amount = ? WHERE name = ?";
        
        // insert command
        String queryI = "INSERT INTO EXPENSE (name, amount) VALUES (?, ?)";
        
        try {
            int rowsAffected;
            try ( // performs an update query
                    PreparedStatement statementU = connection.prepareStatement(queryU)) {
                statementU.setDouble(1, expense.getAmount());
                statementU.setString(2, expense.getName());
                // execute command and return number of affected rows to check
                // if it worked
                rowsAffected = statementU.executeUpdate();
            }

            // if nothing updated does an insert
            if (rowsAffected == 0) {
                try (PreparedStatement statementI = connection.prepareStatement(queryI)) {
                    statementI.setString(1, expense.getName());
                    statementI.setDouble(2, expense.getAmount());
                    statementI.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBExpenseStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setAll(Expense[] expenses) {
        // just calls the set for each item of the array
        for (Expense expense : expenses) {
            set(expense);
        }
    }

    @Override
    public boolean remove(String name) {
        String query = "REMOVE FROM EXPENSE WHERE NAME = '?'";

        try ( PreparedStatement statement = connection.prepareStatement(query)) {
            // sets name param
            statement.setString(1, name);

            // return true if successful
            if (statement.executeUpdate() == 1) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBExpenseStorage.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public void clear() {
        // from what I found online this should do the trick
        // gotta test to see if it works

        String query = "DELETE FROM EXPENSE";

        try ( PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBExpenseStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Expense[] getArray() {
        // temp storage
        ArrayList<Expense> list = new ArrayList<>();

        String query = "SELECT * FROM EXPENSE";

        try ( PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();

            // loops through response
            while (rs.next()) {
                list.add(new Expense(rs.getString("NAME"), rs.getDouble("AMOUNT")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBExpenseStorage.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list.toArray(Expense[]::new);
    }

    // I had to add this function for the db connection to get automatically closed
    @Override
    public void close() {
        // dbManager deals with the closing logic,
        // no need to call connection.close directly and deal with the trycatch
        dbManager.closeConnection();
    }
}
