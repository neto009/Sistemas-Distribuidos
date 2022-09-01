/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Projeto5UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author aluno
 */
public class Servidor {
    
    public static void main (String[] args ) {
        try{
            DatagramSocket s = new DatagramSocket(4545);
            System.out.println("Servidor esperando conexão...");
            
            String envio;
            DatagramPacket recebe = new DatagramPacket (new byte [512], 512);
            
            while (true){
                envio = "";
                s.receive(recebe);
                System.out.print("Mensagem recebida na porta: " + recebe.getPort());
                System.out.println();
                System.out.print("Mensagem recebida: " );
                
                for(int i = 0; i < recebe.getLength(); i++) {
                    System.out.print((char) recebe.getData()[i]);
                }
                System.out.println();
                
                DatagramPacket resp = new DatagramPacket(recebe.getData(), recebe.getLength(), recebe.getAddress(), recebe.getPort());
            
                s.send(resp);
            }
            
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
