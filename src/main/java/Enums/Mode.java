/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Enums;

import Database.Configuration;

/**
 *
 * @author nicol
 */
public enum Mode {
    PRODUCTION(Configuration.prodURL),
    TESTING(Configuration.testURL);

    private final String value;

    private Mode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
