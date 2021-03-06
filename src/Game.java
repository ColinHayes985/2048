import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Game {
    static boolean win=false;
    static boolean lose=false;
    static int score=0;
    static int turns=0;
    static int[][] oldGrid=new int[4][4];

    //Runs the game
    public static void main(String[] args){
        int [][] grid = new int[4][4];
        placeTile(grid);
        placeTile(grid);
        Scanner s = new Scanner(System.in);
        //TODO:Turn it into a GUI?????????
        while (win==false&&lose==false) {
            int dir=print(grid);
            setOldGrid(grid);
            char direction =' '; //s.next().charAt(0);
            move(direction, dir, grid);
            combine(direction, dir, grid);
            move(direction, dir, grid);
            if (checkMove(grid) == true) {
                placeTile(grid);
            }
            checkWin(grid);
            checkLose(grid);
            turns++;
        }

    }

    //Prints the array for the user to see
    private static int print(int[][] array) {
        String printableBoard="";
        for (int i = 0; i < 4; i++) {
            //System.out.println("");
            printableBoard+='\n';
            printableBoard+='\n';
            for (int j = 0; j < 4; j++) {
                //System.out.format("%04d ", array[i][j]);
                printableBoard+=String.format("%04d  ", array[i][j]);
            }
            //System.out.println("");
        }
        printableBoard+="\nScore:"+score;
        String[] options={"UP", "DOWN", "LEFT", "RIGHT"};
        int dir = JOptionPane.showOptionDialog(null, printableBoard, "2048",0,0,null,options, null);
        System.out.println("Score:" + score);
        return dir;
    }

    //Places a 2 or 4 in a randomly selected empty spot in the array
    private static void placeTile(int[][] array) {
        Random rand = new Random();
        int i = rand.nextInt(4);
        int j = rand.nextInt(4);
        while (array[i][j] != 0) {
            i = rand.nextInt(4);
            j = rand.nextInt(4);
        }
        int num = rand.nextInt(10);
        if (num == 0) {
            array[i][j] = 4;
        }
        else{
            array[i][j] = 2;
        }

    }

    //Shifts array elements in desired direction
    private static void move(char direction, int dir, int[][] array) {
        int k = 3;
        if (dir==0) {
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
        } else if (dir==2) {
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
        } else if (dir==1) {
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
        } else if (dir==3) {
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
    private static void combine(char direction, int dir, int[][] array){
        if (dir==0){
            for (int i = 0; i < 3; i++){
                for (int j = 0; j < array[i].length; j++){
                    if (array[i][j] == array[i + 1][j]){
                        array[i][j] *= 2;
                        array[i + 1][j] = 0;
                        score += array[i][j];
                    }
                }
            }
        }
        else if (dir==2){
            for (int j = 0; j < 3; j++){
                for (int i = 0; i < array.length; i++){
                        if (array[i][j]==array[i][j+1]){
                        array[i][j]*=2;
                        array[i][j+1]=0;
                        score += array[i][j];
                    }
                }
            }
        }
        else if (dir==1){
            for (int i = 3; i > 0; i--){
                for (int j = 0; j < array[i].length; j++){
                    if (array[i][j] == array[i - 1][j]){
                        array[i][j] *= 2;
                        array[i-1][j] = 0;
                        score += array[i][j];
                    }
                }
            }
        }
        else if (dir==3){
            for (int j = 3; j > 0; j--){
                for (int i = 0; i < array.length; i++){
                    if (array[i][j] == array[i][j - 1]){
                        array[i][j] *= 2;
                        array[i][j - 1] = 0;
                        score += array[i][j];
                    }
                }
            }
        }
    }

    //Loops through array to check for a 2048
    private static void checkWin(int[][] array){
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array[i].length; j++){
                if (array[i][j] == 2048){
                    win = true;
                    System.out.println("Congratulations! You won with a score of " + score + " in " + turns + " turns!");
                }
            }
        }
    }

    //Loops through array to see if the board is full-If board is full, it calls potentialMoves
    private static void checkLose(int[][] array){
        int emptySpaces = 0;
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array[i].length; j++){
                if (array[i][j] == 0){
                    emptySpaces++;
                }
            }
        }
        if (emptySpaces == 0){
            if (potentialMoves(array) == false){
                lose = true;
                System.out.println("You Lost\nScore: " + score);
            }
        }
    }

    //Loops through array(when full) to see if there are any moves that can be made
    private static boolean potentialMoves(int[][] array){
        for (int i = 0; i < array.length; i++){
            for (int j=0; j < array[i].length; j++) {
                if (i > 0){
                    if (array[i][j] == array[i - 1][j]){
                        return true;
                    }
                }
                if (j > 0){
                    if (array[i][j] == array[i][j - 1]){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //Loops through array to make sure a valid(grid has changed as a result the move) move has been made before placing tile.
    private static boolean checkMove(int[][] array){
        int similarSpots = 0;
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array.length; j++){
                if (oldGrid[i][j] == array[i][j]){
                    similarSpots++;
                }
            }
        }
        if (similarSpots == 16){
                    return false;
        }
        return true;
    }

    //Sets the oldGrid array equal to the current array so it can be compared next turn to ensure a valid move is made
    private static void setOldGrid(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                oldGrid[i][j] = array[i][j];
            }
        }
    }
}

