/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.p1ddv;

import java.util.List;
import java.util.Stack;

/**
 *
 * @author
 */
public class BCP {
    Estado estado;
    int PC,AC=0;
    int[] registros;
    private Stack<Integer> stack;
    int CPU;
    double tiempoInicio;
    double tiempoEmpleado;
    private List<String> archivos;
    private BCP siguienteBCP;
    int base;
    private int alcance;
    private int prioridad;
    private int tiempoLlegada;
    private int rafaga;
    private int rafagaFaltante;

    public BCP(int cpu) {
        estado = Estado.NUEVO;
        registros = new int[] {0,0,0,0};
        stack = new Stack<>();
        tiempoEmpleado = 0;
        siguienteBCP = null;
        this.CPU = cpu;
        prioridad = 1;
        
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getPC() {
        return PC;
    }

    public void setPC(int PC) {
        this.PC = PC;
    }

    public int[] getRegistros() {
        return registros;
    }

    public void setRegistros(int[] registros) {
        this.registros = registros;
    }

    public Stack<Integer> getStack() {
        return stack;
    }

    public void setStack(Stack<Integer> stack) {
        this.stack = stack;
    }

    public int getCPU() {
        return CPU;
    }

    public void setCPU(int CPU) {
        this.CPU = CPU;
    }

    public double getTiempoInicio() {
        return tiempoInicio;
    }

    public void setTiempoInicio(double tiempoInicio) {
        this.tiempoInicio = tiempoInicio;
    }

    public double getTiempoEmpleado() {
        return tiempoEmpleado;
    }

    public void setTiempoEmpleado(double tiempoEmpleado) {
        this.tiempoEmpleado = tiempoEmpleado;
    }

    public List<String> getArchivos() {
        return archivos;
    }

    public void setArchivos(List<String> archivos) {
        this.archivos = archivos;
    }

    public BCP getSiguienteBCP() {
        return siguienteBCP;
    }

    public void setSiguienteBCP(BCP siguienteBCP) {
        this.siguienteBCP = siguienteBCP;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getAlcance() {
        return alcance;
    }

    public void setAlcance(int alcance) {
        this.alcance = alcance;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public int getTiempoLlegada() {
        return tiempoLlegada;
    }

    public void setTiempoLlegada(int tiempoLlegada) {
        this.tiempoLlegada = tiempoLlegada;
    }

    public int getRafaga() {
        return rafaga;
    }

    public void setRafaga(int rafaga) {
        this.rafaga = rafaga;
        this.rafagaFaltante = rafaga;
    }

    public int getAC() {
        return AC;
    }

    public void setAC(int AC) {
        this.AC = AC;
    }
    
    public void decRafaga(){
        this.rafagaFaltante--;
    }

    public int getRafagaFaltante() {
        return rafagaFaltante;
    }

    public void setRafagaFaltante(int rafagaFaltante) {
        this.rafagaFaltante = rafagaFaltante;
    }
    
    
    
    
    
    
    
}
