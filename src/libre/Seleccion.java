/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libre;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Roberto Cruz Leija
 */
public class Seleccion {
    
    public enum TipoSeleccion{RANDOM,TORNEO}
    public enum TipoMuestreo{MEJOR}
    
    public static Individuo seleccionTorneo(Poblacion pob,  ArrayList<Patron> aux){
        Individuo mejor = new Individuo(pob.getMejor().getGenotipo(), aux);
        return mejor;
    }
    public static Individuo seleccionAleatoria(Poblacion pob,  ArrayList<Patron> aux){
        Random ran = new Random();
        int pos = ran.nextInt(pob.getIndivduos().size());
        Individuo mejor = new Individuo(pob.getIndivduos()
                   .get(pos).getGenotipo(), aux);
        return mejor;
    }
    
}
