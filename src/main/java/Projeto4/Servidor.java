/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Projeto4;

import java.io.*;
import java.net.*;
import java.util.Enumeration;
import java.util.Vector;

public class Servidor extends Thread {
    private static Vector clients;
    private Socket conexao;
    private String meuNome;
    
    public Servidor(Socket s) {
        conexao = s;
    }
    
    public static void main(String[] args) {
        try{
            clients = new Vector();
            
            ServerSocket s = new ServerSocket(2000);
            
            while(true) {
                System.out.println("Esperando conectar ....");
                
                Socket conexao = s.accept();
                System.out.println(" Conectou! ");
                Thread t = new Servidor(conexao);
                t.start();
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void run(){
        try{
            BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
            PrintStream saida = new PrintStream(conexao.getOutputStream());
            
            meuNome = entrada.readLine();
            
            if(meuNome == null){
                return;
            }
            clients.add(saida);
            String linha = entrada.readLine();
            
            while((linha != null) && (!linha.trim().equals(""))){
                sendToAll(saida, " disse: ", linha);
                linha = entrada.readLine();
            }
            
            sendToAll(saida, " saiu ", "do Chat!");
            clients.remove(saida);
            conexao.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void sendToAll(PrintStream saida, String acao, String linha) {
        Enumeration e = clients.elements();
        while(e.hasMoreElements()){
            PrintStream chat = (PrintStream) e.nextElement();
            
            if(chat != saida) {
                chat.print(meuNome + acao + linha);
            }
            
            if(acao.equals(" saiu ")) {
                if(chat== saida){
                    chat.println("");
                }
            }
        }        
    }
    
}
