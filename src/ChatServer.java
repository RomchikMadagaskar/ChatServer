import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ChatServer {
    ArrayList<Client> clients=new ArrayList<>();
    ServerSocket serverSocket;
    ChatServer() throws IOException{
        serverSocket = new ServerSocket(1234);
    }
    void sendAll(String message){
        for(Client client:clients){
            client.receive(message);
        }
    }
    public void run(){
        while (true){
            try {
                System.out.println("Waiting...");
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");
                clients.add(new Client(socket,this));
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws IOException {
        new ChatServer().run();


    }
}