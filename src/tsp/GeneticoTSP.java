/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Roberto Cruz Leija
 */
public class GeneticoTSP {
    // parametros
    private Poblacion poblacionActual;
    private int numG,tamP;
    private double pMuta;

    public GeneticoTSP(int numG, int tamP, int numCd, int ci, double pMuta) {
        this.numG = numG;
        this.tamP = tamP;
        this.pMuta = pMuta;
        this.poblacionActual = new Poblacion(tamP,numCd,ci);
        //mejor();
    }
    
    
    public GeneticoTSP(int numG, int tamP,  double pMuta, Poblacion p) {
        this.numG = numG;
        this.tamP = tamP;
        this.pMuta = pMuta;
        this.poblacionActual = p;
        //mejor();
    }
    
    public static double gasto(int v, double d, int m, double h, int t){
        double g = 9.8;
        double t1 = ((v/t)+g*Math.cos(Math.asin(h/d)))*m*(Math.pow(v, 2)/(2*g*Math.cos(Math.asin(h/d))));
        double t2 = m*(g*Math.cos(Math.asin(h/d)))*(d-(Math.pow(v, 2)/(2*g*Math.cos(Math.asin(h/d)))));
        return t1+t2;
    }
    
    public void evolucionar(){
        Grafica grafica = new Grafica("Puntos ", "Distancias","Inclinaciones");
        grafica.crearSerie("0");
        Individuo mejor = null;
        int v = 100;
        int m = 2000;
        int t = 3;
    // generar las itereaciones para las generaciones
    for(int g=1;g<this.numG;g++){
        // garantizar construir una nueva población
        ArrayList<Individuo> ind = new ArrayList<>();
        for(int i=0; i<this.tamP;i++){
            // seleccionamos
            Individuo madre = Seleccion.seleccionTorneo(this.poblacionActual);
            Individuo padre = Seleccion.seleccionAleatoria(this.poblacionActual);
            
            // reproducimos
            Individuo hijo = Cruza.cruzaAsexual(madre, padre);
            // mutamos 
                // evaluar la probabilidad
            Muta.mutaGen(pMuta, hijo);
            // agregamos
            ind.add(hijo);
        }
        // actualizamos la nueva población
        this.poblacionActual = new Poblacion(ind);
       // pedimos el mejor a la poblacion 
       mejor  = this.poblacionActual.getMejor();
       
       grafica.crearPuntoASerie("0",mejor.getFitnessDistancia(),mejor.getFitnessInclinacion());
       System.out.println(g+": "+mejor.getFitnessGeneral()+" Inc: "+mejor.getFitnessInclinacion()
               +" Dis: "+mejor.getFitnessDistancia()+" gasto: "+gasto(v,mejor.getFitnessDistancia(),m,mejor.getFitnessInclinacion(),t));
       mejor.actualizarIndividuo();
    }
    
    grafica.crearGrafica();
    
    }

//    public static void main(String[] args){      
//        Herramientas.cargarDistancias();
//        GeneticoTSP g = new GeneticoTSP(100000,100,Herramientas.distancias.length,0,0.001); 
//        g.evolucionar();   
//    }  
}
