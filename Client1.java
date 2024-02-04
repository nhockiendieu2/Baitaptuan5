package baitaptuan5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client1 {
    public static void main(String[] args) {
        final String SERVER_ADDRESS = "192.168.41.101";
        final int PORT = 12345;
        try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {
            
            
            System.out.println("Client da ket noi voi Server");
            String userInputLine;
            
            while ((userInputLine = userInput.readLine()) !=null) {

                out.println(userInputLine); // Gui du lieu len server
                String response = in.readLine(); // nhan phan hoi tu server
                System.out.println("Server phan hoi: "+ response); // In ra noi dung phan hoi
            
                
            }
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
