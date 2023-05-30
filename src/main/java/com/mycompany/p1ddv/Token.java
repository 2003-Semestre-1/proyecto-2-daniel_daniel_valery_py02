/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.p1ddv;

/**
 *
 * @author aloji
 */
public class Token {
    String instruc;
    String[] argumentos;

    public Token(String instruc, String[] argumentos) {
        this.instruc = instruc;
        this.argumentos = argumentos;
    }

    public String getInstruc() {
        return instruc;
    }

    public String[] getArgumentos() {
        return argumentos;
    }    
}
