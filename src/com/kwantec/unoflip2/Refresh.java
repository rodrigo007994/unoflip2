/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kwantec.unoflip2;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rodrigo
 */
public class Refresh extends Thread{
    public void run(){
       while(true){
           if(DBFunctions.isPlayerChange()){
            DBFunctions.setPlayerChange();
            Game.context.setOrder(DBFunctions.getDirection());
            Game.context.getDiscardPanel().refresh();
            Game.context.getHandPanel().refresh();
           }
           try {
               TimeUnit.MILLISECONDS.sleep(990);
           } catch (InterruptedException ex) {
               Logger.getLogger(Refresh.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
       
    }
    
}
