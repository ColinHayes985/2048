import java.util.Random;
import java.util.Scanner;

public class Game {
    static boolean win=false;
    static boolean lose=false;
    public static void main(String[] args){
        int [][] grid = new int[4][4];
        placeTile(grid);
        placeTile(grid);
        print(grid);
        Scanner s = new Scanner(System.in);
        while (win==false) {
            char direction = s.next().charAt(0);
            move(direction, grid);
            combine('w',grid);
            move(direction,grid);
            placeTile(grid);
            print(grid);
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
    public static void move(char direction, int[][] array){
        int k=3;
        if (direction=='w'||direction=='W') {
            while (k > 0) {
                for (int i = 1; i < array.length; i++) {
                    for (int j = 0; j < array[i].length; j++) {
                        //int k=i;
                        //while(k>0) {
                        if (array[i][j] != 0 && array[i - 1][j] == 0) {
                            array[i - 1][j] = array[i][j];
                            array[i][j] = 0;
                        }
                    }
                }
                k--;
            }

        }
    }
    public static void combine(char direction, int[][]array){
        if (direction=='w'){
            for(int i=0; i<3; i++){
                for(int j=0; j<array[i].length; j++){
                    if (array[i][j]==array[i+1][j]){
                        array[i][j]*=2;
                        array[i+1][j]=0;
                    }
                }
            }
        }

    }
}

