/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.escuelaing.arep.labHttpServer;

/**
 *
 * @author Home
 */
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpServer {

    private static final HttpServer _instance = new HttpServer();

    private HttpServer() {
    }

    ;
    
    public static HttpServer getInstance() {
        return _instance;
    }

    public void start(String[] args) throws IOException, URISyntaxException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);

        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        boolean running = true;
        while (running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            serverConnection(clientSocket);
            clientSocket.close();
        }
        serverSocket.close();
    }

    public void serverConnection(Socket clientSocket) throws IOException, URISyntaxException {
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        clientSocket.getInputStream()));
        String inputLine, outputLine;
        ArrayList<String> request = new ArrayList<String>();

        while ((inputLine = in.readLine()) != null) {
            System.out.println("Received: " + inputLine);
            request.add(inputLine);
            if (!in.ready()) {
                break;
            }
        }
        String uriStr = request.get(0).split(" ")[1];
        URI resourceURI = new URI(uriStr);
        outputLine = getResource(resourceURI);
        out.println(outputLine);

        out.close();
        in.close();
    }

    public String getResource(URI resourceURI) {
        String path = resourceURI.getPath();
        String query = resourceURI.getQuery();
        System.out.println("hola   " + path);
        System.out.println(query);
        return defaultHtml();

    }

    private String defaultHtml() {
        String outputLine = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset=\"UTF-8\">"
                + "<title>Title of the document</title>\n"
                + "</head>"
                + "<body>"
                + "My Web Siteeeee"
                + "</body>"
                + "</html>";
        return outputLine;
    }

    private String getCity() {
        String city = "london";

        HttpClimaService climaService = CurrentServiceInstance.getInstance().getService();

        climaService.setClima(city);

        String response = "None";
        try {
            response = climaService.TimeSeries();
        } catch (IOException e) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, e);
        }
        System.out.println(response);

    }


private String formatCity(String city) {
        String outputLine = "HTTP/1.1 200 OK\r\n"
                +"Content-Type: text/html\r\n"
                +"\r\n"
                +"<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset=\"UTF-8\">"
                + "<title>Title of the document</title>\n"
                + "</head>"
                + "<body>"
                + "My Web Siteeeee"
                + "</body>"
                + "</html>";
        return outputLine;
    }

    public static void main(String args[]) throws IOException, URISyntaxException {
        HttpServer.getInstance().start(args);
    }
}
