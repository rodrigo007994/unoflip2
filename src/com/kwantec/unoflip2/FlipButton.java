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
public class FlipButton extends JButton{
    FlipButton(){
    this.setText("Flip");
    this.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        if(Game.context.isFlip()){
        Game.context.setFlip(false);
        Game.context.getColorLabel().setBackground(java.awt.Color.RED);
        Game.context.setSelectedColor(Game.context.colors.get(0));
        }else{
        Game.context.setFlip(true);
        Game.context.getColorLabel().setBackground(java.awt.Color.CYAN);
        Game.context.setSelectedColor(Game.context.colors.get(4));
        }
        Game.context.getHandPanel().refresh();
        Game.context.getDiscardPanel().refresh();
    }
});
    }
    
}
