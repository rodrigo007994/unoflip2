/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kwantec.unoflip2;

import java.io.Serializable;

/**
 *
 * @author rodrigo
 */
public class Card implements Serializable {
 public int id;
 public Color color;
 public Type type;
 public int number;
 public Color flipColor;
 public Type flipType;
 public int flipNumber;

 public Card(Color color, Type type, int number, Color flipColor, Type flipType, int flipNumber) {
  this.color = color;
  this.type = type;
  this.number = number;
  this.flipColor = flipColor;
  this.flipType = flipType;
  this.flipNumber = flipNumber;
 }
 public Card() {

 }

 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }

 public Color getColor() {
  return color;
 }

 public void setColor(Color color) {
  this.color = color;
 }

 public Type getType() {
  return type;
 }

 public void setType(Type type) {
  this.type = type;
 }

 public int getNumber() {
  return number;
 }

 public void setNumber(int number) {
  this.number = number;
 }

 public Color getFlipColor() {
  return flipColor;
 }

 public void setFlipColor(Color flipColor) {
  this.flipColor = flipColor;
 }

 public Type getFlipType() {
  return flipType;
 }

 public void setFlipType(Type flipType) {
  this.flipType = flipType;
 }

 public int getFlipNumber() {
  return flipNumber;
 }

 public void setFlipNumber(int flipNumber) {
  this.flipNumber = flipNumber;
 }



 @Override
 public String toString() {
  return "Card{" + "color=" + color + ", type=" + type + ", number=" + number + ", flipColor=" + flipColor + ", flipType=" + flipType + ", flipNumber=" + flipNumber + '}';
 }



}