/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kwantec.unoflip2;

import java.util.ArrayList;


/**
 *
 * @author rodrigo
 */
public class Colors extends ArrayList < Color > {
 Colors() {
  this.add(new Color("RED", 255, 0, 0));
  this.add(new Color("GREEN", 0, 255, 0));
  this.add(new Color("BLUE", 0, 0, 255));
  this.add(new Color("YELLOW", 255, 255, 0));
  this.add(new Color("CYAN", 0, 255, 255));
  this.add(new Color("ORANGE", 255, 127, 0));
  this.add(new Color("PINK", 255, 192, 203));
  this.add(new Color("PURPLE", 128, 0, 128));
  this.add(new Color("BLACK", 0, 0, 0));
 }
 public Color getColorFromName(String name) {
  for (Color color: this) {
   if (color.name.equals(name)) {
    return color;
   }
  }
  System.out.println("COLOR NO ENCONTRADO");
  return null;
 }
}