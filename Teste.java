/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package representante.comercial.model;

/**
 *
 * @author L
 */
public class Teste {
    private Cliente c;
    private int i;
    
    private static Teste t;

    private Teste() {
        
    }
    
    public static Teste createTeste(){
        if (t == null){
            t = new Teste();
        }
        return t;
    }
    
    

    public Cliente getC() {
        return c;
    }

    public void setC(Cliente c) {
        this.c = new Cliente(c.getNome(), c.getCpf());
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}
