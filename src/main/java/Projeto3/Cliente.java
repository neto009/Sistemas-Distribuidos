package Projeto3;

import java.io.*;
import java.net.*;

public class Cliente {
    
    public static void main(String[] args) throws IOException {
        Socket conexao = new Socket("localhost", 2001);
        
        DataInputStream entrada = new DataInputStream(conexao.getInputStream());
        DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());
        
        String linha;
        //Cria um buffer para armazenar o que está sendo digitado.
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        
        while(true){
            System.out.println("> ");
            linha = teclado.readLine();
            //envia mensagem para o servidor
            saida.writeUTF(linha);
            linha = entrada.readUTF();
            
            if(linha.equalsIgnoreCase("")){
                System.out.println("Conexão encerrada!");
                break;
            }
            //Exibe mensagem recebida na tela
            System.out.println(linha);
        }
    }
    
}
