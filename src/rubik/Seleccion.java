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
public class Seleccion {
    public enum TipoSeleccion{RANDOM,TORNEO}
    public enum TipoMuestreo{MEJOR}
    
    public static Cubo seleccionTorneo(Poblacion pob){
        Cubo mejor = new Cubo(pob.getMejor().getGenotipo(), pob.getMejor().getEstadoMascaras());
        return mejor;
    }
    
    public static Cubo seleccionAleatoria(Poblacion pob){
        Random ran = new Random();
        int pos = ran.nextInt(pob.getIndivduos().size());
        Cubo mejor = new Cubo(pob.getIndivduos().get(pos).getGenotipo(), 
                              pob.getIndivduos().get(pos).getEstadoMascaras());
        return mejor;
    }
    

}
