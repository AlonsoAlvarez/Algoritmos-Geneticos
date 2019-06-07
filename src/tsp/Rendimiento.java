/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;

/**
 *
 * @author Usuario 1
 */
public class Rendimiento {
    
    public static double combustible(int velocidadDeseada, int distancia, int masa, int altura, int tiempo, int energiaCombustible){
        double t1 = ((velocidadDeseada/tiempo)+(9.8*Math.cos(Math.asin(altura/distancia))))*masa*(Math.pow(velocidadDeseada, 2)/(2*9.8*Math.cos(Math.asin(altura/distancia))));
        double t2 = masa*(9.8*Math.cos(Math.asin(altura/distancia)))*(distancia-(Math.pow(velocidadDeseada, 2)/(2*9.8*Math.cos(Math.asin(altura/distancia)))));
        double trabajoTotal = t1 + t2;
        return trabajoTotal/energiaCombustible;
    }
    
}
