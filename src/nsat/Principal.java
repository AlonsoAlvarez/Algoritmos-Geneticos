/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsat;

/**
 *
 * @author Usuario 1
 */
public class Principal {
    
    public static void main(String args[]){
        
        Tokenizador.leerDatos();

        GeneticoNSAT gen = new GeneticoNSAT(10000, 80, 0.25);
//        gen.evolucionar();
        Thread h = new Thread(gen);
        h.start();
        System.out.println();
        
    }
}
