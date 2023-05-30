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
public class EstrategiaManager {
    
    Estrategia estrategia;
    List<BCP> readyQueue;
    Planificador planificador;
    

    public EstrategiaManager(Estrategia estrategia, List<BCP> readyQueue) {
        this.estrategia = estrategia;
        this.readyQueue = readyQueue;
        this.setPlanificador(estrategia, readyQueue);        
    }
    
    public BCP getProcess(){
        if(readyQueue.isEmpty()){
            return null;
        }
        return planificador.getNextProcess();
    }
    
    /* Metodo para cambiar estrategia si el usuario cambia datos en el menú de configuración */
    public void changeEstrategia(Estrategia estrategia, List<BCP> readyQueue){
        this.setPlanificador(estrategia, readyQueue);
    }

    public Planificador getPlanificador() {
        return planificador;
    }
    
    private void setPlanificador(Estrategia estrategia, List<BCP> readyQueue){
        switch (estrategia) {
            case FCFS:
                planificador = new FCFS_Algorithm(readyQueue);
                break;
            case SRT:
                planificador = new SRT_Algorithm(readyQueue);
                break;
            case SJF:
                planificador = new SJF_Algorithm(readyQueue);
                break;
            case RR:
                planificador = new RR_Algorithm(readyQueue);
                break;
            case HRRN:
                break;
        }
    }
    
    
    
}
