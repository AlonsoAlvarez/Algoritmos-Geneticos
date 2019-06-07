/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libre;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Roberto Cruz Leija
 */
public class Individuo {
    
    private int genotipo[];
    private double fitness;
    private int clasifica;
    ArrayList<Patron> aux;
    
    public int getClasifica() {
        return clasifica;
    }

    public void setClasifica(int clasifica) {
        this.clasifica = clasifica;
    }
    
    public Individuo(int n, ArrayList<Patron> aux) {
        this.aux=aux;
        generarGenotipoAleatorio2(n);
         calcularFitness(genotipo[genotipo.length-1]);
    }
//    public Individuo() {
//        generarGenotipoAleatorio();
//        calcularFitness(genotipo[genotipo.length-1]);
//    }
    public Individuo(int genotipo[], ArrayList<Patron> aux){
        this.aux=aux;
        this.genotipo = genotipo.clone();
        calcularFitness(genotipo[genotipo.length-1]);
    }
//    private void generarGenotipoAleatorio() {
//        this.genotipo = new int[100];
//        Random ran = new Random();
//        for(int x=0; x< this.genotipo.length;x++)
//             this.genotipo[x]= ran.nextInt(2);
//       
//    }
    
    
    private void generarGenotipoAleatorio2(int n) {
        this.genotipo = new int[n];
        Random ran = new Random();
        for(int x=0; x< this.genotipo.length;x++){
                this.genotipo[x]= ran.nextInt(2);
                if(genotipo[x]==2){
                    System.out.println("YA Valio 2");
                }
        }
        this.genotipo[n-1] = ran.nextInt(3)+3;
            if(genotipo[n-1]==6){
                    System.out.println("YA Valio 3");
            }
                
    }
    
    public void actualizarIndividuo(){
        calcularFitness(genotipo[genotipo.length-1]);
        
    }

    

    /**
     * @return the genotipo
     */
    public int[] getGenotipo() {
        return genotipo;
    }

  
    /**
     * @return the fitness
     */
    public double getFitness() {
        return fitness;
    }

    public void calcularFitness(int n) {
        // recorrer las clausulas
        
        if(n==3){
            Knn knn = new Knn(6);
            knn.entrena(aux);
            knn.clasificaConjunto(aux);
            this.fitness = knn.getEficacia();
//            System.out.println("Eficacia knn: "+knn.getEficacia());
        }else{
            if(n==4){
                MinimaDistancia md = new MinimaDistancia();
                md.entrena(aux);
                md.clasificaConjunto(aux);
                this.fitness = md.getEficacia();
//                System.out.println("Eficacia minima distancia: "+md.getEficacia());
            }else{
                NaiveBayes nb = new NaiveBayes();
                nb.entrena(aux);
                nb.clasificaConjunto(aux);
                this.fitness = nb.getEficacia();
//                System.out.println("Eficacia minima distancia: "+nb.getEficacia());
            }
        }
    }

    private boolean verificarClausula(Patron c) {
        boolean pos=true;
        for (int i = 0; i < c.getCaracteristicas().length; i++) {
            if(verificarNeg((int) c.getCaracteristicas()[i])!=1){
                pos= false;
                break;
            }
        }
        return pos;
//       return (verificarNeg(c.getA())==1||
//             verificarNeg(c.getB())==1||
//                 verificarNeg(c.getC())==1);
            
    }

    private int verificarNeg(int a) {
       boolean negacion = false;
       int valor = -1;
       if(a<0){
           a*=-1;
           negacion = true;
           
       }
       if (negacion){
            valor = this.genotipo[a-1];
        if (valor==0){valor = 1;}else{
            valor = 0;
        }
       } else {
            valor = this.genotipo[a-1];
       }
       return valor;
    }

    @Override
    public String toString() {
        String aux="";
        if(genotipo[genotipo.length-1]==3){
            aux="KNN";
        }
        if(genotipo[genotipo.length-1]==4){
            aux="Minima Distancia";
        }
        if(genotipo[genotipo.length-1]==5){
            aux="Bayes";
        }
        return "Individuo{" + "fitness= " + fitness +", clasificador= "+aux +", genotipo= " + Arrays.toString(genotipo) + '}';
    }
    
    
    
}
