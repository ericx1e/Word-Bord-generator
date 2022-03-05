import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class main {
    public static ArrayList<String> dict;
    public static final int BOARD_SIZE = 5;
    public static final int TOTAL_MOVES = 20;
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
            System.out.println(i);

//            for(int r = 0; r < BOARD_SIZE; r++) {
//                System.out.println(String.valueOf(board[r]));
//                out.println(String.valueOf(board[r]));
//            }
//            System.out.println();


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
            String s = String.valueOf(board[r]);
            String rev = new StringBuilder(s).reverse().toString();
            if(dict.contains(s) || dict.contains(rev)) {
                return false;
            }
        }
        for(int c = 0; c < BOARD_SIZE; c++) {
            StringBuilder sb = new StringBuilder();
            String s = "";
            String rev = "";
            for(int r = 0; r < BOARD_SIZE; r++) {
                sb.append(board[r][c]);
            }
            s = sb.toString();
            rev = sb.reverse().toString();
            if(dict.contains(s) || dict.contains(rev)) {
                return false;
            }
        }

        return Solver.solveBoard(board) >= 35;
    }
}
