package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class MainWindow extends JFrame {
    private int width,heigth;
    private JPanel mainPanel;
    final Font font;
    private SudokuTable table;

    public MainWindow(int width, int heigth){
        font = new Font("Segoe UI",Font.PLAIN,22);

        this.width = width;
        this.heigth = heigth;

        setSize(this.width,this.heigth);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        mainPanel = new JPanel();
        mainPanel.setBounds(0,0,width,heigth);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(null);
        add(mainPanel);

        initGUI();

        repaint();
    }

    private void initGUI(){
        Btn button_start = new Btn((width-300)/2,heigth/2 - 150,300,50,mainPanel);
        button_start.setText("Start");
        button_start.setFont(font);

        Btn previous_button = new Btn(5,5,50,50,mainPanel);
        previous_button.setText("Вернуться");
        previous_button.setFont(font);
        previous_button.setVisible(false);

        button_start.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button_start.setVisible(false);
                previous_button.setVisible(true);

                table = new SudokuTable(mainPanel);
            }
        });

        previous_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button_start.setVisible(true);
                previous_button.setVisible(false);

                Arrays.stream(table.getTable()).forEach(i -> Arrays.stream(i).forEach(j -> mainPanel.remove(j )));
                mainPanel.repaint();
            }
        });
    }
}
