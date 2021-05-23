package com.company;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// Таблица SudokuCell (SudokuCell = текстовое поле)
public class SudokuTable{

    private SudokuCell[][] table;
    // табоица чисел
    private int[][] data;
    // табоица чисел со скрытыми рандомными позициями
    private int [][] data_hide;

    private JPanel container;

    // конструктор класса, где в качестве параметра передается куда ее отрисовывать (container = родитель)
    public SudokuTable(JPanel container){
        this.container = container;

        data = SudokuGenerator.getInstance().generate();
        data_hide = SudokuGenerator.getInstance().hide(data,1);

        paintTable();
     }

    public SudokuCell[][] getTable() {
        return table;
    }

    private void paintTable() {
        table = new SudokuCell[9][9];
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){

                // Создание экземпляра класса sudokucell
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


    // сравнение двух таблиц
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
