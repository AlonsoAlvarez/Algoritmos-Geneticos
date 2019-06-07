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
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Usuario 1
 */
public class Servidor {
   public static final int PORT = 5000;
    
    public static void main(String[] args) throws IOException {
        ServerSocket socketServidor = null;
        try {
          socketServidor = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Socket socketCliente = null;
        BufferedReader entrada = null;
        PrintWriter salida = null;
        Socket socketCliente2 = null;
        BufferedReader entrada2 = null;
        PrintWriter salida2 = null;
        System.out.println("Servidor esperando conexion");
        int i=0;
        try {
            while(i<2){
                if(i==0){
                    socketCliente = socketServidor.accept();
                    System.out.println("Connexión acceptada: "+ socketCliente);
                    entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
                    salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())),true);
                    i++;
                }else{
                    socketCliente2 = socketServidor.accept();
                    System.out.println("Connexión acceptada: "+ socketCliente2);
                    entrada2 = new BufferedReader(new InputStreamReader(socketCliente2.getInputStream()));
                    salida2 = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente2.getOutputStream())),true);
                    i++;
                    break;
                }
            }  
            while (true) {
                String str2="", str=""; 
                entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
                str = entrada.readLine();
                System.out.println("str: "+str);
                if(1<str.length()){
                    System.out.println("Cliente1: " + str);
                    entrada2 = entrada;
                    salida2.println(str);
                }
                entrada2 = new BufferedReader(new InputStreamReader(socketCliente2.getInputStream()));
                str2 = entrada2.readLine();
                System.out.println("str2: "+str2);
                if(1<str2.length()){
                    System.out.println("Cliente2: " + str2);
                    entrada = entrada2;
                    salida.println(str2);
                }
            }

        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }  
        salida.close();
        entrada.close();
        socketCliente.close();
        salida2.close();
        entrada2.close();
        socketCliente2.close();
        socketServidor.close();
    } 
}
