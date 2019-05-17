/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kwantec.unoflip2;

import java.io.Serializable;
import javax.swing.JLabel;

/**
 *
 * @author rodrigo
 */
public class Context implements Serializable {
 public int currentPlayerId;
 public JLabel colorLabel;
 public String hostIp;
 public int hostPort;
 public String dbName;
 public String dbUser;
 public String dbPassword;
 public Colors colors;
 public Color selectedColor;
 public boolean flip;
 public boolean order;
 public static DiscardPanel discardPanel;
 public static HandPanel handPanel;
 Context() {
  currentPlayerId = new CurrentPlayer().getId();
  dbName = new CurrentDataBaseName().getDBName();
  dbUser = new CurrentDataBaseUser().getUser();
  dbPassword = new CurrentDataBasePassword().getPassword();
  colorLabel = new JLabel("COLOR");
  hostIp = new CurrentHost().getHost();
  hostPort = new CurrentPort().getPort();
  colors = new Colors();
  selectedColor = colors.get(0);
  flip = false;
  order = true;
  discardPanel = new DiscardPanel();
  handPanel = new HandPanel();
  colorLabel.setOpaque(true);
  colorLabel.setBackground(java.awt.Color.RED);
 }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

 public boolean isOrder() {
  return order;
 }

 public void setOrder(boolean order) {
  this.order = order;
 }

 public int getCurrentPlayerId() {
  return currentPlayerId;
 }

 public void setCurrentPlayerId(int currentPlayerId) {
  this.currentPlayerId = currentPlayerId;
 }

 public JLabel getColorLabel() {
  return colorLabel;
 }

 public void setColorLabel(JLabel colorLabel) {
  this.colorLabel = colorLabel;
 }

 public String getHostIp() {
  return hostIp;
 }

 public void setHostIp(String hostIp) {
  this.hostIp = hostIp;
 }

 public int getHostPort() {
  return hostPort;
 }

 public void setHostPort(int hostPort) {
  this.hostPort = hostPort;
 }

 public Colors getColors() {
  return colors;
 }

 public void setColors(Colors colors) {
  this.colors = colors;
 }

 public Color getSelectedColor() {
  return selectedColor;
 }

 public void setSelectedColor(Color selectedColor) {
  this.selectedColor = selectedColor;
 }

 public boolean isFlip() {
  return flip;
 }

 public void setFlip(boolean flip) {
  this.flip = flip;
 }

 public static DiscardPanel getDiscardPanel() {
  return discardPanel;
 }

 public static void setDiscardPanel(DiscardPanel discardPanel) {
  Context.discardPanel = discardPanel;
 }

 public static HandPanel getHandPanel() {
  return handPanel;
 }

 public static void setHandPanel(HandPanel handPanel) {
  Context.handPanel = handPanel;
 }

}