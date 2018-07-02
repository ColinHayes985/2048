import java.util.Random;
import java.util.Scanner;

public class Game {
    public static void main(String[] args){
        int [][] grid = new int[4][4];
        placeTile(grid);
        placeTile(grid);
        print(grid);

    }
    public static void print(int[][] array) {
        for (int i = 0; i < 4; i++) {
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
        int num = rand.nextInt(5);
        if (num == 0) {
            array[i][j]=4;
        }
        else{
            array[i][j]=2;
        }

    }
}

