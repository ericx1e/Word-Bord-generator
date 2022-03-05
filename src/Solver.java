import java.util.ArrayList;
import java.util.HashSet;

public class Solver {
    public static int solveBoard(char[][] board) {
        ArrayList<Sim> boardsToCheck = new ArrayList<>();
        boardsToCheck.add(new Sim(board));

        int maxScore = 0;
        for (int j = 0; j < main.TOTAL_MOVES; j++) {
            ArrayList<Sim> nextBoardsToCheck = new ArrayList<>();
            //Parallel array
//                ArrayList<Integer> nextBoardsScores = new ArrayList<>();
            for (Sim b : boardsToCheck) {
                for (int i = 0; i < main.BOARD_SIZE * main.BOARD_SIZE * 2; i++) {
                    Move nextMove = new Move((i < main.BOARD_SIZE * main.BOARD_SIZE) ? "r" : "c", (i % (main.BOARD_SIZE * main.BOARD_SIZE)) / main.BOARD_SIZE, i % main.BOARD_SIZE);
                    if (nextMove.n == 0) {
                        continue;
                    }
                    Sim nextBoard = b.moveAndScore(nextMove);

                    int score = nextBoard.totalScore;

//                        if(scoreBoard(nextBoard) > 0) {

                    if(score > maxScore) {
                        maxScore = score;
                        nextBoardsToCheck = new ArrayList<>();
                    }
                    if (score == maxScore) {
                        nextBoardsToCheck.add(nextBoard);
                    }

//                        }

                    //prints out the board
//                        for(int r = 0; r < BOARD_SIZE; r++) {
//                            System.out.println(nextBoard[r]);
//                        }
                }
            }
            boardsToCheck = new ArrayList<>(nextBoardsToCheck);
        }

        return maxScore;
    }

    static HashSet<String> scoreBoard(char[][] board) {
        HashSet<String> result = new HashSet<>();
        for(int r = 0; r < main.BOARD_SIZE; r++) {
            String s = String.valueOf(board[r]);
            String rev = new StringBuilder(s).reverse().toString();
            if(main.dict.contains(s)) {
                result.add(s);
            }
            if(main.dict.contains(rev)) {
                result.add(rev);
            }
        }

        for(int c = 0; c < main.BOARD_SIZE; c++) {
            StringBuilder sb = new StringBuilder();
            for (int r = 0; r < main.BOARD_SIZE; r++) {
                sb.append(board[r][c]);
            }
            String s = sb.toString();
            String rev = sb.reverse().toString();
            if(main.dict.contains(s)) {
                result.add(s);
            }
            if(main.dict.contains(rev)) {
                result.add(rev);
            }
        }
        return result;
    }

    static class Sim {
        char[][] board;
        ArrayList<Move> moves;
        HashSet<String> wordsFound; //easy way to prevent duplicates
        int totalScore;

        public Sim(char[][] board) {
            this.board = board;
            moves = new ArrayList<>();
            wordsFound = new HashSet<>();
        }

        public Sim(char[][] board, ArrayList<Move> moves, HashSet<String> wordsFound) {
            this.board = board;
            this.moves = moves;
            this.wordsFound = wordsFound;
            this.totalScore = wordsFound.size();
        }

        public Sim moveAndScore(Move move) {
            char[][] result = new char[main.BOARD_SIZE][];
            for (int i = 0; i < main.BOARD_SIZE; i++) {
                result[i] = board[i].clone();
            }

            if (move.dir.equals("r")) {
                for (int i = 0; i < main.BOARD_SIZE; i++) {
                    result[move.i][(i + move.n + main.BOARD_SIZE) % main.BOARD_SIZE] = board[move.i][i];
                }
            } else {
                for (int i = 0; i < main.BOARD_SIZE; i++) {
                    result[(i + move.n + main.BOARD_SIZE) % main.BOARD_SIZE][move.i] = board[i][move.i];
                }
            }

            ArrayList<Move> nextMoves = new ArrayList<>(moves);
            nextMoves.add(move);

            HashSet<String> nextWordsFound = new HashSet<>(wordsFound);
            nextWordsFound.addAll(scoreBoard(result));

            return new Sim(result, nextMoves, nextWordsFound);
        }

    }

    static class Move {
        public String dir;
        public int i, n;

        public Move(String dir, int i, int n) {
            this.dir = dir;
            this.i = i;
            this.n = n;
        }

        @Override
        public String toString() {
            return "Move(" +
                    "dir='" + dir + '\'' +
                    ", i=" + i +
                    ", n=" + n +
                    ')';
        }
    }
}
