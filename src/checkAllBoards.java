import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class checkAllBoards {
    private static final int BOARD_SIZE = 4;
    private static final ArrayList<String> dict = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        Scanner dictIn = new Scanner(new File("words" + BOARD_SIZE + ".txt"));
        while(dictIn.hasNext()) {
            dict.add(dictIn.next());
        }
        dictIn.close();

        Scanner boardsIn = new Scanner(new File("boards" + BOARD_SIZE + ".txt"));
        while(boardsIn.hasNext()) {
            char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
            for(int i = 0; i < BOARD_SIZE; i++) {
                board[i] = boardsIn.next().toCharArray();
            }
            if(!isValid(board)) {
                for(int r = 0; r < BOARD_SIZE; r++) {
                    System.out.println(Arrays.toString(board[r]));
                }
            }
        }
    }
    private static boolean isValid(char[][] board) {
        for(int r = 0; r < BOARD_SIZE; r++) {
            String s = "";
            String rev = "";
            for(int c = 0; c < BOARD_SIZE; c++) {
                s += board[r][c];
                rev += board[r][BOARD_SIZE-1-c];
            }
            if(dict.contains(s) || dict.contains(rev)) {
                return false;
            }
        }
        for(int c = 0; c < BOARD_SIZE; c++) {
            String s = "";
            String rev = "";
            for(int r = 0; r < BOARD_SIZE; r++) {
                s += board[r][c];
                rev += board[BOARD_SIZE-1-r][c];
            }
            if(dict.contains(s) || dict.contains(rev)) {
                return false;
            }
        }
        return true;
    }
}
