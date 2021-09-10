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
            serverSocket = new ServerSocket(getPort());

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
    static int getPort(){
        if(System.getenv("PORT")!=null){
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

    public void serverConnection(Socket clientSocket) throws IOException, URISyntaxException {
        OutputStream out = clientSocket.getOutputStream();
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
        if(!request.isEmpty()){
            String uriStr = request.get(0).split(" ")[1];
            URI resourceURI = new URI(uriStr);
            buildResource(resourceURI,out);
            
        }
        

        out.close();
        in.close();
    }

    public void buildResource(URI resourceURI,OutputStream out) throws IOException {
        
        PrintWriter outPrintWriter = new PrintWriter(out,true);
        
        String path = resourceURI.getPath();
        if(!path.equals("/img/gato.jpg")){
            path="/index.html";
        }
        String query = resourceURI.getQuery();
        System.out.println("hola   " + path);
        System.out.println(query);
        byte byteFlow[] = getResourse(path);
        String header = getHeader(path);
        if(byteFlow != null){
            
            outPrintWriter.println(header);
            out.write(byteFlow);
        }
        outPrintWriter.close();
        out.close();
    }
    
    private byte[] getResourse(String path) throws FileNotFoundException, IOException{
        String localPath = "src/main/resources/public_html"+path;
        File resource = new  File(localPath);
        System.out.println(resource.exists());
        byte byteFlow[] = new byte[(int)resource.length()];
        if(resource.exists() && resource.isFile()){
            System.out.println(localPath+" este es el path");
            FileInputStream fileStream = new FileInputStream(resource);
            fileStream.read(byteFlow);
            fileStream.close();
        }else{
            byteFlow = null;
        }
        return byteFlow;
    }
    
      private String getHeader(String path) {
        String header ="HTTP/1.1 404 Not Found\r\n Content-Type: text/txt";
        if(path.equals("/") || path.equals("/index.html")){
            
            header = "HTTP/1.1 200 OK\r\n Content-Type: text/html\r\n";
        }else if(path.equals("/img/gato.jpg")){
            System.out.println("oyemeeeeeeeeeeeeeeeeeeee\n\n\n\n");
            header = "HTTP/1.1 200 OK\r\n Content-Type: image/jpg\r\n";
        }
        return header;
    }





    public static void main(String args[]) throws IOException, URISyntaxException {
        HttpServer.getInstance().start(args);
    }
}

  

//        . .
//       {Ô,Ô} 
//       /)_)
//        ""
// =======================
//  