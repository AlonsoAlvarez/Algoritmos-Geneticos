/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libre;

import java.util.ArrayList;

/**
 *
 * @author Usuario 1
 */
public class Main2 {
    
    public static void main(String args[]){
//        Tokenizador.leerDatos();
//        ArrayList<Patron> aux = GeneradorInstancias.generarInstancias(new byte[]{1,1,1,1,1,1});
//        Knn knn = new Knn(6);
//        knn.entrena(aux);
//        knn.clasificaConjunto(aux);
//        System.out.println("Eficacia knn: "+knn.getEficacia());
        
        
        
        Tokenizador.leerDatos();

        GenLibre gen = new GenLibre(100, 3, 0.5);
//        gen.evolucionar();
        Thread h = new Thread(gen);
        h.start();
        System.out.println();
        
    }
}
