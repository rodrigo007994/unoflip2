/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kwantec.unoflip2;

import javax.swing.JFrame;

/**
 *
 * @author rodrigo
 */
public class UnoFlipFrame extends JFrame{
    UnoFlipFrame(){
        add(new MainPanel());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(20,20, 600,400);
        setTitle("UnoFlip!");
    }
}
