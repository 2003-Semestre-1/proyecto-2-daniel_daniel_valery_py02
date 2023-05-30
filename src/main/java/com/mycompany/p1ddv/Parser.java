/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.p1ddv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * Clase que se encarga de parsear el programa asm ingresado
 */
public class Parser {
    
    /**
     * Metodo que procesa el archivo con el programa y que obtiene las instrucciones
     * y a su vez, se asegura de que la estructura del programa sea correcta.
     * 
     * @param tokens lista conteniendo los tokens generados en el procesamiento
     * lexico
     * @param cpu
     * @return Una lista con las instrucciones del programa procesado.
     * @throws IOException
     * @throws Exception 
     */
    public List<Instruction> parse(List<Token> tokens, int cpu) throws Exception{
        List<Instruction> instrucs = new ArrayList<>();
        Instruction instruc;
        for (Token token : tokens){
            instruc = getInstruccion(token);
            instruc.token = token;
            instruc.bcp = new BCP(cpu);
            instrucs.add(instruc);
        }
        
        return instrucs;
    }
    
    /**
     * Obtiene la codificación de la información de un token para generar
     * una instrucción
     * 
     * @param token
     * @return Una instrucción formada por la información del token
     */
    private Instruction getInstruccion(Token token) throws Exception{
        String nombreInstruccion = token.getInstruc();
        int instruccion = -1;
        List<Integer> argumentos = new ArrayList<>();
        String[] tokenArgs = token.getArgumentos();
        
        switch (nombreInstruccion) {
            case "LOAD" -> {
                instruccion = 1;
                if (tokenArgs.length == 1){
                    argumentos.add(getCodigoRegistro(tokenArgs[0]));
                }else{
                    throw new Exception(
                            "Error de formato en instrucción 'LOAD'");
                }
            }
            case "STORE" -> {
                instruccion = 2;
                if (tokenArgs.length == 1){
                    argumentos.add(getCodigoRegistro(tokenArgs[0]));
                }else{
                    throw new Exception(
                            "Error de formato en instrucción 'STORE'");
                }
            }
            case "MOV" -> {
                instruccion = !isNumber(tokenArgs[1]) ? 3 : 4;
                if (tokenArgs.length == 2){
                    argumentos.add(getCodigoRegistro(tokenArgs[0]));
                    if (instruccion == 4){
                        argumentos.add(Integer.valueOf(tokenArgs[1]));
                    }else argumentos.add(getCodigoRegistro(tokenArgs[1]));
                }else{
                    throw new Exception(
                            "Error de formato en instrucción 'LOAD'");
                }
            }
            case "SUB" -> {
                instruccion = 5;
                if (tokenArgs.length != 1){
                    throw new Exception(
                            "Error de formato en instrucción 'SUB'");
                }
                argumentos.add(getCodigoRegistro(tokenArgs[0]));
            }
            case "ADD" -> {
                instruccion = 6;
                if (tokenArgs.length != 1){
                    throw new Exception(
                            "Error de formato en instrucción 'ADD'");
                }
                argumentos.add(getCodigoRegistro(tokenArgs[0]));
            }
            case "INC" -> {
                instruccion = tokenArgs.length == 0 ? 7 : 8;
                if ((instruccion == 7 && tokenArgs.length != 0) ||
                        ( instruccion == 8 && tokenArgs.length != 1)){
                    throw new Exception(
                            "Error de formato en instrucción 'INC'");
                }
                if (tokenArgs.length == 1){
                    argumentos.add(getCodigoRegistro(tokenArgs[0]));
                }
            }
            case "DEC" -> {
                instruccion = tokenArgs.length == 0 ? 9 : 10;
                if ((instruccion == 9 && tokenArgs.length != 0) ||
                        ( instruccion == 10 && tokenArgs.length != 1)){
                    throw new Exception(
                            "Error de formato en instrucción 'DEC'");
                }
                if (tokenArgs.length == 1){
                    argumentos.add(getCodigoRegistro(tokenArgs[0]));
                }
            }
            case "SWAP" -> {
                instruccion = 11;
                if (tokenArgs.length == 2){
                    argumentos.add(getCodigoRegistro(tokenArgs[0]));
                    argumentos.add(getCodigoRegistro(tokenArgs[1]));
                }else{
                    throw  new Exception(
                            "Error de formato en instruccion 'SWAP'");
                }
            }
            case "INT" -> {
                instruccion = 12;
                if (tokenArgs.length == 1){
                    argumentos.add(getCodigoInterrupcion(tokenArgs[0]));
                }else{
                    throw  new Exception(
                            "Error de formato en instruccion 'INT'");
                }
            }
            case "JMP" -> {
                instruccion = 13;
                if (tokenArgs.length == 1){
                    argumentos.add(Integer.valueOf(tokenArgs[0]));
                }else{
                    throw  new Exception(
                            "Error de formato en instruccion 'JMP'");
                }
            }
            case "CMP" -> {
                instruccion = 14;
                if (tokenArgs.length == 2){
                    argumentos.add(getCodigoRegistro(tokenArgs[0]));
                    argumentos.add(getCodigoRegistro(tokenArgs[1]));
                }else{
                    throw  new Exception(
                            "Error de formato en instruccion 'CMP'");
                }
            }
            case "JE" -> {
                instruccion = 15;
                if (tokenArgs.length == 1){
                    argumentos.add(Integer.valueOf(tokenArgs[0]));
                }else{
                    throw  new Exception(
                            "Error de formato en instruccion 'JE'");
                }
            }
            case "JNE" -> {
                instruccion = 16;
                if (tokenArgs.length == 1){
                    argumentos.add(Integer.valueOf(tokenArgs[0]));
                }else{
                    throw  new Exception(
                            "Error de formato en instruccion 'JNE'");
                }
            }
            case "PARAM" -> {
                instruccion = 17;
                if (tokenArgs.length > 4){
                    for (String parametro : tokenArgs){
                        argumentos.add(Integer.valueOf(parametro));
                    }
                }else{
                    throw  new Exception(
                            "Error de formato en instruccion 'PARAM'");
                }
            }
            case "PUSH" -> {
                instruccion = 18;
                if (tokenArgs.length == 1){
                    argumentos.add(getCodigoRegistro(tokenArgs[0]));
                }else{
                    throw  new Exception(
                            "Error de formato en instruccion 'PUSH'");
                }
            }
            case "POP" -> {
                instruccion = 19;
                if (tokenArgs.length == 1){
                    argumentos.add(getCodigoRegistro(tokenArgs[0]));
                }else{
                    throw  new Exception(
                            "Error de formato en instruccion 'PUSH'");
                }
            }
            default -> throw  new Exception(
                            "Instricción '" + nombreInstruccion + "' no soportada");
        }
        
        return new Instruction(instruccion, argumentos);
        
    }
    
    private int getCodigoInterrupcion(String instruccion) throws Exception{
        return switch(instruccion){
            case "20H" -> 1;
            case "10H" -> 2;
            case "09H" -> 3;
            case "21H" -> 4;
            default -> throw new Exception(
                    "Instrucción '" + instruccion + "' no existe");
        };
    }
    
    /**
     * Devuelve el código relacionado a la representación en texto del registro
     * 
     * @param nombre
     * @return entero que representa el registro de CPU.
     */
    private int getCodigoRegistro(String nombre) throws Exception{
        return switch (nombre) {
            case "AX" -> 1;
            case "BX" -> 2;
            case "CX" -> 3;
            case "DX" -> 4;
            default -> throw new Exception("Registro '" + nombre + "' no existe");
        };
    }
    
    private boolean isNumber(String number){
        try {
            Integer.valueOf(number);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
    
}
