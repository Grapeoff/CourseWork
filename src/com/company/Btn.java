package com.company;

import javax.swing.*;
import java.awt.*;

public class Btn extends JButton {

    public Btn(int x, int y, int width, int heigth, Container container) {
        setBounds(x,y,width,heigth);
        setBackground(Color.ORANGE);
        setFocusPainted(false);
        setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        container.add(this);
    }
}
