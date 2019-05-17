/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kwantec.unoflip2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import javax.swing.JButton;

/**
 *
 * @author rodrigo
 */
public class SetupButton  extends JButton{
    SetupButton(){
        setText("Setup");
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Setup();
            }
        });
    }
    public void Setup(){
  Connection c = null;
  Statement stmt = null;
  int numPlayers = 2;
  try {
   Class.forName("org.postgresql.Driver");
   c = DriverManager.getConnection("jdbc:postgresql://" + Game.context.getHostIp() + ":" + Game.context.getHostPort() + "/"+Game.context.getDbName(), Game.context.getDbUser(), Game.context.getDbPassword());
   stmt = c.createStatement();
   stmt.executeUpdate("DROP TABLE IF EXISTS discard;");
   stmt.executeUpdate("DROP TABLE IF EXISTS stack;");
   stmt.executeUpdate("DROP TABLE IF EXISTS hands;");
   stmt.executeUpdate("DROP TABLE IF EXISTS players;");


   stmt.executeUpdate("CREATE TABLE stack (\n" +
    "    card_id serial PRIMARY KEY,\n" +
    "    card_color varchar (200) NOT NULL,\n" +
    "    card_type varchar (200) NOT NULL,\n" +
    "    card_number INT NOT NULL,\n" +
    "    card_fcolor varchar (200) NOT NULL,\n" +
    "    card_ftype varchar (200) NOT NULL,\n" +
    "    card_fnumber INT NOT NULL" +
    ");");
   stmt.executeUpdate("CREATE TABLE discard (\n" +
    "    card_id serial PRIMARY KEY,\n" +
    "    card_color varchar (200) NOT NULL,\n" +
    "    card_type varchar (200) NOT NULL,\n" +
    "    card_number INT NOT NULL,\n" +
    "    card_fcolor varchar (200) NOT NULL,\n" +
    "    card_ftype varchar (200) NOT NULL,\n" +
    "    card_fnumber INT NOT NULL" +
    ");");
   stmt.executeUpdate("CREATE TABLE players (\n" +
    "    player_id serial PRIMARY KEY,\n" +
    "    player_name varchar (200) NOT NULL,\n" +
    "    player_turn BOOLEAN DEFAULT false," +
    "    player_change BOOLEAN DEFAULT true," +
    "    player_direction BOOLEAN DEFAULT true" +
    ");");
   stmt.executeUpdate("CREATE TABLE hands (\n" +
    "    card_id serial PRIMARY KEY,\n" +
    "    card_player INT REFERENCES players(player_id),\n" +
    "    card_color varchar (200) NOT NULL,\n" +
    "    card_type varchar (200) NOT NULL,\n" +
    "    card_number INT NOT NULL,\n" +
    "    card_fcolor varchar (200) NOT NULL,\n" +
    "    card_ftype varchar (200) NOT NULL,\n" +
    "    card_fnumber INT NOT NULL" +
    ");");
   Cards cards = getCardStack2();
   int cardn = 0;
   for (int k = 1; k <= numPlayers; k++) {
    stmt.executeUpdate("INSERT INTO players (player_name) VALUES ('player_" + k + "');");
    for (int h = 0; h < 7; h++) {
     stmt.executeUpdate("INSERT INTO hands (card_player,card_color,card_type,card_number,card_fcolor,card_ftype,card_fnumber) VALUES (" + k + ",'" + cards.get(cardn).color.name + "','" + cards.get(cardn).type.name + "'," + cards.get(cardn).number + ",'" + cards.get(cardn).flipColor.name + "','" + cards.get(cardn).flipType.name + "'," + cards.get(cardn).flipNumber + ");");
     cards.remove(cardn);
     cardn++;
    }
   }
   //cardStack
   stmt.executeUpdate("INSERT INTO discard(card_color,card_type,card_number,card_fcolor,card_ftype,card_fnumber) VALUES ('" + cards.get(cardn).color.name + "','" + cards.get(cardn).type.name + "'," + cards.get(cardn).number + ",'" + cards.get(cardn).flipColor.name + "','" + cards.get(cardn).flipType.name + "'," + cards.get(cardn).flipNumber + ");");
   cards.remove(cardn);
   cardn++;
   for (Card card: cards) {
    stmt.executeUpdate("INSERT INTO stack(card_color,card_type,card_number,card_fcolor,card_ftype,card_fnumber) VALUES ('" + card.color.name + "','" + card.type.name + "'," + card.number + ",'" + card.flipColor.name + "','" + card.flipType.name + "'," + card.flipNumber + ");");
   }
   stmt.executeUpdate("UPDATE players set player_turn=true WHERE player_id=1;");

   stmt.close();
   c.close();
   Card card = getLastCardFromTable("stack");
   System.out.println(card.color.name);
  } catch (ClassNotFoundException | SQLException e) {
   System.out.println(e.getClass().getName() + "ccc: " + e.getMessage());
  }
    }
    public static Card getLastCardFromTable(String table) {
  Connection c = null;
  Statement stmt = null;
  Card card = new Card();
  try {
   Class.forName("org.postgresql.Driver");
   c = DriverManager.getConnection("jdbc:postgresql://" + Game.context.getHostIp() + ":" + Game.context.getHostPort() + "/"+Game.context.getDbName(), Game.context.getDbUser(), Game.context.getDbPassword());
   stmt = c.createStatement();
   ResultSet rs = stmt.executeQuery("SELECT card_id,card_color,card_type,card_number,card_fcolor,card_ftype,card_fnumber FROM " + table + " ORDER BY card_id DESC LIMIT 1;");
   if (rs.next()) {
    Colors colors = new Colors();
    card.setId(rs.getInt("card_id"));
    card.setColor(colors.getColorFromName(rs.getString("card_color")));
    card.setType(new Type(rs.getString("card_type")));
    card.setNumber(rs.getInt("card_number"));
    card.setFlipColor(colors.getColorFromName(rs.getString("card_fcolor")));
    card.setFlipType(new Type(rs.getString("card_ftype")));
    card.setFlipNumber(rs.getInt("card_fnumber"));
   }
   stmt.close();
   c.close();
   return card;
  } catch (ClassNotFoundException | SQLException e) {
   System.out.println(e.getClass().getName() + ": " + e.getMessage());
   return null;
  }
 }
    public static Cards getCardStack2() {
  Cards cards = new Cards();
  for (int k = 0; k < 8; k++) {
   for (Color color: new NormalColors()) {
    for (Type type: new NormalTypes()) {
     if (type.name.equals("NORMAL")) {
      for (int c = 1; c <= 9; c++) {
       Card card = new Card();
       card.setColor(color);
       card.setType(type);
       card.setNumber(c);
       cards.add(card);
      }
     } else {
      Card card = new Card();
      card.setColor(color);
      card.setType(type);
      card.setNumber(0);
      cards.add(card);
     }
    }


   }
  }
  Collections.shuffle(cards);
  int h = 0;
  for (int k = 0; k < 8; k++) {
   for (Color color: new FlipColors()) {
    for (Type type: new FlipTypes()) {
     if (type.name.equals("NORMAL")) {
      for (int c = 1; c <= 9; c++) {
       cards.get(h).setFlipColor(color);
       cards.get(h).setFlipType(type);
       cards.get(h).setFlipNumber(c);
       h++;
      }
     } else {
      cards.get(h).setFlipColor(color);
      cards.get(h).setFlipType(type);
      cards.get(h).setFlipNumber(0);
      h++;
     }
    }


   }
  }
  Collections.shuffle(cards);
  return cards;
 }
}
