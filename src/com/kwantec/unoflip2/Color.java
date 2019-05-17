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
public class Color implements Serializable{
    String name;
    int red;
    int green;
    int blue;
    int rgb;

    public Color(String name, int red, int green, int blue) {
        this.name = name;
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.rgb = rgb = 0xFFFF * red + 0xFF * green + blue;
    }
    
    
}
