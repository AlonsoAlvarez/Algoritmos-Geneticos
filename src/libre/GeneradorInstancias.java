/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libre;

import java.util.ArrayList;

/**
 *
 * @author Roberto Cruz Leija
 */
public class GeneradorInstancias {
    
    public static ArrayList<Patron> generarInstancias(int[]carateristicas){
        if(carateristicas[carateristicas.length-1]==1){
            System.out.println("ya vlaio");
        }
        ArrayList<Patron> aux = new ArrayList<>();
        
//        int unos = 0;
//        
//        for (int x=0;x<carateristicas.length-1;x++)
//         if (carateristicas[x]==1)unos++;
        
        // recorremos las instancias
        for(Patron pOriginal: Tokenizador.instancias){
            // asegurar la creacion de un patron 
            Patron pNuevo;
            String clase = pOriginal.getClaseOriginal();
            double vectorN [] = new double[carateristicas.length];
            int j = 0;
            for (int i=0;i<carateristicas.length;i++){
              if(carateristicas[i]==1){
                vectorN [j] = pOriginal.getCaracteristicas()[i];
                j++;
              }else{
                  vectorN [j] = 0;
                j++;
              }
            }
            pNuevo = new Patron(vectorN, clase);
            aux.add(pNuevo);
        }
         return aux;  
    }
    
}
