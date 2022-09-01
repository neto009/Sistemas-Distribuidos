/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Projeto3;

import java.io.*;
import java.net.*;

/**
 *
 * @author cibel
 */
public class Cliente {
    public static void main (String[] args){
        try{
            Socket conexao = new Socket("localhost", 2001);
            DataInputStream entrada = new DataInputStream(conexao.getInputStream());
            DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());
            
            String linha;
            //Cria um buffer para armazenar o que está sendo digitado no teclado
            BufferedReader teclado = new BufferedReader (new InputStreamReader(System.in));
            
            while(true){
                System.out.println("> ");
                linha = teclado.readLine();
                //envia mensagem para o servidor
                saida.writeUTF(linha);
                linha = entrada.readUTF();
                //se receber uma mensagem vazia encerra o chat
                if (linha.equalsIgnoreCase("")){
                    System.out.println("Conexão encerrada!");
                    break;
                }
                //exibe mensagem recebida na tela
                System.out.println(linha);
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
        
    }
}
