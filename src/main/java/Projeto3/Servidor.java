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
public class Servidor {
    public static void main (String[] args) throws Exception{
        ServerSocket s = new ServerSocket(2001);
        while(true){
            System.out.println("Esperando conectar...");
            Socket conexao = s.accept();
            System.out.println(" Conectou! ");
            DataInputStream entrada = new DataInputStream(conexao.getInputStream());
            DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());
            
            String linha = entrada.readUTF();
            System.out.println("Recebi: " + linha);
            while (linha != null && !(linha.trim().equals(""))){
                saida.writeUTF(linha);
                linha = entrada.readUTF();
            }
            saida.writeUTF(linha);
            conexao.close();
        }
    }
}
