package app.container;
import app.docker.DockerHandler;
import com.spotify.docker.client.DockerException;

import java.net.*;
import java.io.*;

public class EchoServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private DockerHandler myDockerHandler;
    private PrintWriter out;
    private BufferedReader in;

    public static final String imageName = "library/ubuntu";
    public static final String containerMountDirectory = "";
    public void start(int port) throws IOException {
        myDockerHandler = new DockerHandler(imageName, containerMountDirectory);

        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            String[] parsedInput = inputLine.split(" ");
            System.out.println("Recieved Message: " + inputLine);
            if (".".equals(inputLine)) {
                out.println("good bye");
                break;
            }
            if("startCloud".equals(parsedInput[0])) {
                String id = myDockerHandler.createContainer();
                out.println(id);
            }
            if("endCloud".equals(parsedInput[0])) {
                try{
                    myDockerHandler.stopContainer(parsedInput[1]);
                } catch (DockerException e) {
                    out.println("Exception in Stopping Container");
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    out.println("Exception in Stopping Container");
                    throw new RuntimeException(e);
                }
                out.println("Successfully closed container " + parsedInput[1]);
            }
        }
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }

    public static void main(String[] args) {
        EchoServer server=new EchoServer();
        try {
            System.out.println("Starting Server");
            server.start(6666);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


}