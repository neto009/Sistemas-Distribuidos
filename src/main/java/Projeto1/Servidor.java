/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Projeto1;

import java.net.*;
import java.io.*;

/**
 *
 * @author cibel
 */
public class Servidor {
    public static void main(String[] args){
        try{
            //criar a porta do servidor
            ServerSocket s = new ServerSocket(2000);
            System.out.println("Esperando conexão.............");
            
            //servidor aceita a conexão
            Socket conexao = s.accept();
            //imprime no terminal que a conexão foi aceita
            System.out.println("Conexao aceita, esperando dados...");
            
            //Cria canal pra receber os dados
            DataInputStream entrada = new DataInputStream(conexao.getInputStream());
            //Cria canal para enviar dados
            DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());
            
            
            
            //conta 0 até 100
            for(int i = 0; i <= 100; i++){
                //Le o que recebeu pelo canal de entrada
                int linha = entrada.readInt();
                //Imprime no terminal para mostrar que recebeu o dado do cliente
                System.out.println("entre");
                //manda a mensagem para o cliente
                saida.writeUTF("Recebi seu dado: " + linha);
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
