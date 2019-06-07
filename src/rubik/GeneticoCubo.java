/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubik;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author CESAR IVAN MTZ
 */
public class GeneticoCubo implements Runnable{
   
    private Poblacion poblacionActual;
    private Configuracion manager;

    

    public GeneticoCubo(Configuracion manager) {
        this.manager = manager;
        this.poblacionActual = new Poblacion(this.manager.getTamPoblacion(),this.manager.getTamGenotipo(), this.manager.getScramble());    
    }
    
          public void evolucionar(){

                // generar las itereaciones para las generaciones
                for(int g=1;g<this.manager.getNumGeneraciones();g++){
              // garantizar construir una nueva población
              ArrayList<Cubo> ind;
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
                  Cubo madre = this.manager.aplicarSeleccion(poblacionActual,0);
                  Cubo padre = this.manager.aplicarSeleccion(poblacionActual,1);
                  // reproducimos
                  Cubo hijo = Cruza.cruzaXMascara(this.manager.getMask(), madre, padre);
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
          Cubo mejor  = this.poblacionActual.getMejor();
          int f = mejor.getFitness();
          System.out.println("g: "+g+" f:"+f+" id:"+this.hashCode());
              //System.out.println(mejor.toString());
            if(f==54){   
                System.out.println("g: "+g+" "+Arrays.toString(mejor.getGenotipo())); 
                break;
            }
          }
       }

   public Configuracion getManager() {
        return manager;
    }

    
    public Poblacion getPoblacionActual() {
        return poblacionActual;
    }

    public void setPoblacionActual(Poblacion poblacionActual) {
        this.poblacionActual = poblacionActual;
    }
    
    @Override
    public void run() {
        System.out.println("poblacion: "+poblacionActual.toString());
        evolucionar();
    }
    
//    public static void main(String args[]){
//     GeneticoCubo gc = new GeneticoCubo(1000000, 60, .15, 30,new int[]{3,3,9,9,2,7,7,4,11,11,8,8,5,5,2,11,6,6,1,7,12,10,2,2,7,3,11,11,2,2});
//     gc.evolucionar();
//    }

}
