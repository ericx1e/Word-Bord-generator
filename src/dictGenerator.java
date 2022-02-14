
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class dictGenerator {
    public static void main(String[] args) throws FileNotFoundException {
        final int WORD_LENGTH = 4;
        ArrayList<String> result = new ArrayList<>();
        Scanner in = new Scanner(new File("fullDict.txt"));
        while(in.hasNext()) {
            String s = in.next();
            if(s.length() == WORD_LENGTH) {
                result.add(s);
            }
        }
        System.out.println(result.size());
        in.close();

        PrintWriter out = new PrintWriter(new File("words" + WORD_LENGTH + ".txt"));
        for(String s : result) {
            out.println(s);
        }
        out.close();
    }
}
