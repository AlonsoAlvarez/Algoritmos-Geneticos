/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;

import java.util.ArrayList;

/**
 *
 * @author Roberto Cruz Leija
 */
public class TSPMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
    // Herramientas.generarDistanciasAletorias(15, 1000);
     Herramientas.cargarDistancias();
     Herramientas.generarInclinacionesAletorias(50, 150);
       
     GeneticoTSP tsp = new  GeneticoTSP(1000, 50, 50, 0, .2);
     tsp.evolucionar();
       
     
//        double r1[] = new double[]{0,34.5,22.1,3,0.7};
//        double r2[] = new double[]{34.5,0,11,45,895};
//        double r3[] = new double[]{22.1,11,0,67.6,333};
//        double r4[] = new double[]{3,45,67.6,0,2};
//        double r5[] = new double[]{0.7,895,333,2,0};
//        
//        //double inc[] = new double[]{10.7, 30.5, 5.8, 9.0, 15.7};
//        double inc[] = new double[]{10, 40, 37, 45, 60};
//        
//        Herramientas.distancias = new double[][]{r1,r2,r3,r4,r5};
//        Herramientas.inclinaciones = inc;
       
       
//        Individuo i1 = new Individuo(5,4);
//        Individuo i2 = new Individuo(5,0);
//        Individuo i3 = new Individuo(5,2);
//        Individuo i4 = new Individuo(5,1);
//        Individuo i5 = new Individuo(5,3);
//        
//        ArrayList<Individuo> aInd = new ArrayList<>();
//        aInd.add(i1);
//         aInd.add(i2);
//          aInd.add(i3);
//           aInd.add(i4);
//            aInd.add(i5);
//            
//        Poblacion p = new Poblacion(aInd);
//        GeneticoTSP tsp = new GeneticoTSP(500, aInd.size(), .2, p);
//        tsp.evolucionar();
        
        System.out.println();
    }
    
}
