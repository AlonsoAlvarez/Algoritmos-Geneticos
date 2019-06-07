/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nreinas;


import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Roberto Cruz Leija
 */
public class GeneticoNReinas implements Runnable{
    // parametros
    private Poblacion poblacionActual;
    private Configuracion manager;

    public void setManager(Configuracion manager) {
        this.manager = manager;
        this.poblacionActual = new Poblacion(this.manager.getTamPoblacion(),this.manager.getTamGenotipo());
    }

    public void setMejor(Individuo ind){
        this.poblacionActual.addIndividuo(ind);
    }
    
    public Individuo getMejor(){
        int idMejor = 0;
        ArrayList<Individuo> individuos = poblacionActual.getIndivduos();
        for(int x=1;x<individuos.size();x++){
            if(individuos.get(x).getFitness()<individuos.get(idMejor).getFitness()){
            idMejor = x;
            }
        }
        return new Individuo(individuos.get(idMejor).getGenotipo());
    }
    
    
    
    public GeneticoNReinas() {
    }


    public GeneticoNReinas(Configuracion manager) {
        this.manager = manager;
        this.poblacionActual = new Poblacion(this.manager.getTamPoblacion(),this.manager.getTamGenotipo());
        
    }
    
    public void evolucionar(){
   
    // generar las itereaciones para las generaciones
    for(int g=1;g<this.manager.getNumGeneraciones();g++){
        // garantizar construir una nueva población
        ArrayList<Individuo> ind;
        // calcular un N
        int n = (int)(this.manager.getTamPoblacion()*this.manager.getpMuestra());
        if (n>0){
        ind = new ArrayList<>();
        ind.add(this.poblacionActual.getMejor());
        
        }else {
        ind = new ArrayList<>();
        }
        for(int i=n; i<this.manager.getTamPoblacion();i++){
            // seleccionamos
            Individuo madre = this.manager.aplicarSeleccion(poblacionActual,0);
            Individuo padre = this.manager.aplicarSeleccion(poblacionActual,1);
            // reproducimos
            Individuo hijo = Cruza.cruzaXMascara(this.manager.getMask(), madre, padre);
            // mutamos 
            // evaluar la probabilidad
            Muta.mutaGen(this.manager.getProbMuta(), hijo);
            // agregamos
            ind.add(hijo);
        }
        // actualizamos la nueva población
        this.poblacionActual = new Poblacion(ind);
      //System.out.println(g);
      // pedimos el mejor a la poblacion 
    Individuo mejor  = this.poblacionActual.getMejor();
    int f = mejor.getFitness();
    System.out.println("g: "+g+" f:"+f+" id:"+this.hashCode());
    if(f==0){
        
        System.out.println("g: "+g+" "+Arrays.toString(mejor.getGenotipo()));
    
        break;
    }
    }
    // pedimos el mejor a la poblacion 
//    Individuo mejor  = this.poblacionActual.getMejor();
//    System.out.println(mejor.getFitness());
    //System.out.println(Arrays.toString(mejor.getGenotipo()));
    }


    /**
     * @return the manager
     */
    public Configuracion getManager() {
        return manager;
    }

    @Override
    public void run() {
        evolucionar();
    }

    public int getNumG() {
        return manager.getNumGeneraciones();
    }

    public int getTamP() {
        return manager.getTamPoblacion();
    }

    public double getpMuta() {
        return manager.getProbMuta();
    }

}
