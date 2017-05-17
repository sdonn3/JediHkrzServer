package com.jedihkrz.server.services;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by steven.donnelly on 5/16/17.
 */
public class FileWriter {
    public void writeFile (byte[] bytes, String path){
        try {
            FileOutputStream fos = new FileOutputStream(new File(path));
            fos.write(bytes);
            fos.close();
        }
        catch (Exception e){

        }
    }
}
