package app.client;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class EchoClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        String resp = in.readLine();
        return resp;
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public static void main(String []args) {
        int num;
        EchoClient client = new EchoClient();
        try {
            //client.startConnection("127.0.0.1", 6666);
            //client.startConnection("152.3.54.23", 6666);
            client.startConnection("67.159.89.163", 6666);
        } catch(Exception e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("Enter operation (startCloud or endCloud), or exit to quit: ");
            String command = scanner.nextLine();
            String[] parsedCommand = command.split(" ");
            try {
                if (parsedCommand[0].equals("startCloud") || parsedCommand[0].equals("endCloud")) {
                    client.sendMessage(command);
                } else if(parsedCommand[0].equals("exit")) {
                    client.stopConnection();
                    System.out.println("Successfully stopped connection");
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}