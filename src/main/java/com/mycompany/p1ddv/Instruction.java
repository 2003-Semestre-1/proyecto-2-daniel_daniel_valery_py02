/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.p1ddv;

import java.util.List;

/**
 *
 * @author 
 */
public class Instruction {
    int instruc;
    List<Integer> argumentos;
    Token token;
    BCP bcp;

    public Instruction(int instruc, List<Integer> argumentos) {
        this.instruc = instruc;
        this.argumentos = argumentos;
    }

    public int getInstruc() {
        return instruc;
    }

    public List<Integer> getArgumentos() {
        return argumentos;
    }
    
    
}
