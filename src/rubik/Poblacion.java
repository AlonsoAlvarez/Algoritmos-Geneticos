/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubik;


import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author CESAR IVAN MTZ
 */
public class Poblacion {
      private ArrayList<Cubo> indivduos;

    public Poblacion(int numInd, int tam, int[] escramble) {
        this.indivduos = new ArrayList<>();
        for(int x=0; x<numInd;x++)
            this.indivduos.add(new Cubo(tam, escramble));     
    }
    
    public Poblacion(ArrayList<Cubo> aux){
        this.indivduos = (ArrayList<Cubo>)aux.clone();
    }
    
    public ArrayList<Cubo> getNMejores(int n){
        // validar que n <= tamaño de la población
      if(n<this.indivduos.size()){
      // ordenar a la población
      ordenarPoblacionActual();
      // creamos un coleccion nueva de individuos
      ArrayList<Cubo> muestra = new ArrayList<>();
        
       for(int x=this.indivduos.size()-1;x>=this.indivduos.size()-n;x--){
        //Agregar las caras estado actual
        Cubo e = new Cubo(this.indivduos.get(x).getGenotipo(), this.indivduos.get(x).getEstadoMascaras());   
        muestra.add(e);
       }   
      return muestra;
      }
      
        return (ArrayList<Cubo>) this.indivduos.clone();
    }
    
    public Cubo getMejor(){
        int idMejor = 0;
        for(int x=1;x<this.indivduos.size();x++){
            if(this.indivduos.get(x).getFitness()>this.indivduos.get(idMejor).getFitness()){
            idMejor = x;
            }
        }
          //Agregar las caras estado actual
     return new Cubo(this.indivduos.get(idMejor).getGenotipo(), this.indivduos.get(idMejor).getEstadoMascaras());   
    }
    
      
    public ArrayList<Cubo> getMuestraAleatoria(int n){
      // validar que n <= tamaño de la población
      if(n<this.indivduos.size()){
      // creamos un coleccion nueva de individuos
      ArrayList<Cubo> muestra = new ArrayList<>();
          Random ran = new Random();
       for(int x=0;x<n;x++){
       int pos = ran.nextInt(this.indivduos.size());
         //Agregar las caras estado actual
       Cubo e = new Cubo(this.indivduos.get(pos).getGenotipo(), this.indivduos.get(pos).getEstadoMascaras());
       muestra.add(e);
       }   
      return muestra;
      }
      
        return (ArrayList<Cubo>) this.indivduos.clone();
    }
 

    /**
     * @return the indivduos
     */
    public ArrayList<Cubo> getIndivduos() {
        return indivduos;
    }

    private void ordenarPoblacionActual() {
     for(int z = 1; z < this.indivduos.size(); ++z) {
      for(int v = 0; v < (this.indivduos.size() - z); ++v) {
         
         if(this.indivduos.get(v).getFitness()
                 > this.indivduos.get(v+1).getFitness()){
                //Agregar las caras estado actual
            Cubo aux = new Cubo(this.indivduos.get(v).getGenotipo(), this.indivduos.get(v).getEstadoMascaras());
            this.indivduos.set(v,new Cubo(this.indivduos.get(v+1).getGenotipo(), this.indivduos.get(v+1).getEstadoMascaras()));
            this.indivduos.set(v+1,aux);
           
         }
      }
    }
            
    }
    
    public void setIndividuo(Cubo nuevo){
        this.indivduos.remove(indivduos.size());
        this.indivduos.add(nuevo);
    }
    
      public void setMejor(Cubo cub){
        this.indivduos.set(0, cub);
    }
    
    
    public void setMejores(ArrayList<Cubo> cubos){
        this.indivduos = cubos;
    }

    @Override
    public String toString() {
        return "" + indivduos + "";
    }
}
