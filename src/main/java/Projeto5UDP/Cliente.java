/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Projeto5UDP;

import java.net.*;
import java.io.*;

/**
 *
 * @author aluno
 */
public class Cliente {
    
    public static void main (String [] args){
    
        try{
        DatagramSocket s = new DatagramSocket();
        InetAddress dest = InetAddress.getByName("localhost");
        String envio;
        
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("> ");
        envio = teclado.readLine();
        
        while(!envio.equalsIgnoreCase("")){
            byte[] buffer = envio.getBytes();
            DatagramPacket msg = new DatagramPacket (buffer, buffer.length, dest, 4545);
            s.send(msg);
            DatagramPacket resposta = new DatagramPacket(new byte [512], 512);
            s.receive(resposta);
            
            for(int i = 0; i < resposta.getLength(); i++){
                System.out.print((char) resposta.getData()[i]);
            }
            System.out.println("> ");
            envio = teclado.readLine();
            s.close();
        }
        }catch(Exception e){
            e.printStackTrace();
        }
    
    
    }
            
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
