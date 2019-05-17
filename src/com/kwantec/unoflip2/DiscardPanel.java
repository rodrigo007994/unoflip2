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
public class DiscardPanel extends JPanel{
    
    DiscardPanel(){
    setLayout(new GridLayout(1,0));
    }
    public void refresh(){
        removeAll();
        add(new VisualCardTable(DBFunctions.getLastCardFromTable("discard")));
        revalidate();
        repaint();
    }
}
