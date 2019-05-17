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
public class NormalTypes extends ArrayList<Type>{
    NormalTypes(){
        add(new Type("NORMAL"));
        add(new Type("COMODIN"));
        add(new Type("FLIP"));
        add(new Type("REVERSA"));
        add(new Type("SALTAUNO"));
        add(new Type("TOMAUNO"));
    }
    
}
