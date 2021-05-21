package com.company;

import javax.swing.*;
import java.awt.*;

public class SudokuCell extends JTextField {

    private Font font = new Font("Segoe UI", Font.PLAIN,26);

    public SudokuCell(int x,int y){
        setLocation(x,y);
        setSize(90,90);
        setFont(font);
        setHorizontalAlignment(0);
    }
}
