/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kwantec.unoflip2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author rodrigo
 */
public class StealButton extends JButton{
    StealButton(){
        setText("ROBA");
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBFunctions.addCardtoHand(DBFunctions.getFirstCardFromTableWithRemove("stack"),Game.context.currentPlayerId);
                    Game.context.getHandPanel().refresh();
            }
        });
    }
}
