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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author rodrigo
 */
public class VisualCard extends JPanel {
 public Card displayCard;


 VisualCard(Card card) {
  displayCard = new Card();
  if (Game.context != null && Game.context.isFlip() == true) {
   displayCard.color = card.flipColor;
   displayCard.number = card.flipNumber;
   displayCard.type = card.flipType;
  } else {
   displayCard.color = card.color;
   displayCard.number = card.number;
   displayCard.type = card.type;
  }
  //this.add(new SelectCardButton(context,card));

  ///
  addMouseListener(new MouseAdapter() {
   @Override
   public void mouseClicked(MouseEvent e) {
    playCard(card, DBFunctions.getLastCardFromTable("discard"));
   } //DBFuntions.addCardtoHand(DBFuntions.getFirstCardFromTableWithRemove("stack"),Game.context.currentPlayerId);

  });
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
  if (displayCard.type.name.equals("COMODIN") || displayCard.type.name.equals("TOMACOLOR")) {
   g2.setColor(java.awt.Color.BLACK);
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

 public void playCard(Card handCard, Card tableCard) {
  if (!DBFunctions.isPlayerTurn(Game.context.currentPlayerId)) {
   JOptionPane.showMessageDialog(Game.context.getHandPanel(), "No es tu turno - Robas 2");
   DBFunctions.addCardtoHand(DBFunctions.getFirstCardFromTableWithRemove("stack"), Game.context.currentPlayerId);
   DBFunctions.addCardtoHand(DBFunctions.getFirstCardFromTableWithRemove("stack"), Game.context.currentPlayerId);
   Game.context.getHandPanel().refresh();
  } else {

   if (Game.context.isFlip()) {
    if (handCard.flipType.name.equals("TOMACOLOR")) {
     handCard.flipColor.name = Game.context.getSelectedColor().name;
     playCardSuccess(handCard);
    } else if (handCard.flipColor.name.equals(tableCard.flipColor.name)) {
     playCardSuccess(handCard);
    } else if (handCard.flipNumber == tableCard.flipNumber && handCard.flipNumber != 0) {
     playCardSuccess(handCard);
    } else if (handCard.flipType.name.equals(tableCard.flipType.name) && !handCard.flipType.name.equals("NORMAL")) {
     playCardSuccess(handCard);
    } else {
     JOptionPane.showMessageDialog(Game.context.getHandPanel(), "Carta no compatible - Robas 2");
     DBFunctions.addCardtoHand(DBFunctions.getFirstCardFromTableWithRemove("stack"), Game.context.currentPlayerId);
     DBFunctions.addCardtoHand(DBFunctions.getFirstCardFromTableWithRemove("stack"), Game.context.currentPlayerId);
     Game.context.getHandPanel().refresh();
    }
   } else {
    if (handCard.type.name.equals("COMODIN")) {
     handCard.color.name = Game.context.getSelectedColor().name;
     playCardSuccess(handCard);
    } else if (handCard.color.name.equals(tableCard.color.name)) {
     playCardSuccess(handCard);
    } else if (handCard.number == tableCard.number && handCard.number != 0) {
     playCardSuccess(handCard);
    } else if (handCard.type.name.equals(tableCard.type.name) && !handCard.type.name.equals("NORMAL")) {
     playCardSuccess(handCard);
    } else {
     JOptionPane.showMessageDialog(Game.context.getHandPanel(), "Carta no compatible - Robas 2");
     DBFunctions.addCardtoHand(DBFunctions.getFirstCardFromTableWithRemove("stack"), Game.context.currentPlayerId);
     DBFunctions.addCardtoHand(DBFunctions.getFirstCardFromTableWithRemove("stack"), Game.context.currentPlayerId);
     Game.context.getHandPanel().refresh();
    }
   }
  }
 }
 public void playCardSuccess(Card handCard) {
  if (Game.context.isFlip()) {
   if (handCard.flipType.name.equals("FLIP")) {
    Game.context.setFlip(false);
    Game.context.getColorLabel().setBackground(java.awt.Color.RED);
    Game.context.setSelectedColor(Game.context.getColors().get(0));
   }
  } else {
   if (handCard.type.name.equals("FLIP")) {
    Game.context.setFlip(true);
    Game.context.getColorLabel().setBackground(java.awt.Color.CYAN);
    Game.context.setSelectedColor(Game.context.getColors().get(4));
   }
  }


  DBFunctions.removeCardFromHand(handCard.getId());
  DBFunctions.addCardtoTable(handCard, "discard");

  if (!Game.context.isFlip() && handCard.type.name.equals("TOMAUNO")) {
   DBFunctions.addCardtoHand(DBFunctions.getFirstCardFromTableWithRemove("stack"), DBFunctions.getNextPlayer());
   DBFunctions.setNextTurnWithJump();
  } else if (Game.context.isFlip() && handCard.flipType.name.equals("TOMACINCO")) {
   DBFunctions.addCardtoHand(DBFunctions.getFirstCardFromTableWithRemove("stack"), DBFunctions.getNextPlayer());
   DBFunctions.addCardtoHand(DBFunctions.getFirstCardFromTableWithRemove("stack"), DBFunctions.getNextPlayer());
   DBFunctions.addCardtoHand(DBFunctions.getFirstCardFromTableWithRemove("stack"), DBFunctions.getNextPlayer());
   DBFunctions.addCardtoHand(DBFunctions.getFirstCardFromTableWithRemove("stack"), DBFunctions.getNextPlayer());
   DBFunctions.addCardtoHand(DBFunctions.getFirstCardFromTableWithRemove("stack"), DBFunctions.getNextPlayer());
   DBFunctions.setNextTurnWithJump();
  } else if (!Game.context.isFlip() && handCard.type.name.equals("REVERSA")) {
   if (Game.context.isOrder()) {
    Game.context.setOrder(false);
   } else {
    Game.context.setOrder(true);
   }
   DBFunctions.setDirection(Game.context.isOrder());
   DBFunctions.setNextTurn();
  } else if (!Game.context.isFlip() && handCard.type.name.equals("SALTAUNO")) {
   DBFunctions.setNextTurnWithJump();
  } else if (Game.context.isFlip() && handCard.type.name.equals("SALTATODOS")){
      
  }else if (Game.context.isFlip() && handCard.flipType.name.equals("TOMACOLOR")) {
      Card card=new Card();
      if(Game.context.isFlip()){
          do{
          card=DBFunctions.getFirstCardFromTableWithRemove("stack");
          DBFunctions.addCardtoHand(card, DBFunctions.getNextPlayer());
        }while(!card.flipColor.name.equals(Game.context.selectedColor.name));
      }else{
      do{
          card=DBFunctions.getFirstCardFromTableWithRemove("stack");
          DBFunctions.addCardtoHand(card, DBFunctions.getNextPlayer());
      }while(!card.color.name.equals(Game.context.selectedColor.name));
      }
      
  DBFunctions.setNextTurnWithJump();
  } else {
   DBFunctions.setNextTurn();
  }
  DBFunctions.setChange();
  Game.context.getHandPanel().refresh();
  Game.context.getDiscardPanel().refresh();


 }
}