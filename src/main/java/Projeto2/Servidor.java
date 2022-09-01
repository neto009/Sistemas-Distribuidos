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
public class Servidor {
    public static void main(String[] args){
        try{
            InetAddress endereco_remoto;
            int porta_remota;
            //criar a porta do servidor
            ServerSocket s = new ServerSocket(2000);
            InetAddress endereco_servidor;
            endereco_servidor = s.getInetAddress();
            System.out.println("IP Servidor: " + endereco_servidor.getHostAddress());
            System.out.println("Esperando conexão.............");
            
            //servidor aceita a conexão e cria um socket exclusivo
            Socket conexao = s.accept();
            //imprime no terminal que a conexão foi aceita
            System.out.println("Conexao aceita, esperando dados...");
            //Acessa endereço do cliente
            endereco_remoto = conexao.getInetAddress();
            //Acessar a porta 
            porta_remota = conexao.getPort();
            //Mostra as informações
            System.out.println("Nome da maquina remota: " + endereco_remoto.getHostName());
            System.out.println("IP da maquina remota: " + endereco_remoto.getHostAddress());
            System.out.println("Porta maquina remota: " + porta_remota);
            
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
