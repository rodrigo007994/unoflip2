/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kwantec.unoflip2;

import javax.swing.JPanel;

/**
 *
 * @author rodrigo
 */
public class MainPanel extends JPanel{
    MainPanel(){
        setLayout(new java.awt.GridLayout());
        add(new TablePanel());
        add(Game.context.getHandPanel());
        
    }
    
}
