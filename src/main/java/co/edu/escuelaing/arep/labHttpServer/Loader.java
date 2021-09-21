/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.escuelaing.arep.labHttpServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Home
 */
public class Loader {
    public static byte[] getResourse(String path) throws FileNotFoundException, IOException{
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
}
