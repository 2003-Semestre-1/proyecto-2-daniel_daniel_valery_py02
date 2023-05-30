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
public class RR_Algorithm implements Planificador {
    private List<BCP> readyQueue;
    private int quanta = 3;
    private int actualQuanta = 0;
    

    public RR_Algorithm(List<BCP> readyQueue) {
        this.readyQueue = readyQueue;
    }
    
    public RR_Algorithm(List<BCP> readyQueue, int quanta) {
        this.readyQueue = readyQueue;
        this.quanta = quanta;
    }

    public int getQuanta() {
        return quanta;
    }

    public void setQuanta(int quanta) {
        this.quanta = quanta;
    }

    public int getActualQuanta() {
        return actualQuanta;
    }

    public void setActualQuanta(int actualQuanta) {
        this.actualQuanta = actualQuanta;
    }
    
    public void incrementQuanta(){
        actualQuanta++;
    }
    
    public void restartQuanta(){
        actualQuanta = quanta;
    }
    
    public boolean isQuantaExpired(){
        return actualQuanta > quanta;
    }
    

    @Override
    public BCP getNextProcess() {
        BCP nextBCP = readyQueue.get(0);
        readyQueue.remove(0);
        return nextBCP;
    }
    
}
