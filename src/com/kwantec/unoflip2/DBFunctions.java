/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kwantec.unoflip2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;

/**
 *
 * @author rodrigo
 */
public class DBFunctions {
 public static void addCardtoTable(Card card, String table) {
  Connection c = null;
  Statement stmt = null;
  try {
   Class.forName("org.postgresql.Driver");
   c = DriverManager.getConnection("jdbc:postgresql://" + Game.context.getHostIp() + ":" + Game.context.getHostPort() + "/"+Game.context.getDbName(), Game.context.getDbUser(), Game.context.getDbPassword());
   stmt = c.createStatement();
   stmt.executeUpdate("INSERT INTO " + table + "(card_color,card_type,card_number,card_fcolor,card_ftype,card_fnumber) VALUES ('" + card.color.name + "','" + card.type.name + "'," + card.number + ",'" + card.flipColor.name + "','" + card.flipType.name + "'," + card.flipNumber + ");");
   stmt.close();
   c.close();
  } catch (Exception e) {
   System.out.println(e.getClass().getName() + ": " + e.getMessage());
  }
 }
 public static void addCardtoHand(Card card, int player) {
  Connection c = null;
  Statement stmt = null;
  try {
   Class.forName("org.postgresql.Driver");
   c = DriverManager.getConnection("jdbc:postgresql://" + Game.context.getHostIp() + ":" + Game.context.getHostPort() + "/"+Game.context.getDbName(), Game.context.getDbUser(), Game.context.getDbPassword());
   stmt = c.createStatement();
   stmt.executeUpdate("INSERT INTO hands (card_player,card_color,card_type,card_number,card_fcolor,card_ftype,card_fnumber) VALUES (" + player + ",'" + card.color.name + "','" + card.type.name + "'," + card.number + ",'" + card.flipColor.name + "','" + card.flipType.name + "'," + card.flipNumber + ");");
   stmt.close();
   c.close();
  } catch (Exception e) {
   System.out.println(e.getClass().getName() + ": " + e.getMessage());
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
 public static Card getFirstCardFromTable(String table) {
  Connection c = null;
  Statement stmt = null;
  Card card = new Card();
  try {
   Class.forName("org.postgresql.Driver");
   c = DriverManager.getConnection("jdbc:postgresql://" + Game.context.getHostIp() + ":" + Game.context.getHostPort() + "/"+Game.context.getDbName(), Game.context.getDbUser(), Game.context.getDbPassword());
   stmt = c.createStatement();
   ResultSet rs = stmt.executeQuery("SELECT card_id,card_color,card_type,card_number,card_fcolor,card_ftype,card_fnumber FROM " + table + " ORDER BY card_id ASC LIMIT 1;");
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
 public static Card getFirstCardFromTableWithRemove(String table) {
  Connection c = null;
  Statement stmt = null;
  int cardId = 0;
  Card card = new Card();
  try {
   Class.forName("org.postgresql.Driver");
   c = DriverManager.getConnection("jdbc:postgresql://" + Game.context.getHostIp() + ":" + Game.context.getHostPort() + "/"+Game.context.getDbName(), Game.context.getDbUser(), Game.context.getDbPassword());
   stmt = c.createStatement();
   ResultSet rs = stmt.executeQuery("SELECT card_id,card_color,card_type,card_number,card_fcolor,card_ftype,card_fnumber FROM " + table + " ORDER BY card_id ASC LIMIT 1;");

   if (rs.next()) {
    Colors colors = new Colors();
    cardId = rs.getInt("card_id");
    card.setId(rs.getInt("card_id"));
    card.setColor(colors.getColorFromName(rs.getString("card_color")));
    card.setType(new Type(rs.getString("card_type")));
    card.setNumber(rs.getInt("card_number"));
    card.setFlipColor(colors.getColorFromName(rs.getString("card_fcolor")));
    card.setFlipType(new Type(rs.getString("card_ftype")));
    card.setFlipNumber(rs.getInt("card_fnumber"));
   }
   stmt.executeUpdate("DELETE FROM " + table + " WHERE card_id=" + cardId + ";");
   stmt.close();
   c.close();
   return card;
  } catch (ClassNotFoundException | SQLException e) {
   System.out.println(e.getClass().getName() + ": " + e.getMessage());
   return null;
  }
 }
 public static Cards getHandFromPlayer(int player) {
  Connection c = null;
  Statement stmt = null;
  Cards cards = new Cards();
  try {
   Class.forName("org.postgresql.Driver");
   c = DriverManager.getConnection("jdbc:postgresql://" + Game.context.getHostIp() + ":" + Game.context.getHostPort() + "/"+Game.context.getDbName(), Game.context.getDbUser(), Game.context.getDbPassword());
   stmt = c.createStatement();
   ResultSet rs = stmt.executeQuery("SELECT card_id,card_color,card_type,card_number,card_fcolor,card_ftype,card_fnumber FROM hands WHERE card_player=" + player + " ORDER BY card_color;");
   while (rs.next()) {
    Card card = new Card();
    Colors colors = new Colors();
    card.setId(rs.getInt("card_id"));
    card.setColor(colors.getColorFromName(rs.getString("card_color")));
    card.setType(new Type(rs.getString("card_type")));
    card.setNumber(rs.getInt("card_number"));
    card.setFlipColor(colors.getColorFromName(rs.getString("card_fcolor")));
    card.setFlipType(new Type(rs.getString("card_ftype")));
    card.setFlipNumber(rs.getInt("card_fnumber"));
    cards.add(card);
   }
   stmt.close();
   c.close();
   return cards;
  } catch (ClassNotFoundException | SQLException e) {
   System.out.println(e.getClass().getName() + ": " + e.getMessage());
   return null;
  }
 }
 public static void removeCardFromHand(int cardId) {
  Connection c = null;
  Statement stmt = null;
  try {
   Class.forName("org.postgresql.Driver");
   c = DriverManager.getConnection("jdbc:postgresql://" + Game.context.getHostIp() + ":" + Game.context.getHostPort() + "/"+Game.context.getDbName(), Game.context.getDbUser(), Game.context.getDbPassword());
   stmt = c.createStatement();
   stmt.executeUpdate("DELETE FROM hands WHERE card_id=" + cardId + ";");
   stmt.close();
   c.close();
  } catch (ClassNotFoundException | SQLException e) {
   System.out.println(e.getClass().getName() + ": " + e.getMessage());
  }
 }

 public static Cards getCardStack() {
  Cards cards = new Cards();
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
  Collections.shuffle(cards);
  int h = 0;
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
  Collections.shuffle(cards);
  return cards;
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
 public static boolean isPlayerTurn(int playerId) {
  Connection c = null;
  Statement stmt = null;
  boolean turn = false;
  try {
   Class.forName("org.postgresql.Driver");
   c = DriverManager.getConnection("jdbc:postgresql://" + Game.context.getHostIp() + ":" + Game.context.getHostPort() + "/"+Game.context.getDbName(), Game.context.getDbUser(), Game.context.getDbPassword());
   stmt = c.createStatement();
   ResultSet rs = stmt.executeQuery("SELECT player_turn FROM players WHERE player_id=" + playerId + ";");
   if (rs.next()) {
    turn = rs.getBoolean("player_turn");
   }
   stmt.close();
   c.close();
  } catch (ClassNotFoundException | SQLException e) {
   System.out.println(e.getClass().getName() + ": " + e.getMessage());
  }
  return turn;
 }
 public static void setTurn(int playerId) {
  Connection c = null;
  Statement stmt = null;
  try {
   Class.forName("org.postgresql.Driver");
   c = DriverManager.getConnection("jdbc:postgresql://" + Game.context.getHostIp() + ":" + Game.context.getHostPort() + "/"+Game.context.getDbName(), Game.context.getDbUser(), Game.context.getDbPassword());
   stmt = c.createStatement();
   stmt.executeUpdate("UPDATE players set player_turn=false");
   stmt.executeUpdate("UPDATE players set player_turn=true WHERE player_id=" + playerId + ";");
   stmt.close();
   c.close();
  } catch (ClassNotFoundException | SQLException e) {
   System.out.println(e.getClass().getName() + ": " + e.getMessage());
  }
 }
 public static int getNumPlayers() {
  Connection c = null;
  Statement stmt = null;
  int numPlayers = 0;
  try {
   Class.forName("org.postgresql.Driver");
   c = DriverManager.getConnection("jdbc:postgresql://" + Game.context.getHostIp() + ":" + Game.context.getHostPort() + "/"+Game.context.getDbName(), Game.context.getDbUser(), Game.context.getDbPassword());
   stmt = c.createStatement();
   ResultSet rs = stmt.executeQuery("SELECT COUNT(player_id) AS num FROM players;");
   if (rs.next()) {
    numPlayers = rs.getInt("num");
   }
   stmt.close();
   c.close();

  } catch (ClassNotFoundException | SQLException e) {
   System.out.println(e.getClass().getName() + ": " + e.getMessage());
  }
  return numPlayers;
 }
 public static void setNextTurn() {
  int numPlayers = getNumPlayers();
  int playerId = Game.context.getCurrentPlayerId();
  int nextPlayerId = playerId;
  if (Game.context.order) {
   if (playerId == numPlayers) {
    nextPlayerId = 1;
   } else {
    nextPlayerId = playerId + 1;
   }
   setTurn(nextPlayerId);
  } else {
   if (playerId == 1) {
    nextPlayerId = numPlayers;
   } else {
    nextPlayerId = playerId - 1;
   }
   setTurn(nextPlayerId);
  }

 }
 public static int getNextPlayer() {
  int numPlayers = getNumPlayers();
  int playerId = Game.context.getCurrentPlayerId();
  int nextPlayerId = playerId;
  if (Game.context.order) {
   if (playerId == numPlayers) {
    nextPlayerId = 1;
   } else {
    nextPlayerId = playerId + 1;
   }
  } else {
   if (playerId == 1) {
    nextPlayerId = numPlayers;
   } else {
    nextPlayerId = playerId - 1;
   }

  }
  return nextPlayerId;
 }
 public static void setNextTurnWithJump() {
  int playerId = Game.context.getCurrentPlayerId();
  int nextPlayerId = playerId;
  int numPlayers = getNumPlayers();
  if (Game.context.order) {
   if (playerId == numPlayers) {
    nextPlayerId = 2;
   } else if ((playerId + 1) == numPlayers) {
    nextPlayerId = 1;
   } else {
    nextPlayerId = playerId + 2;
   }
   setTurn(nextPlayerId);
  } else {
   if (playerId == 2) {
    nextPlayerId = numPlayers;
   } else if (playerId == 1) {
    nextPlayerId = numPlayers - 1;
   } else {
    nextPlayerId = playerId - 2;
   }
   setTurn(nextPlayerId);
  }
 }
public static boolean isPlayerChange() {
  Connection c = null;
  Statement stmt = null;
  boolean change = false;
  try {
   Class.forName("org.postgresql.Driver");
   c = DriverManager.getConnection("jdbc:postgresql://" + Game.context.getHostIp() + ":" + Game.context.getHostPort() + "/"+Game.context.getDbName(), Game.context.getDbUser(), Game.context.getDbPassword());
   stmt = c.createStatement();
   ResultSet rs = stmt.executeQuery("SELECT player_change FROM players WHERE player_id=" + Game.context.getCurrentPlayerId() + ";");
   if (rs.next()) {
    change = rs.getBoolean("player_change");
   }
   stmt.close();
   c.close();
  } catch (ClassNotFoundException | SQLException e) {
   System.out.println(e.getClass().getName() + ": " + e.getMessage());
  }
  return change;
 }
public static boolean getDirection() {
  Connection c = null;
  Statement stmt = null;
  boolean direction = false;
  try {
   Class.forName("org.postgresql.Driver");
   c = DriverManager.getConnection("jdbc:postgresql://" + Game.context.getHostIp() + ":" + Game.context.getHostPort() + "/"+Game.context.getDbName(), Game.context.getDbUser(), Game.context.getDbPassword());
   stmt = c.createStatement();
   ResultSet rs = stmt.executeQuery("SELECT player_direction FROM players WHERE player_id=" + Game.context.getCurrentPlayerId() + ";");
   if (rs.next()) {
    direction = rs.getBoolean("player_direction");
   }
   stmt.close();
   c.close();
  } catch (ClassNotFoundException | SQLException e) {
   System.out.println(e.getClass().getName() + ": " + e.getMessage());
  }
  return direction;
 }
public static void setDirection(boolean direction) {
  Connection c = null;
  Statement stmt = null;
  try {
   Class.forName("org.postgresql.Driver");
   c = DriverManager.getConnection("jdbc:postgresql://" + Game.context.getHostIp() + ":" + Game.context.getHostPort() + "/"+Game.context.getDbName(), Game.context.getDbUser(), Game.context.getDbPassword());
   stmt = c.createStatement();
   if(direction){
       stmt.executeUpdate("UPDATE players set player_direction=true;");
   }else{
       stmt.executeUpdate("UPDATE players set player_direction=false;");
   }
   stmt.close();
   c.close();
  } catch (ClassNotFoundException | SQLException e) {
   System.out.println(e.getClass().getName() + ": " + e.getMessage());
  }
 }
public static void setChange() {
  Connection c = null;
  Statement stmt = null;
  try {
   Class.forName("org.postgresql.Driver");
   c = DriverManager.getConnection("jdbc:postgresql://" + Game.context.getHostIp() + ":" + Game.context.getHostPort() + "/"+Game.context.getDbName(), Game.context.getDbUser(), Game.context.getDbPassword());
   stmt = c.createStatement();
   stmt.executeUpdate("UPDATE players set player_change=true;");
   stmt.close();
   c.close();
  } catch (ClassNotFoundException | SQLException e) {
   System.out.println(e.getClass().getName() + ": " + e.getMessage());
  }
 }
public static void setPlayerChange() {
  Connection c = null;
  Statement stmt = null;
  try {
   Class.forName("org.postgresql.Driver");
   c = DriverManager.getConnection("jdbc:postgresql://" + Game.context.getHostIp() + ":" + Game.context.getHostPort() + "/"+Game.context.getDbName(), Game.context.getDbUser(), Game.context.getDbPassword());
   stmt = c.createStatement();
   stmt.executeUpdate("UPDATE players set player_change=false WHERE player_id=" + Game.context.getCurrentPlayerId() + ";");
   stmt.close();
   c.close();
  } catch (ClassNotFoundException | SQLException e) {
   System.out.println(e.getClass().getName() + ": " + e.getMessage());
  }
 }
}