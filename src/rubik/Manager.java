/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubik;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author CESAR IVAN MTZ
 */
public class Manager {
    private String id;
    private int nG;
    private boolean ejecucion;
    private ArrayList<GeneticoCubo> geneticos;
    private ArrayList<Configuracion> configuraciones;
    private int cubos;
    
    public ArrayList<GeneticoCubo> getGeneticos() {
        return geneticos;
    }
    
    public Manager (String id, int cubos){
        this.cubos = cubos;
        this.nG = 0;
        this.ejecucion = false;
        this.geneticos = new ArrayList<>();
        this.configuraciones = new ArrayList<>();
        this.id = id;
        iniciarCliente();
    }
    
    public void generarGeneticos(ArrayList<Configuracion> configuraciones){
    // crear los geneticos en base a las configuraciones
    this.nG = configuraciones.size();
    for (int x=0; x<nG;x++){
        this.configuraciones.add(configuraciones.get(x));
        GeneticoCubo gen = new GeneticoCubo(configuraciones.get(x));
        this.geneticos.add(gen);
    }
    }
    
    public void generarGeneticos(int nG){
    // crear los geneticos en base a las configuraciones
    for (int x=0; x<nG;x++){
        this.configuraciones.add(new Configuracion(this.cubos));
        GeneticoCubo gen = new GeneticoCubo(configuraciones.get(x));
        this.geneticos.add(gen);
    }
    }
    
    public void ejecutarGeneticos(){
    // ejecutar los geneticos en un hilo diferente
    for (GeneticoCubo aux: this.geneticos){
    Thread hilo = new Thread(aux);
    hilo.start();
    }
    this.ejecucion = true;
    }
    
    //Vamos a cmabia la familia de un  gentico a otro genetico
    public void setPoblacion(int conf1, int conf2){
        Poblacion pob2=this.geneticos.get(conf2).getPoblacionActual();
        ///Setear el tamaño del conf1 al conf2
        this.configuraciones.get(conf1).setTamPoblacion(this.configuraciones.get(conf2).getTamPoblacion());
        this.geneticos.get(conf1).setPoblacionActual(pob2);
          for(int i=0;i<100;i++)
            System.out.println("Cambio");
    }
    
    
    ///Se cambia el mejor entre geneticos
    public void setMejor(int conf1, int conf2){     
        Cubo ind=this.geneticos.get(conf2).getPoblacionActual().getMejor();
        this.geneticos.get(conf1).getPoblacionActual().setMejor(ind);
  
          for(int i=0;i<100;i++)
            System.out.println("Seleccionar Mejor");
    }
    
    
    
    
    public void setMejores(int conf1, int conf2, int n){
      
        ArrayList<Cubo> individuos=this.geneticos.get(conf2).getPoblacionActual().getNMejores(n);
        this.configuraciones.get(conf1).setTamPoblacion(n);
        this.geneticos.get(conf1).getPoblacionActual().setMejores(individuos);
        
          for(int i=0;i<100;i++)
            System.out.println("Cambiar varios");
    }
    
    
    public String getInfoGenetico(int i){
        String aux="";  
        aux=this.configuraciones.get(i).getId()+" Fitness: "+
        this.geneticos.get(i).getPoblacionActual().getMejor().getFitness()+" "+Arrays.toString(this.geneticos.get(i).getPoblacionActual().getMejor().getGenotipo()); 
        return aux;
    }
    
    
    
    public Cubo mejorDeTodos(){
        int pos = 0;
        int menor=0;
        for (int i = 0; i < getGeneticos().size(); i++) {
            if(menor<getGeneticos().get(i).getPoblacionActual().getMejor().getFitness()){
                menor=getGeneticos().get(i).getPoblacionActual().getMejor().getFitness();
                pos=i;
            }
        }
        return getGeneticos().get(pos).getPoblacionActual().getMejor();
    }
       
    public void enviar(){
        System.out.println("Envio");
        int i=0;
        String linea = mejorDeTodos().toString();
        //String linea = "tronco mental";
        salidaCl.println(linea);
    }
        
    boolean esNumero(char c){
        return c=='0' || c=='1' || c=='2' || c=='3' || c=='4' || c=='5' || c=='6' || c=='7' || c=='8' || c=='9';
    }
    
    boolean esColor(char c){
        return c=='b' || c=='g' || c=='o' || c=='r' || c=='w' || c=='y';
    }
    
    public char[][] stringMask(String linea){
        char[][] aux = new char[6][9];
        int k=0;
        for(int i=0; i<6; i++){
            int j=0;
            while(j<9){
                if(esColor(linea.charAt(k))){
                    aux[i][j]=linea.charAt(k);
                    j++;
                }
                k++;
            }
        }
        return aux;
    }
    
    public int[] stringGen(String linea){
        int l=0;
        for(int j=0; j<linea.length(); j++){
            if(linea.charAt(j)=='='){
                l=j+1;
                break;
            }
        }
        int[] aux = new int[linea.length()+1];
        int k=0;
        for(int i=l; i<linea.length()-1; i++){
            if(linea.charAt(i)=='[' || linea.charAt(i)==' ' || linea.charAt(i)==','){
                
            }else{
                String tmp="";
                while(esNumero(linea.charAt(i))){
                    tmp+=linea.charAt(i);
                    i++;
                }
                aux[k]=Integer.parseInt(tmp);
                k++;
            }
        }
        int n=0;
        for(int m=0; m<aux.length; m++){
            if(aux[m]==0){
                n=m;
                break;
            }
        }
        int[] aux2 = new int[n];
        for(int o=0; o<n; o++){
            aux2[o]=aux[o];
        }
        return aux2;
    } 
    
    public void addMejorATodos(Cubo c){
//        for (int i = 0; i < getGeneticos().size(); i++) {
//            getGeneticos().get(i).getPoblacionActual().setIndividuo(c);
//        }
        this.geneticos.get(0).getPoblacionActual().setMejor(c);
    }
    
    public void refresh() throws IOException{
        System.out.println("Refresco");
        int r=0;
        String linea = entrada.readLine();
        int[] genotipoExterno = stringGen(linea);
        char[][] mask = stringMask(linea);
        // Cubo(int [] genotipo, char[][] mascaras)
        Cubo c = new Cubo(genotipoExterno, mask);
        addMejorATodos(c);
    }
    
    private String ServDice;

    public String getServDice() {
        return ServDice;
    }
    
    public void espera() throws IOException{
        System.out.println("Refresco");
        String linea = "";
        while("".equals(linea)){
            linea=entrada.readLine();
        }
        this.ServDice=linea;
        int[] genotipoExterno = stringGen(linea);
        char[][] mask = stringMask(linea);
        // Cubo(int [] genotipo, char[][] mascaras)
        Cubo c = new Cubo(genotipoExterno, mask);
        addMejorATodos(c);
    }

    Socket socketCliente = null;
    BufferedReader entrada = null;
    PrintWriter salidaCl = null;
    
    public void iniciarCliente(){
        System.out.println("cliente iniciado");
        try {
            String host = JOptionPane.showInputDialog("Ingrese una ip");
            socketCliente = new Socket(host, 5000);
            entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            salidaCl = new PrintWriter(new BufferedWriter(new 
            OutputStreamWriter(socketCliente.getOutputStream())),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
      
    
}
