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
public class CurrentPlayer{
    
    public int getId(){
        try {
            Properties properties = new Properties();
            InputStream is= new FileInputStream("config.properties");
            properties.load(is);
            is.close();
            return Integer.parseInt(properties.getProperty("currentplayer"));
        } catch (IOException ex) {
            Logger.getLogger(CurrentPlayer.class.getName()).log(Level.SEVERE, null, ex);
            return 99999;
        }
    
    }
    
}
