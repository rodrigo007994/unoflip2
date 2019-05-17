/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kwantec.unoflip2;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author rodrigo
 */
public class ControlPanel extends JPanel {
 ControlPanel() {
  setLayout(new GridLayout(7, 0));
  add(new UnoButton());
  add(new StealButton());
  add(new FlipButton());
  if(Game.context.getCurrentPlayerId()==1){add(new SetupButton());}
  add(Game.context.getColorLabel());
  for (Color color: new NormalColors()) {
   addActionListenerColor(new JButton(color.name));
  }
  for (Color color: new FlipColors()) {
   addActionListenerColorFlip(new JButton(color.name));
  }
 }
 public void addActionListenerColorFlip(JButton button) {
  button.addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
    if (Game.context.isFlip()) {
     switch (button.getText()) {

      case "CYAN":
       Game.context.getColorLabel().setBackground(java.awt.Color.CYAN);
       Game.context.setSelectedColor(Game.context.getColors().getColorFromName(button.getText()));
       break;
      case "ORANGE":
       Game.context.getColorLabel().setBackground(java.awt.Color.ORANGE);
       Game.context.setSelectedColor(Game.context.getColors().getColorFromName(button.getText()));
       break;
      case "PINK":
       Game.context.getColorLabel().setBackground(java.awt.Color.PINK);
       Game.context.setSelectedColor(Game.context.getColors().getColorFromName(button.getText()));
       break;
      case "PURPLE":
       Colors colors = new Colors();
       Game.context.getColorLabel().setBackground(new java.awt.Color(colors.getColorFromName("PURPLE").red, colors.getColorFromName("PURPLE").green, colors.getColorFromName("PURPLE").blue));
       Game.context.setSelectedColor(Game.context.getColors().getColorFromName(button.getText()));
       break;
     }
    }

   }
  });
  add(button);
  revalidate();
  repaint();
 }
 public void addActionListenerColor(JButton button) {
  button.addActionListener(new ActionListener() {
   @Override
   public void actionPerformed(ActionEvent e) {
    if (!Game.context.isFlip()) {
     switch (button.getText()) {
      case "BLUE":
       Game.context.getColorLabel().setBackground(java.awt.Color.BLUE);
       Game.context.setSelectedColor(Game.context.getColors().getColorFromName(button.getText()));
       break;
      case "GREEN":
       Game.context.getColorLabel().setBackground(java.awt.Color.GREEN);
       Game.context.setSelectedColor(Game.context.getColors().getColorFromName(button.getText()));
       break;
      case "YELLOW":
       Game.context.getColorLabel().setBackground(java.awt.Color.YELLOW);
       Game.context.setSelectedColor(Game.context.getColors().getColorFromName(button.getText()));
       break;
      case "RED":
       Game.context.getColorLabel().setBackground(java.awt.Color.RED);
       Game.context.setSelectedColor(Game.context.getColors().getColorFromName(button.getText()));
       break;

     }
    }
   }
  });
  add(button);
  revalidate();
  repaint();
 }
}