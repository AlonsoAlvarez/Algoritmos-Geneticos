/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libre;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Usuario 1
 */
public class GenLibre implements Runnable {
   // parametros
    private Poblacion poblacionActual;
    private int numG,tamP;
    private double pMuta;
    private Configuracion manager;
     ArrayList<Patron> aux = GeneradorInstancias.generarInstancias(new int[]{1,1,1,1});
    
    public int getNumG() {
        return numG;
    }

    public int getTamP() {
        return tamP;
    }

    public double getpMuta() {
        return pMuta;
    }

    
    
    public void setManager(Configuracion manager) {
        this.numG = manager.getNumGeneraciones();
        this.tamP = manager.getTamPoblacion();
        this.pMuta = manager.getProbMuta();
        this.poblacionActual = new Poblacion(tamP);
        this.manager = manager;
    }
    
    public void setMejor(Individuo ind){
        this.tamP++;
        this.poblacionActual.add(ind);
    }
    
    public Individuo getMejor(){
        return this.poblacionActual.getMejor();
    }
    
    
    public GenLibre(int numG, int tamP, double pMuta) {
        this.numG = numG;
        this.tamP = tamP;
        this.pMuta = pMuta;
        this.poblacionActual = new Poblacion(tamP);
        //mejor();
    }
    
    
    
    public GenLibre() {
    }


    public GenLibre(Configuracion manager) {
        this.manager = manager;
        this.poblacionActual = new Poblacion(this.manager.getTamPoblacion(),this.manager.getTamGenotipo());
        this.numG = manager.getNumGeneraciones();
        this.tamP = manager.getTamPoblacion();
        this.pMuta = manager.getProbMuta();
        this.poblacionActual = new Poblacion(tamP);
    }
    
    
    Individuo mejor = null;
    
    public void evolucionar( ArrayList<Patron> aux){
    int mask[] = Cruza.generarMascaraAleatoria(Tokenizador.instancias.get(0).getCaracteristicas().length+1);
    
    // generar las itereaciones para las generaciones
    for(int g=1;g<this.numG;g++){
        // garantizar construir una nueva población
        ArrayList<Individuo> ind = new ArrayList<>();
        for(int i=0; i<this.poblacionActual.size();i++){
            // seleccionamos
            Individuo madre = Seleccion.seleccionTorneo(this.poblacionActual,aux);
            Individuo padre = Seleccion.seleccionAleatoria(this.poblacionActual,aux);
            
            // reproducimos
            Individuo hijo = Cruza.cruzaXMascara(mask, madre, padre, aux);
            // mutamos 
                // evaluar la probabilidad
            Muta.mutaGen(pMuta, hijo);
            // agregamos
            this.aux = GeneradorInstancias.generarInstancias(hijo.getGenotipo());
            hijo.calcularFitness(hijo.getGenotipo()[hijo.getGenotipo().length-1]);
            
            ind.add(hijo);
            System.out.println("       "+i+"  hijo: "+hijo.toString());
//             if(hijo.getGenotipo()[hijo.getGenotipo().length-1]==1){
//                System.out.println("fsrtgsrt");
//            }
           
        }
        // actualizamos la nueva población
        this.poblacionActual = new Poblacion(ind);
       // pedimos el mejor a la poblacion 
       mejor  = this.poblacionActual.getMejor();
       mejor.actualizarIndividuo();
        System.out.println(g+": "+mejor.toString());
    }
    
    System.out.println(mejor.getFitness());
    System.out.println(Arrays.toString(mejor.getGenotipo()));
    }

//    private void mejor() {
//       Individuo i = new Individuo(new int[]{0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0}, aux);
//       this.poblacionActual.getIndivduos().add(i);
//    }

    @Override
    public void run() {
        evolucionar(aux);
    }
     
}
