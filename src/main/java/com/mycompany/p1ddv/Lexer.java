/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.p1ddv;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 
 */
public class Lexer {
    
    
    public List<Token> tokenize(List<String> inputString){
        List<Token> result = new ArrayList<Token>();
        
        for (String linea : inputString){
            String[] partesLinea = linea.split(" ",2);
            String[] argumentos = new String[]{};
            if (partesLinea.length > 1){
                argumentos = partesLinea[1].replace(",", "")
                        .split(" ");
            }
            result.add(new Token(partesLinea[0],argumentos));
        }
        return result;
    }
}
