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
public class Main {
    
    public static void main(String args[]){
                 Tokenizador.leerDatos();

        ArrayList<Patron> aux = GeneradorInstancias.generarInstancias(new int[]{1,1,1,1});
        Knn knn = new Knn(6);
        knn.entrena(aux);
        knn.clasificaConjunto(aux);
        System.out.println("Eficacia knn: "+knn.getEficacia());
        
       MinimaDistancia md = new MinimaDistancia();
       md.entrena(aux);
       md.clasificaConjunto(aux);
       System.out.println("Eficacia minima distancia: "+md.getEficacia());
       
       NaiveBayes nb = new NaiveBayes();
       nb.entrena(aux);
       nb.clasificaConjunto(aux);
       System.out.println("Bayes: "+nb.getEficacia());
    }
}
