import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class main {
    static ArrayList<String> dict;
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("words.txt"));
        dict = new ArrayList<>();
        while(in.hasNext()) {
            dict.add(in.next());
        }

        PrintWriter out = new PrintWriter(new File("boards.txt"));

        for(int i = 0; i < 365 * 2; i++) {
            char[][] board = new char[5][5];
            for(int j = 0; j < 5; j++) {
                int randI = (int)(Math.random() * dict.size());
                board[j] = dict.get(randI).toCharArray();
            }
            while(!isValid(board)) {
                for(int j = 0; j < 5; j++) {
                    int randI = (int)(Math.random() * dict.size());
                    board[j] = shuffle(dict.get(randI)).toCharArray();
                }
            }
            for(int r = 0; r < 5; r++) {
                out.println(String.valueOf(board[r]));
            }
        }

        out.close();
    }

    static String shuffle(String s) {
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

    static boolean isValid(char[][] board) {
        for(int r = 0; r < 5; r++) {
            String s = "";
            String rev = "";
            for(int c = 0; c < 5; c++) {
                s += board[r][c];
                rev += board[r][4-c];
            }
            if(dict.contains(s) || dict.contains(rev)) {
                return false;
            }
        }
        for(int c = 0; c < 5; c++) {
            String s = "";
            String rev = "";
            for(int r = 0; r < 5; r++) {
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
