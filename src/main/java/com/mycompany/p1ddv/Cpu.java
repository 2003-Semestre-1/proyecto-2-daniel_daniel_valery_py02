/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.p1ddv;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 *
 *Clase que se encarga de las etapas de ejecución de un programa. Incluye las 
 * etapas de fetch y execute.
 * 
 */
public class Cpu {
    int MAX_MAIN_MEMORY = 256;
    int MAX_DISK_MEMORY = 512;
    int MAX_STACK_SIZE = 10;
    int PC=11,AC=0;
    private Instruction IR = null;
    Instruction[] mainMemory;
    private int[] registros = {0,0,0,0};
    private Stack<Integer> stack;
    int ID;
    List<String> mensajes = new ArrayList<>();
    boolean idle=true;
    BCP runningProcess = null;
    int files=0;
    
//    private BCP bcp = null;
    
    private void ejecutarInterrup(int codigo){
        /*
        TODO Agregar logica para las interrupciones
        Conexión con la interfaz gráfica
        */
        switch (codigo) {
            case 1:
                idle = true;
                runningProcess.setEstado(Estado.FINALIZADO);
                break;
            default:
                throw new AssertionError();
        }
        
    }

    public Cpu(int id) {
        this.stack = new Stack<>();
        this.ID = id;
    }
    
    public void changeContext(BCP newProcess){
        this.PC = newProcess.getPC();
        this.registros = newProcess.getRegistros();
        this.AC = newProcess.getAC();
        this.stack = newProcess.getStack();
        this.idle = false;
    }
    
    public void setMemoria(Instruction[] mainMemory){
        this.mainMemory = mainMemory;
    }
    
    /**
     * Ejecuta la etapa de fetch.aumenta en 1 el PC y carga en IR la instruccion
 a ejecutar.
     * @param bcp
     */
    public void fetch(){
        IR = mainMemory[PC];
        PC = PC+1;
    }
    
