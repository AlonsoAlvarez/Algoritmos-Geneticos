/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Roberto Cruz Leija
 */
public class NaiveBayes implements ClasificadorSupervisado{

    private ArrayList<Patron> instancias;
    private double[][] matrizMedias;
    private double[][] matrizVarianzas;
    private double[][] matrizDistribuciones;
    private double[] probPriori;
    private double[] probPosteriori;
    private Map<String,ClaseBayes> clases;
    double eficacia;

    public double getEficacia() {
        return eficacia;
    }
    
    public NaiveBayes() {
        this.clases = new HashMap<String,ClaseBayes>();
    }
    
    
    
    @Override
    public void entrena(ArrayList<Patron> instancias) {
        this.instancias = instancias;
        // calcular la probabildiad a priori por cada clase
        this.clases.put(this.instancias.get(0).getClaseOriginal(),new ClaseBayes(this.instancias.get(0).getClaseOriginal()));
        
        // recorrer las instancias 
        for(Patron patron: instancias){
            // verifiar si existe una ClaseBayes con el nombre de la clase del patron
            if(!this.clases.containsKey(patron.getClaseOriginal())){
               // si lo contiene lo acumula 
               this.clases.put(patron.getClaseOriginal(),new ClaseBayes(patron.getClaseOriginal()));
                          }
             this.clases.get(patron.getClaseOriginal()).acumularAMedia(patron.getCaracteristicas());
        }
       
        for(Entry<String,ClaseBayes> aux: this.clases.entrySet()){
            aux.getValue().calcularMedia();
            // aprovechando el ciclo for calculamos el apriori
            aux.getValue().calcularApriori(instancias.size());
        }
        // calculamos las varianzas
        for(Patron patron: instancias){
            // verifiar si existe una ClaseBayes con el nombre de la clase del patron
            String clase = patron.getClaseOriginal();
            this.clases.get(clase).calcularVarianza(patron.getCaracteristicas());
            
        }
               
    }

    @Override
    public void clasifica(Patron patron) {
       // recibir el patron a clasificar 
       double[][] distribucion = 
          new double[this.clases.size()][patron.getCaracteristicas().length];
       // calcular una matriz de distribuciones normales parametrizadas
       int r = 0;
       double evidencia=0;
       for(Entry<String,ClaseBayes> aux: this.clases.entrySet()){
           Patron media = aux.getValue().getMedia();
           Patron varianza = aux.getValue().getVarianza();
           double producto=aux.getValue().getApriori();
           for(int c=0;c<patron.getCaracteristicas().length;c++){
           distribucion[r][c]= calcularDN(patron.getCaracteristicas()[c],media.getCaracteristicas()[c],varianza.getCaracteristicas()[c]);
           producto*=distribucion[r][c];
           }
           evidencia+=producto;
           r++;
       }
       double psMayor = -1;
       r = 0;
       String clase ="";
       for(Entry<String,ClaseBayes> aux: this.clases.entrySet()){
           double producto=aux.getValue().getApriori();
           for(int c=0;c<patron.getCaracteristicas().length;c++){
           producto*=distribucion[r][c];
           }
            r++;
           producto/=evidencia;
           // verficamos si es el mayor
           if(producto>psMayor){
           psMayor = producto;    
           clase = aux.getKey();
           }
       }
       patron.setClaseResultante(clase);
//       System.out.println();
             
    }

    public void clasificaConjunto(ArrayList<Patron> instancias){
    // recorremos la coleccion a clasificar
        int total = instancias.size();
        // contador de clasificacion correctas
        int aux = 0;
        for (Patron patron: instancias){
            clasifica(patron);
            if(patron.getClaseResultante().equals(patron.getClaseOriginal()))aux++;
        }
       this.eficacia = aux*100/total;
    }
    
    private double calcularDN(double c, double m, double v) {
        
        double div = 1/(Math.sqrt(2*Math.PI*v));
        double exp = Math.exp(-1*((Math.pow(c-m, 2))/(2*v)));
        double res = (div)*(exp); 
        return res;
     
    }
    
}
