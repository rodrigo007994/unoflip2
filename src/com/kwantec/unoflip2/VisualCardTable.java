/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kwantec.unoflip2;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author rodrigo
 */
public class VisualCardTable extends JPanel {
 public Card displayCard;


 VisualCardTable(Card card) {
  displayCard = card;
  if (Game.context.isFlip() == false) {
   displayCard.color = card.color;
   displayCard.number = card.number;
   displayCard.type = card.type;
  } else {
   displayCard.color = card.flipColor;
   displayCard.number = card.flipNumber;
   displayCard.type = card.flipType;
  }

 }
 public void paint(Graphics g) {
  super.paint(g);
  Graphics2D g2 = (Graphics2D) g;
  g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
  switch (displayCard.color.name) {
   case "BLACK":
    g2.setColor(java.awt.Color.BLACK);
    break;
   case "BLUE":
    g2.setColor(java.awt.Color.BLUE);
    break;
   case "GREEN":
    g2.setColor(java.awt.Color.GREEN);
    break;
   case "YELLOW":
    g2.setColor(java.awt.Color.YELLOW);
    break;
   case "RED":
    g2.setColor(java.awt.Color.RED);
    break;
   case "CYAN":
    g2.setColor(java.awt.Color.CYAN);
    break;
   case "ORANGE":
    g2.setColor(java.awt.Color.ORANGE);
    break;
   case "PINK":
    g2.setColor(java.awt.Color.PINK);
    break;
   case "PURPLE":
    Colors colors = new Colors();
    g2.setColor(new java.awt.Color(colors.getColorFromName("PURPLE").red, colors.getColorFromName("PURPLE").green, colors.getColorFromName("PURPLE").blue));
    break;
  }
  g2.fillRect(4, 50, 130, 160);
  g2.setColor(java.awt.Color.WHITE);
  if (displayCard.number == 0) {
   g2.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 25));

   switch (displayCard.type.name) {
    case "COMODIN":
     g2.drawString("COMODIN", 10, 90);
     break;
    case "FLIP":
     g2.drawString("FLIP", 10, 90);
     break;
    case "REVERSA":
     g2.drawString("REVERSA", 10, 90);
     break;
    case "SALTAUNO":
     g2.drawString("SALTA", 10, 90);
     break;
    case "SALTATODOS":
     g2.setFont(new Font("Serif", Font.PLAIN, 18));
     g2.drawString("SALTA", 10, 90);
     g2.drawString("TODOS", 10, 120);
     break;
    case "TOMAUNO":
     g2.setFont(new Font("Serif", Font.PLAIN, 45));
     g2.drawString("+1", 20, 100);
     break;
    case "TOMACINCO":
     g2.setFont(new Font("Serif", Font.PLAIN, 45));
     g2.drawString("+5", 20, 100);
     break;
    case "TOMACOLOR":
     g2.setFont(new Font("Serif", Font.PLAIN, 18));
     g2.drawString("TOMA", 10, 90);
     g2.drawString("COLOR", 10, 120);
     break;

   }
  } else {
   g2.setFont(new Font("Serif", Font.PLAIN, 60));
   g2.drawString(Integer.toString(displayCard.number), 40, 120);
  }

 }

}