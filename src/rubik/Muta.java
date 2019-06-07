/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubik;

import java.util.Random;

/**
 *
 * @author CESAR IVAN MTZ
 */
public class Muta {
    
  
    
    public static void mutaGen(double prob, Cubo cub){
    // evaluar la probabilidad 
    double aux = Math.random();
    if(aux<=prob){
        Cubo help = cub;
        // modificar un bit del genotipo
        Random ran = new Random();
        //ran.nextInt(12)+1;
        int posGen = ran.nextInt(cub.getGenotipo().length);
        int posR = ran.nextInt(12)+1;;
        cub.getGenotipo()[posGen]=posR;
//        if(posGen!=0)
//        if(ran.nextInt(2)==0) cub.getGenotipo()[posGen-1]=posR;
        // actualizamos el fenotipo y el fitness
        cub.calcularFitness();   
    }  
    }
    
    public static void main(String args[]){
    String aux = "1,2,3,4,5";
    String arreglo[]= aux.split(",");
    int arr[] = new int[arreglo.length];
     for(int i=0;i<arreglo.length;i++)
           arr[i] = Integer.parseInt(arreglo[i]);
     
        System.out.println("");
    }
}