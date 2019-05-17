/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
package com.kwantec.unoflip2;

import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 *
 * @author rodrigo
 */
public class HandPanel extends JPanel{
    HandPanel(){
        setLayout(new GridLayout(4,0));

    }
    public void refresh(){
        this.removeAll();
            for(Card card : DBFunctions.getHandFromPlayer(Game.context.currentPlayerId))
                add(new VisualCard(card));
        
        revalidate();
        repaint();
    }
    
}
