/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Program;

import File.FileManager;

/**
 *
 * @author will & nicolas
 *
 * Main application class to run the Student Budget Calculator program. This
 * program allows users to manage their budgets by adding expenses, calculating
 * the remaining budget, and saving or loading expenses to/from a file.
 */
public class StudentBudgetApp {

    public static void main(String[] args) {
        FileManager.ensure();
        ScreenManager manager = new ScreenManager();

        // Loops infinetely.
        while (true) {
            manager.display();
        }
    }
}
