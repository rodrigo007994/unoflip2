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
public class FlipColors extends ArrayList<Color>{
    FlipColors(){
        this.add(new Color("CYAN", 0, 255, 255));
        this.add(new Color("ORANGE", 255, 127, 0));
        this.add(new Color("PINK", 255, 192, 203));
        this.add(new Color("PURPLE", 128, 0, 128));
    }
}
