/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kwantec.unoflip2;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;



/**
 *
 * @author rodrigo
 */
public class UnoFlip2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Game.setContext(new Context());
        Game.context.handPanel.refresh();
        Game.context.discardPanel.refresh();
        UnoFlipFrame uff = new UnoFlipFrame();
        uff.setVisible(true);
        Refresh refresh = new Refresh();
        refresh.start();
        }
    
  
}
