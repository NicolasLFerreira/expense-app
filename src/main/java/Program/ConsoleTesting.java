/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Program;

import Expenses.*;

/**
 *
 * @author nicol
 */
public class ConsoleTesting {

    public static void main(String[] args) {
        BudgetManager bm = new BudgetManager();
        
        FinancialRecord fr = new FinancialRecord("Name", 285);
        
        bm.addIncome(fr);
        
        System.out.println(bm.getIncome("Name"));
    }
}
