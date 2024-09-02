/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Enums;

/**
 *
 * @author nicolas
 */
public enum ScreenType {
    MENU("Student Budget App v1.0"),
    HELP("Help Screen"),
    BUDGET("Budget Manager");

    private final String title;

    private ScreenType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
