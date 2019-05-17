/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kwantec.unoflip2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rodrigo
 */
public class CurrentPort{
    
    public int getPort(){
        try {
            Properties properties = new Properties();
            InputStream is= new FileInputStream("config.properties");
            properties.load(is);
            is.close();
            return Integer.parseInt(properties.getProperty("port"));
        } catch (IOException ex) {
            Logger.getLogger(CurrentPort.class.getName()).log(Level.SEVERE, null, ex);
            return 999999;
        }
    
    }
    
}
