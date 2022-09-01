/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Projeto2;

import java.net.*;
import java.io.*;

/**
 *
 * @author cibel
 */
public class Cliente {
    public static void main(String[] args){
        try{
            //Cria um socket para se conectar com o servidor
            Socket s = new Socket("0.0.0.0", 2000);
            
            //cria um canal para envio de dados
            DataOutputStream saida = new DataOutputStream(s.getOutputStream());
            //cria um canal para receber os dados
            DataInputStream entrada = new DataInputStream(s.getInputStream());
            
            //conta de 0 a 100
            for (int i = 0; i <= 100; i++){
                //envia o número inteiro pelo canal de envio
                saida.writeInt(i);
                //imprime envio no terminal
                System.out.println("Enviei: " +i);
                //recebe a resposta do servidor pelo canal de entrada
                String en = entrada.readUTF();
                //imprime no terminal o que recebeu do servidor
                System.out.println("Recebi: "+en);
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
        
        
    }
}

