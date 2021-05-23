package com.company;

import java.util.*;

public class SudokuGenerator {

    //Единственный экземпляр класса SudokuGenerator
    private static SudokuGenerator instance;

    private SudokuGenerator(){};

    //Метод получения единственного экземпляра класса
    public static SudokuGenerator getInstance(){
        if (instance == null){
            instance = new SudokuGenerator();
        }
        return instance;
    }

    public int[][] generate(){
        int[][] arr = createBase(9);
        int r = new Random().nextInt(1000);

        return mix(arr,0,r);
    }

    public int[][] mix(int[][] table, int counter, int exit){
        int[][] arr = transposing(swapColsArea(swapRowsArea(swapColsSmall(swapRowsSmall(table)))));

        if (counter == exit){
            return arr;
        }

        int n = ++counter;
        return mix(arr,n,exit);
    }

    // Создается латинский квадрат
    public int[][] createBase(int difficulty){
        int[][] arr = new int[difficulty][difficulty];
        for (int i = 0; i < difficulty; i++){
            for(int j = 0; j < difficulty; j++){
                arr[i][j] = ((i * 3) + (i / 3) + j) % (3 * 3) + 1;
            }
        }

        return arr;
    }

    // перевод из строчек в колонки
    public int[][] transposing(int[][] table) {
        int[][] arr = new int[table.length][table.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                arr[j][i] = table[i][j];
            }
        }
        return arr;
    }

    // смена 2 случайных разных рядов в случаном регионе
    public int[][] swapRowsSmall(int[][] table){
        Random rnd = new Random();
        int region = rnd.nextInt(3);

        int row1 = 0;
        int row2 = 0;

        while (row1 == row2){
            row1 = getRandomFromRegion(region);
            row2 = getRandomFromRegion(region);
        }

        int [][] arr = new int[table.length][table.length];
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr.length; j++){
                if(i == row1){
                    arr[row1][j] = table[row2][j];
                    continue;
                }else if(i == row2){
                    arr[row2][j] = table[row1][j];
                    continue;
                }
                arr[i][j] = table[i][j];
            }
        }
        return arr;
    }

    // смена 2 случайных разных колонок в случаном регионе
    public int[][] swapColsSmall(int[][] table){
        Random rnd = new Random();
        int region = rnd.nextInt(3);

        int col1 = 0;
        int col2 = 0;

        while (col1 == col2){
            col1 = getRandomFromRegion(region);
            col2 = getRandomFromRegion(region);
        }
        int [][] arr = new int[table.length][table.length];
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr.length; j++){
                if(j == col1){
                    arr[i][col1] = table[i][col2];
                    continue;
                }else if(j == col2){
                    arr[i][col2] = table[i][col1];
                    continue;
                }
                arr[i][j] = table[i][j];
            }
        }
        return arr;
    }

    // смена 2 случайных регионов по Х
    public int[][] swapRowsArea(int[][] table){
        Random rnd = new Random();
        int region1 = rnd.nextInt(3);
        int region2 = rnd.nextInt(3);

        while (region1 == region2){
            region1 = rnd.nextInt(3);
            region2 = rnd.nextInt(3);
        }

        if(region1 > region2){
            int temp = region1;
            region1 = region2;
            region2 = temp;
        }

        int [][] arr = new int[table.length][table.length];
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr.length; j++) {
                if (i < (3 * region1) + 3 && i >= (3 * region1)) {
                    arr[i][j] = table[i + 3 * (region2 - region1)][j];
                    continue;
                } else if (i >= (3 * region2) && i < (3 * region2) + 3) {
                    arr[i][j] = table[i - (3 * (region2 - region1))][j];
                    continue;
                }
                arr[i][j] = table[i][j];
            }
        }
        return arr;
    }

    // смена 2 случайных регионов по Y
    public int[][] swapColsArea(int[][] table){
        Random rnd = new Random();
        int region1 = rnd.nextInt(3);
        int region2 = rnd.nextInt(3);

        while (region1 == region2){
            region1 = rnd.nextInt(3);
            region2 = rnd.nextInt(3);
        }

        if(region1 > region2){
            int temp = region1;
            region1 = region2;
            region2 = temp;
        }

        int [][] arr = new int[table.length][table.length];
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr.length; j++) {
                if (j < (3 * region1) + 3 && j >= (3 * region1)) {
                    arr[i][j] = table[i][j + 3 * (region2 - region1)];
                    continue;
                } else if (j >= (3 * region2) && j < (3 * region2) + 3) {
                    arr[i][j] = table[i][j- (3 * (region2 - region1))];
                    continue;
                }
                arr[i][j] = table[i][j];
            }
        }
        return arr;
    }

    // генерация случайного ряда или колонки в зависимости от региона
    public int getRandomFromRegion(int region){
        int min = 3 * region;
        int max = min + 3;

        return new Random().ints(min,max).findFirst().getAsInt();
    }

    public int[][] hide(int[][] table,int difficulty){
        int[][] arr = new int[table.length][table.length];

        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr.length; j++){
                arr[i][j] = table[i][j];
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++){
                for (int k = 0;k < difficulty; k++){
                    int x = getRandomFromRegion(i);
                    int y = getRandomFromRegion(j);
                    arr[x][y] = 0;
                }
            }
        }
        return arr;
    }
}