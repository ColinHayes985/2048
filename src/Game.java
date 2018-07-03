import java.util.Random;
import java.util.Scanner;

public class Game {
    static boolean win=false;
    static boolean lose=false;
    public static void main(String[] args){
        int [][] grid = new int[4][4];
        placeTile(grid);
        placeTile(grid);
        Scanner s = new Scanner(System.in);
        //TODO:Win Condition:2048
        //TODO:Lose Condition:Board filled AND No more moves
        //TODO:Add error handling so if nothing moves(ex:move right when all tiles are in right-most position) no tile is added
        //TODO:Turn it into a GUI?????????
        while (win==false) {
            print(grid);
            char direction = s.next().charAt(0);
            move(direction, grid);
            //System.out.println("Move:\n");//For Debugging
            //print(grid);//For Debugging
            //Took combine out of move because move is used again but tiles aren't combined again
            combine(direction,grid);
            //System.out.println("Combine:\n");//For Debugging
            //print(grid);//For Debugging
            move(direction,grid);
            //System.out.println("Move:\n");//For Debugging
            //print(grid);//For Debugging
            placeTile(grid);
            //System.out.println("Place:\n");//For Debugging
            checkWin(grid);
        }

    }
    public static void print(int[][] array) {
        for (int i = 0; i < 4; i++) {
            System.out.println("");
            for (int j = 0; j < 4; j++) {
                System.out.format("%04d ", array[i][j]);
            }
            System.out.println("");
        }
    }
    public static void placeTile(int[][] array) {
        Random rand = new Random();
        int i = rand.nextInt(4);
        int j = rand.nextInt(4);
        while (array[i][j] != 0) {
            i = rand.nextInt(4);
            j = rand.nextInt(4);
        }
        int num = rand.nextInt(10);
        if (num == 0) {
            array[i][j]=4;
        }
        else{
            array[i][j]=2;
        }

    }
    public static void move(char direction, int[][] array) {
        int k = 3;
        if (direction == 'w' || direction == 'W') {
            while (k > 0) {
                for (int i = 1; i < array.length; i++) {
                    for (int j = 0; j < array[i].length; j++) {
                        if (array[i][j] != 0 && array[i - 1][j] == 0) {
                            array[i - 1][j] = array[i][j];
                            array[i][j] = 0;
                        }
                    }
                }
                k--;
            }
        } else if (direction == 'a' || direction == 'A') {
            while (k > 0) {
                for (int i = 0; i < array.length; i++) {
                    for (int j = 1; j < array[i].length; j++) {
                        if (array[i][j] != 0 && array[i][j - 1] == 0) {
                            array[i][j - 1] = array[i][j];
                            array[i][j] = 0;
                        }
                    }
                }
                k--;
            }
        } else if (direction=='s'||direction=='S') {
            while (k > 0) {
                for (int i = 2; i >= 0; i--) {
                    for (int j = 0; j < array[i].length; j++) {
                        if (array[i][j] != 0 && array[i + 1][j] == 0) {
                            array[i + 1][j] = array[i][j];
                            array[i][j] = 0;
                        }
                    }
                }
                k--;
            }
        } else if (direction == 'd'||direction=='D') {
            while (k > 0) {
                for (int i = 0; i < array.length; i++) {
                    for (int j = 2; j >= 0; j--) {
                        if (array[i][j] != 0 && array[i][j + 1] == 0) {
                            array[i][j + 1] = array[i][j];
                            array[i][j] = 0;
                        }
                    }
                }
                k--;
            }
        }
    }

    public static void combine(char direction, int[][]array){
        if (direction=='w'||direction=='W'){
            for(int i=0; i<3; i++){
                for(int j=0; j<array[i].length; j++){
                    if (array[i][j]==array[i+1][j]){
                        array[i][j]*=2;
                        array[i+1][j]=0;
                    }
                }
            }
        }
        else if (direction=='a'||direction=='A'){
            for(int j=0; j<3; j++){
                for(int i=0; i<array.length; i++){
                        if (array[i][j]==array[i][j+1]){
                        array[i][j]*=2;
                        array[i][j+1]=0;
                    }
                }
            }
        }
        else if (direction=='s'||direction=='S'){
            for(int i=3; i>1; i--){
                for(int j=0; j<array[i].length; j++){
                    if (array[i][j]==array[i-1][j]){
                        array[i][j]*=2;
                        array[i-1][j]=0;
                        //print(array);//For Debugging
                    }
                }
            }
        }
        else if (direction=='d'||direction=='D'){
            for(int j=3; j>1; j--){
                for(int i=0; i<array.length; i++){
                    if (array[i][j]==array[i][j-1]){
                        array[i][j]*=2;
                        array[i][j-1]=0;
                    }
                }
            }
        }
    }
    public static void checkWin(int[][]array){
        for (int i=0; i<array.length; i++){
            for (int j=0; j<array[i].length; j++){
                if (array[i][j]==2048){
                    win=true;
                }
            }
        }
    }
}

