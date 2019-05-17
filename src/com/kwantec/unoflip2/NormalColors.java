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
public class NormalColors extends ArrayList<Color>{
    NormalColors(){
        this.add(new Color("GREEN", 0, 255, 0));
        this.add(new Color("RED", 255, 0, 0));
        this.add(new Color("BLUE", 0, 0, 255));
        this.add(new Color("YELLOW", 255, 255, 0));
    }
    
}