    /**
     * Ejecuta la etapa de ejecución del CPU.Ejecuta la instruccion almacenada 
     * en el IR
     * @param bcp
     * @throws java.lang.Exception
     */
    public void execute(BCP bcp) throws Exception{
        if(bcp.CPU == this.ID){
            switch (IR.getInstruc()) {
                case 1 -> {
                    int peso=2;
                    double tiempoDuracion = peso * 0.001;
                    bcp.setTiempoEmpleado(tiempoDuracion);
                    AC = registros[IR.getArgumentos().get(0)-1];
                    bcp.AC = bcp.registros[IR.getArgumentos().get(0)-1];
                    mensajes.add("Valor cargado al AC");
                }
                case 2 -> {
                    int peso=2;
                    double tiempoDuracion = peso * 0.001;
                    bcp.setTiempoEmpleado(tiempoDuracion);
                    bcp.registros[IR.getArgumentos().get(0)-1] = bcp.AC;
                    registros[IR.getArgumentos().get(0)-1] = AC;                   
                    mensajes.add("Valor de AC almacenado en el registro destino" );
                }
                case 3 ->{
                    int peso=2;
                    double tiempoDuracion = peso * 0.001;
                    bcp.setTiempoEmpleado(tiempoDuracion);
                    bcp.registros[IR.getArgumentos().get(0)-1] = bcp.registros[IR.getArgumentos().get(1)-1];
                    registros[IR.getArgumentos().get(0)-1] = registros[IR.getArgumentos().get(1)-1];
                    mensajes.add("Valores movidos del registro origen al registro destino" );
                } 
                case 4 ->{
                    int peso=1;
                    double tiempoDuracion = peso * 0.001;
                    bcp.setTiempoEmpleado(tiempoDuracion);
                    bcp.registros[IR.getArgumentos().get(0)-1] = IR.getArgumentos().get(1);
                    registros[IR.getArgumentos().get(0)-1] = IR.getArgumentos().get(1);
                    mensajes.add("Valor movido al destino" );
                } 
                case 5 ->{
                    int peso=3;
                    double tiempoDuracion = peso * 0.001;
                    AC = AC-registros[IR.getArgumentos().get(0)-1];
                    bcp.setTiempoEmpleado(tiempoDuracion);
                    bcp.AC = bcp.AC-bcp.registros[IR.getArgumentos().get(0)-1];
                    mensajes.add("Valor del BX sumado al AC");
                } 
                case 6 ->{
                    int peso=3;
                    double tiempoDuracion = peso * 0.001;
                    bcp.setTiempoEmpleado(tiempoDuracion);
                    AC = AC+registros[IR.getArgumentos().get(0)-1];
                    bcp.AC = bcp.AC+bcp.registros[IR.getArgumentos().get(0)-1];
                    mensajes.add("Valor del BX restado al AC");
                } 
                case 7 ->{
                    int peso=1;
                    double tiempoDuracion = peso * 0.001;
                    bcp.setTiempoEmpleado(tiempoDuracion);
                    AC++;
                    bcp.AC++;
                    mensajes.add("Valor del AC incrementado en 1");
                }
                case 8 -> {
                    int peso=1;
                    double tiempoDuracion = peso * 0.001;
                    bcp.setTiempoEmpleado(tiempoDuracion);
                    registros[IR.getArgumentos().get(0)-1]++;
                    bcp.registros[IR.getArgumentos().get(0)-1]++;
                    mensajes.add("Valor del registro incrementado en 1");
                }
                case 9 -> {
                    int peso=1;
                    double tiempoDuracion = peso * 0.001;
                    bcp.setTiempoEmpleado(tiempoDuracion);
                    AC--;
                    bcp.AC--;
                    mensajes.add("Valor del AC decrementado en 1");
                }
                case 10 ->{
                    int peso=1;
                    double tiempoDuracion = peso * 0.001;
                    bcp.setTiempoEmpleado(tiempoDuracion);
                    registros[IR.getArgumentos().get(0)-1]--;
                    bcp.registros[IR.getArgumentos().get(0)-1]--;
                    mensajes.add("Valor del registro decrementado en 1");
                } 
                case 11 -> {
                    int peso=1;
                    double tiempoDuracion = peso * 0.001;
                    bcp.setTiempoEmpleado(tiempoDuracion);
                    int temp = registros[IR.getArgumentos().get(0)-1];
                    registros[IR.getArgumentos().get(0)] = registros[IR.getArgumentos().get(1)-1];
                    registros[IR.getArgumentos().get(1)-1] = temp;
                    
                    int temp1 = bcp.registros[IR.getArgumentos().get(0)-1];
                    bcp.registros[IR.getArgumentos().get(0)] = bcp.registros[IR.getArgumentos().get(1)-1];
                    bcp.registros[IR.getArgumentos().get(1)-1] = temp;
                    mensajes.add("Valores intercambiados entre los registros");
                }
                case 12 ->{
                    int peso=1;
                    double tiempoDuracion = peso * 0.001;
                    bcp.setTiempoEmpleado(tiempoDuracion);
                    ejecutarInterrup(IR.getArgumentos().get(0));
                    mensajes.add("Programa finalizado");
                } 
                case 13 -> {
                    int peso=1;
                    double tiempoDuracion = peso * 0.001;
                    bcp.setTiempoEmpleado(tiempoDuracion);
                    var newPC = PC + IR.getArgumentos().get(0);
                    if ( newPC < bcp.getBase() || newPC > bcp.getBase() + bcp.getAlcance() ){
                        throw new Exception("Error de acceso de memoria");
                    }
                    PC = newPC - 1;
                    mensajes.add("DX = ? ");
                    
                }
                case 14 -> {
                    int peso=1;
                    double tiempoDuracion = peso * 0.001;
                    bcp.setTiempoEmpleado(tiempoDuracion);
                    AC = registros[IR.getArgumentos().get(0)] - registros[IR.getArgumentos().get(1)];
                }
                case 15 -> {
                    int peso=1;
                    double tiempoDuracion = peso * 0.001;
                    bcp.setTiempoEmpleado(tiempoDuracion);
                    var newPC = PC + IR.getArgumentos().get(0);
                    if ( newPC < bcp.getBase() || newPC > bcp.getBase() + bcp.getAlcance() ) {
                        if (AC == 0){
                            PC = newPC - 1;
                        }
                    }
                }
                case 16 -> {
                    int peso=1;
                    double tiempoDuracion = peso * 0.001;
                    bcp.setTiempoEmpleado(tiempoDuracion);
                    var newPC = PC + IR.getArgumentos().get(0);
                    if ( newPC < bcp.getBase() || newPC > bcp.getBase() + bcp.getAlcance() ) {
                        if (AC != 0){
                            PC = newPC - 1;
                        }
                    }
                }
                case 17 -> {
                    int peso=1;
                    double tiempoDuracion = peso * 0.001;
                    bcp.setTiempoEmpleado(tiempoDuracion);
                    int argumentosSize = IR.getArgumentos().size();
                    if (stack.size() + argumentosSize > MAX_STACK_SIZE){
                        throw new Exception("Stack overflow");
                    }
                    for (int param : IR.getArgumentos()){
                        stack.push(param);
                    }
                }
                case 18 -> {
                    int peso=1;
                    double tiempoDuracion = peso * 0.001;
                    bcp.setTiempoEmpleado(tiempoDuracion);
                    if (stack.size() == MAX_STACK_SIZE){
                        throw new Exception("Stack overflow");
                    }
                    stack.push(registros[IR.getArgumentos().get(0)-1]);
                    mensajes.add("PUSH AX");
                }
                case 19 -> {
                    int peso=1;
                    double tiempoDuracion = peso * 0.001;
                    bcp.setTiempoEmpleado(tiempoDuracion);
                    if ( stack.isEmpty()){
                        throw new Exception("Error stack vacío");
                    }
                    registros[IR.getArgumentos().get(0)] = stack.pop();
                    mensajes.add("POP de AX");
                }
            }
            IR.bcp.setEstado(Estado.FINALIZADO);
        }
    }

    public int getMAX_MAIN_MEMORY() {
        return MAX_MAIN_MEMORY;
    }

    public int getMAX_DISK_MEMORY() {
        return MAX_DISK_MEMORY;
    }

    public int getMAX_STACK_SIZE() {
        return MAX_STACK_SIZE;
    }

    public int getPC() {
        return PC;
    }

    public int getAC() {
        return AC;
    }

    public Instruction getIR() {
        return IR;
    }

    public Instruction[] getMainMemory() {
        return mainMemory;
    }

    public int[] getRegistros() {
        return registros;
    }

    public Stack<Integer> getStack() {
        return stack;
    }

    public BCP getRunningProcess() {
        return runningProcess;
    }

    public void setRunningProcess(BCP runningProcess) {
        this.runningProcess = runningProcess;
    }
    
        

    @Override
    public String toString() {
        return "Registros : AX:" + this.registros[0] + " BX: " +
                this.registros[1] + " CX: " + this.registros[2] + " DX: " +
                this.registros[3] + "\n" + "AC: " + this.AC;
    }
    
    
    
}
