/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Projeto4;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author cibel
 */
public class Servidor extends Thread{
    private static Vector clientes;
    private Socket conexao;
    private String meuNome;
    
    public Servidor(Socket s){
        conexao = s;
    }
    
    public static void main(String[] args) {
        try{
            clientes = new Vector();
            
            ServerSocket s = new ServerSocket(2000);
            
            while(true){
                System.out.print("Esperando conectar...");
                Socket conexao = s.accept();
                System.out.print(" Conectou!");
                System.out.println();
                System.out.println("Endereço do cliente: " + conexao.getLocalAddress());
                System.out.println("Porta Exclusiva: " + conexao.getPort());
                Thread t = new Servidor(conexao);
                t.start();
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void run(){
        try{
            BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            PrintStream saida = new PrintStream(conexao.getOutputStream());
            
            meuNome = entrada.readLine();
            if (meuNome == null){
                return;
            }
            clientes.add(saida);
            sendToAll(saida, " entrou ", " no Chat!");
            String linha = entrada.readLine();
            while((linha != null) && (!linha.trim().equals(""))){
                sendToAll(saida, " disse: ", linha);
                linha = entrada.readLine();
            }
            
            sendToAll(saida, " saiu ", " do Chat!");
            clientes.remove(saida);
            conexao.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void sendToAll(PrintStream saida, String acao, String linha){
        Enumeration e = clientes.elements();
        while(e.hasMoreElements()){
            PrintStream chat = (PrintStream) e.nextElement();
            
            if(chat != saida){
                //****O comando estava errado (chat.print)
                chat.println(meuNome + acao + linha);
            }
            if(acao.equals(" saiu ")){
                if (chat == saida)
                    chat.println("");
            }
        }
    }
}
