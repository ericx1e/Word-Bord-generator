import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class main {
    private static ArrayList<String> dict;
    private static final int BOARD_SIZE = 4;
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("words" + BOARD_SIZE + ".txt"));
        dict = new ArrayList<>();
        while(in.hasNext()) {
            dict.add(in.next());
        }

        PrintWriter out = new PrintWriter(new File("boards" + BOARD_SIZE + ".txt"));

        for(int i = 0; i < 365 * 2; i++) { //two years of boards
            char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
            for(int j = 0; j < BOARD_SIZE; j++) {
                int randI = (int)(Math.random() * dict.size());
                board[j] = dict.get(randI).toCharArray();
            }
            while(!isValid(board)) {
                for(int j = 0; j < BOARD_SIZE; j++) {
                    int randI = (int)(Math.random() * dict.size());
                    board[j] = shuffle(dict.get(randI)).toCharArray();
                }
            }
            for(int r = 0; r < BOARD_SIZE; r++) {
                out.println(String.valueOf(board[r]));
            }
        }

        out.close();
    }

    private static String shuffle(String s) {
        List<Character> list = new ArrayList<Character>();
        for(char c : s.toCharArray()) {
            list.add(c);
        }
        Collections.shuffle(list);
        StringBuilder builder = new StringBuilder();
        for(char c : list) {
            builder.append(c);
        }

        return builder.toString();
    }

    private static boolean isValid(char[][] board) {
        for(int r = 0; r < BOARD_SIZE; r++) {
            String s = "";
            String rev = "";
            for(int c = 0; c < BOARD_SIZE; c++) {
                s += board[r][c];
                rev += board[r][4-c];
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
                rev += board[4-r][c];
            }
            if(dict.contains(s) || dict.contains(rev)) {
                return false;
            }
        }
        return true;
    }
}
