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
public class TablePanel extends JPanel{
    TablePanel(){
        setLayout(new GridLayout(2,0));
        add(new ControlPanel());
        add(Game.context.getDiscardPanel());
    }
}
