/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.escuelaing.arep.labHttpServer;

import java.io.IOException;



/**
 *
 * @author Home
 */
public class Controller {
    @Service(uri="/image")
    public static byte[] getImage() throws IOException{
        return Loader.getResourse("/img/gato.jpg");
    }
    @Service(uri="/")
    public static byte[] index() throws IOException{
        return Loader.getResourse("/index.html");
    }
    
    @Service(uri="")
    public static byte[] defaultItem() throws IOException{
        return Loader.getResourse("/index.html");
    }
    @Service(uri="/img/gato.jpg")
    public static byte[] getImagePath() throws IOException{
        return Loader.getResourse("/img/gato.jpg");
    }
    
    
    
}
