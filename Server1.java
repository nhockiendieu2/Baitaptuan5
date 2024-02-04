package baitaptuan5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1 {
    public static void main(String[] args) {
        final int PORT = 12345; // pORT
        try(ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server dang cho ket noi tu Client........");
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client da ket noi: "+clientSocket);
                
                // Bat dau 1 luong moi de xu ly client
                Thread clientHandlerThread = new Thread(new ClientHandler(clientSocket));
                clientHandlerThread.start();
                
            }
            
            
            
        } catch (Exception e) {
        }
    }
}

// Nhan du lieu tu client
class ClientHandler implements Runnable{
    private Socket clientSocket;
    public ClientHandler(Socket clientSocket){
        this.clientSocket = clientSocket;
    }
    
    @Override
    public void run(){
        try (// Tao luong dau vao
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // Tao luong dau ra
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true))
        {
            // Nhan du lieu tu Client
            String clientMessage;
            while((clientMessage = in.readLine())!= null){
                System.out.println("Client gui: "+clientMessage);
                //Xu ly du lieu neu can
                //Gui phan hoi laij cho client
                out.println("Server nhan duoc: "+clientMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                // Dong socket sau khi xxu ly xong
                clientSocket.close();
            } catch (IOException e) {
                 e.printStackTrace();
            }
        
        }
    
    
    }

}