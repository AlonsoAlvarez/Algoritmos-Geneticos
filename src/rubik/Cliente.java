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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario 1
 */
public class Cliente {
    Socket socketCliente = null;
    BufferedReader entrada = null;
    PrintWriter salidaCl = null;
    int jugador;
    
    public void iniciarCliente(){
        System.out.println("cliente iniciado");
        try {
            String host = JOptionPane.showInputDialog("Ingrese una ip");
            socketCliente = new Socket(host, 5000);
            entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            salidaCl = new PrintWriter(new BufferedWriter(new 
            OutputStreamWriter(socketCliente.getOutputStream())),true);
            jugador=Integer.parseInt(entrada.readLine());
            System.out.println("Jugador: "+jugador);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void enviarMejor(Cubo cu){
        System.out.println("Envio");
        int i=0;
        String linea = cu.toString();
        salidaCl.println(linea);
    }
    
    
    
    public void refreshMejor(Poblacion p){
        System.out.println("Refresco");
        int r=0;
        String linea = null;
        try {
            linea = entrada.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(linea!=null){
            int[] genotipoExterno = stringGen(linea);
            char[][] mask = stringMask(linea);
            // Cubo(int [] genotipo, char[][] mascaras)
            Cubo c = new Cubo(genotipoExterno, mask);
            p.setIndividuo(c);
        }else{
            System.out.println("no entro nada");
        }
    }
    
    public void esperaMejor(Poblacion p){
        System.out.println("Refresco");
        String linea = "";
        while("".equals(linea)){
            try {
                linea=entrada.readLine();
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        int[] genotipoExterno = stringGen(linea);
        char[][] mask = stringMask(linea);
        // Cubo(int [] genotipo, char[][] mascaras)
        Cubo c = new Cubo(genotipoExterno, mask);
        p.setIndividuo(c);
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
        
    boolean esNumero(char c){
        return c=='0' || c=='1' || c=='2' || c=='3' || c=='4' || c=='5' || c=='6' || c=='7' || c=='8' || c=='9';
    }
    
    boolean esColor(char c){
        return c=='b' || c=='g' || c=='o' || c=='r' || c=='w' || c=='y';
    }
    
}
