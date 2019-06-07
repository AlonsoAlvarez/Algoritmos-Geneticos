/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libre;

import java.util.Random;

/**
 *
 * @author Roberto Cruz Leija
 */
public class Muta {
   public static void mutaGen(double prob,Individuo ind){
       double aux = Math.random();
        if(aux<=prob){
            // modificar un bit del genotipo
            Random ran = new Random();
            int can = ran.nextInt(ind.getGenotipo().length);
            int temp;
            for (int i = 0; i < can; i++) {
                temp=ran.nextInt(ind.getGenotipo().length);
                if(temp==ind.getGenotipo().length-1){
                    ind.getGenotipo()[temp]=ran.nextInt(3)+3;
                }else{
                    if(ind.getGenotipo()[temp]==1){
                        ind.getGenotipo()[temp]=0;
                    }else{
                        ind.getGenotipo()[temp]=1;
                    }
                }
            }
//            int posGen = ran.nextInt(ind.getGenotipo().length);
//            int valor =ind.getGenotipo()[posGen]; 
//            int posGen2= ran.nextInt(ind.getGenotipo().length);
//            if(posGen==ind.getGenotipo().length-1 || posGen2==ind.getGenotipo().length-1){
//                ind.getGenotipo()[posGen] = ind.getGenotipo()[posGen2]+3; 
//                ind.getGenotipo()[posGen2] = valor+3; 
//            }else{
//                if(1<ind.getGenotipo()[posGen2] || 1<valor){
//                    ind.getGenotipo()[posGen] = ind.getGenotipo()[posGen2]-3; 
//                    ind.getGenotipo()[posGen2] = valor-3; 
//                }else{
//                    ind.getGenotipo()[posGen] = ind.getGenotipo()[posGen2]; 
//                    ind.getGenotipo()[posGen2] = valor; 
//                }
//            }

            // actualizamos el fenotipo y el fitness
            ind.actualizarIndividuo();

        }     
   }
}
