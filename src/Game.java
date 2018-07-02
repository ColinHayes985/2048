import java.util.Random;
import java.util.Scanner;

public class Game {
    public static void main(String[] args){
        int [][] grid = new int[4][4];
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
}

