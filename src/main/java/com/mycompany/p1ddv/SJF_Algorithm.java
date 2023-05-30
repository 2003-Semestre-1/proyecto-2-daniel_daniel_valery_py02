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
public class SJF_Algorithm implements Planificador {
    private List<BCP> readyQueue;

    public SJF_Algorithm(List<BCP> readyQueue) {
        this.readyQueue = readyQueue;
    }

    @Override
    public BCP getNextProcess() {
        readyQueue.sort((o1, o2) -> o1.getRafaga()- o2.getRafaga() );
        BCP nextBCP = readyQueue.get(0);
        readyQueue.remove(0);
        return nextBCP;
    }
    
}
