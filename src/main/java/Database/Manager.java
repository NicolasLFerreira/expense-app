/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Database;

import java.sql.Connection;

/**
 *
 * @author nicol
 * 
 * Interface for abstracting the actual implementation of the db manager.
 * Contains only the relevant stuff for code consuming a db manager.
 */
public interface Manager {
    public Connection getConnection();
    public void closeConnection();
}
