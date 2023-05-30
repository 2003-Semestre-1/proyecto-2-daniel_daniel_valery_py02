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
public class SRT_Algorithm implements Planificador {
    private List<BCP> readyQueue;
    private int currentTimeUnit;

    public SRT_Algorithm(List<BCP> readyQueue) {
        this.readyQueue = readyQueue;
        this.currentTimeUnit = 0;
    }
    
    public void incCurrentTimeUnit(){
        currentTimeUnit++;
    }

    @Override
    public BCP getNextProcess() {
        readyQueue.sort((o1, o2) -> o1.getRafaga()- o2.getRafaga() );
        BCP nextBCP = null;
        for(int i = 0; i < readyQueue.size(); i++){
            if(readyQueue.get(i).getTiempoLlegada() <= currentTimeUnit){
                nextBCP = readyQueue.get(i);
                readyQueue.remove(i);
            }
        }
        return nextBCP;
    }
    
    public BCP getShortestRemainingTime(BCP currentBCP){
        readyQueue.sort((o1, o2) -> o1.getRafaga()- o2.getRafaga());
        BCP nextBCP = null;
        for(int i = 0; i < readyQueue.size(); i++){
            if(readyQueue.get(i).getTiempoLlegada() <= currentTimeUnit
                    && readyQueue.get(i).getRafaga() < currentBCP.getRafagaFaltante()){
                nextBCP = readyQueue.get(i);
                readyQueue.remove(i);
            }
        }
        return nextBCP;
    }
}
