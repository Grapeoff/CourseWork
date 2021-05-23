package com.company;

import javax.swing.*;
// Библиотека создания форм окон
import java.awt.*;
//
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
// Отслеживание нажатия клавиш
//

//extends JFrame наследование создания форм
public class MainWindow extends JFrame {
    private int width,heigth;
    private JPanel mainPanel;
    final Font font;
    private SudokuTable table;
    private ArrayList<Btn> buttons;

    // Конструктор окна приложения
    public MainWindow(int width, int heigth){
        font = new Font("Segoe UI",Font.PLAIN,22);
        buttons = new ArrayList<>();

        this.width = width;
        this.heigth = heigth;

        setSize(this.width,this.heigth);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setTitle("Судокy");

        mainPanel = new JPanel();
        mainPanel.setBounds(0,0,width,heigth);
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setLayout(null);
        add(mainPanel);

        initGUI();

        repaint();
    }

    // метод создания кнопок ( + любого другого элемента интерфейса)
    private void initGUI(){
        Btn button_start = new Btn((width-300)/2,heigth/2 - 150,300,50,mainPanel);
        button_start.setText("Старт");
        button_start.setFont(font);
        buttons.add(button_start);

        Btn previous_button = new Btn(5,5,50,50,mainPanel);
        previous_button.setText("<");
        previous_button.setFont(font);
        previous_button.setVisible(false);
        buttons.add(previous_button);

        button_start.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                buttons.get(0).setVisible(false);
                buttons.get(1).setVisible(true);

                table = new SudokuTable(mainPanel);
            }
        });

        previous_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                buttons.get(0).setVisible(true);
                buttons.get(1).setVisible(false);

                for (int i = 0; i < table.getTable().length; i++) {
                    for (int j = 0; j < table.getTable().length; j++) {
                        mainPanel.remove(table.getTable()[i][j]);
                    }
                }

                mainPanel.repaint();
            }
        });
    }
}
