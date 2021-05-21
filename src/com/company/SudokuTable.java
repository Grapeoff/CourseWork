package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SudokuTable implements Checkable {

    private SudokuCell[][] table;
    private int[][] data;
    private int [][] data_hide;
    private Container container;

    public SudokuTable(Container container){
        this.container = container;

        data = SudokuGenerator.getInstance().generate();
        data_hide = SudokuGenerator.getInstance().hide(data,5);

        paintTable();
    }

    public SudokuCell[][] getTable() {
        return table;
    }

    private void paintTable() {
        table = new SudokuCell[9][9];
        for (int i = 0; i < 9; i++){
            for (int j = 0; i < 9; j++){
                table[i][j] = new SudokuCell((300 + container.getWidth()/2) - 90 * i,40 + 90 * j);

                String value = String.valueOf(data_hide[i][j]);
                if (!value.equals("0")){
                    table[i][j].setText(value);
                }

                table[i][j].addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        if (isWon()){
                            JOptionPane.showMessageDialog(container,"Victory");
                        }
                    }
                });

                container.add(table[i][j]);
                container.repaint();
            }
        }
    }

    @Override
    public boolean isWon() {
        for (int i = 0; i < data.length; i++){
            for (int j = 0; j < data.length; j++){
                if (!table[i][j].getText().equals(String.valueOf(data[i][j]))) {
                    return false;
                }
            }
        }
        return true;
    }
}
