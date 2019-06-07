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
public class Cruza {
    
    public static int[] generarMascaraAleatoria(int dim){
    int mask[]= new int[dim];
    Random ran = new Random();
    
    for(int x=0; x<dim;x++){
    mask[x]=ran.nextInt(2);
    }
    return mask;
    }
    public static Cubo cruzaXMascara(int mask[], Cubo madre, Cubo padre){
        Cubo hijo1;
        Cubo hijo2;
        int geno1[] = new int[madre.getGenotipo().length];
        int geno2[] = new int[padre.getGenotipo().length];
    // recorrer la maskara de cruza
    for (int x=0; x<mask.length;x++){
        // 1 madre y 0 padre
        if(mask[x]==1){
            geno1[x]=madre.getGenotipo()[x];
            geno2[x]=padre.getGenotipo()[x];
        
        }else{
            geno1[x]=padre.getGenotipo()[x];
            geno2[x]=madre.getGenotipo()[x];
        }
    }
    
    hijo1 = new Cubo(geno1, padre.getEstadoMascaras());
    hijo2 = new Cubo(geno2, madre.getEstadoMascaras());
    
    
    
    if (hijo1.getFitness()<hijo2.getFitness()){
        return hijo2;}
    else{return hijo1;}
    
    }
    
}