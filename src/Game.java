import java.util.Random;
import java.util.Scanner;

public class Game {
    static boolean win=false;
    static boolean lose=false;
    static int score=0;
    static int[][] oldGrid=new int[4][4];
    //Runs the game
    public static void main(String[] args){
        int [][] grid = new int[4][4];
        placeTile(grid);
        placeTile(grid);
        Scanner s = new Scanner(System.in);
        //int [][]test={{1,2,3,4},{5,6,7,4},{9,10,11,12},{13,14,15,16}};//Testing purposes
        //checkLose(test);//Testing purposes
        //TODO:Win Condition:2048-DONE
        //TODO:Lose Condition:Board filled AND No more moves-DONE
        //TODO:Add error handling so if nothing moves(ex:move right when all tiles are in right-most position) no tile is added
        //TODO:Turn it into a GUI?????????
        while (win==false&&lose==false) {
            print(grid);
            setOldGrid(grid);
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
            if (checkMove(grid)==true) {
                placeTile(grid);
            }
            //System.out.println("Place:\n");//For Debugging
            checkWin(grid);
            checkLose(grid);
        }

    }

    //Prints the array for the user to see
    public static void print(int[][] array) {
        for (int i = 0; i < 4; i++) {
            System.out.println("");
            for (int j = 0; j < 4; j++) {
                System.out.format("%04d ", array[i][j]);
            }
            System.out.println("");
        }
        System.out.println("Score:"+score);
    }

    //Places a 2 or 4 in a randomly selected empty spot in the array
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

    //Shifts array elements in desired direction
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

    //Loops through array to combined like elements next to each other in direction input
    public static void combine(char direction, int[][]array){
        if (direction=='w'||direction=='W'){
            for(int i=0; i<3; i++){
                for(int j=0; j<array[i].length; j++){
                    if (array[i][j]==array[i+1][j]){
                        array[i][j]*=2;
                        array[i+1][j]=0;
                        score+=array[i][j];
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
                        score+=array[i][j];
                    }
                }
            }
        }
        else if (direction=='s'||direction=='S'){
            for(int i=3; i>0; i--){
                for(int j=0; j<array[i].length; j++){
                    if (array[i][j]==array[i-1][j]){
                        array[i][j]*=2;
                        array[i-1][j]=0;
                        score+=array[i][j];
                        //print(array);//For Debugging
                    }
                }
            }
        }
        else if (direction=='d'||direction=='D'){
            for(int j=3; j>0; j--){
                for(int i=0; i<array.length; i++){
                    if (array[i][j]==array[i][j-1]){
                        array[i][j]*=2;
                        array[i][j-1]=0;
                        score+=array[i][j];
                    }
                }
            }
        }
    }

    //Loops through array to check for a 2048
    public static void checkWin(int[][]array){
        for (int i=0; i<array.length; i++){
            for (int j=0; j<array[i].length; j++){
                if (array[i][j]==2048){
                    win=true;
                    System.out.println("Congratulations! You won with a score of "+score);
                }
            }
        }
    }

    //Loops through array to see if the board is full-If board is full, it calls potentialMoves
    public static void checkLose(int[][]array){
        int emptySpaces=0;
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array[i].length;j++){
                if(array[i][j]==0){
                    emptySpaces++;
                }
            }
        }
        if (emptySpaces==0){
            if(potentialMoves(array)==false){
                lose=true;
                System.out.println("You Lost\nScore: "+score);
            }
        }
    }

    //Loops through array(when full) to see if there are any moves that can be made
    public static boolean potentialMoves(int[][]array){
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array[i].length;j++) {
                if (i>0){
                    if (array[i][j]==array[i-1][j]){
                        return true;
                    }
                }
                if (j>0){
                    if (array[i][j]==array[i][j-1]){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //Loops through array to make sure a valid(grid has changed as a result the move) move has been made before placing tile.
    public static boolean checkMove(int[][] array){
        int similarSpots=0;
        for (int i=0; i<array.length;i++){
            for(int j=0; j<array.length;j++){
                if (oldGrid[i][j]==array[i][j]){
                    similarSpots++;
                }
            }
        }
        if(similarSpots==16){
                    return false;
        }
        return true;
    }
    public static void setOldGrid(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                oldGrid[i][j] = array[i][j];
            }
        }
    }
}

