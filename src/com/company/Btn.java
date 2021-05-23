package com.company;

import javax.swing.*;
import java.awt.*;

public class Btn extends JButton {

    public Btn(int x, int y, int width, int heigth, Container container) {
        setBounds(x,y,width,heigth);
        setBackground(Color.YELLOW);
        setFocusPainted(false);
        setBorder(BorderFactory.createLineBorder(Color.WHITE,2));

        container.add(this);
    }
}
