import java.util.Scanner;

public class NQueensBacktracking {
    private static int solutionCount = 0;

    // Check if a queen can be safely placed at board[row][col]
    private static boolean isSafe(int[][] board, int row, int col, int N) {
        // Check this row on left side
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1)
                return false;
        }

        // Check upper diagonal on left side
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1)
                return false;
        }

        // Check lower diagonal on left side
        for (int i = row, j = col; j >= 0 && i < N; i++, j--) {
            if (board[i][j] == 1)
                return false;
        }

        return true;
    }

    // Print chessboard configuration
    private static void printBoard(int[][] board, int N) {
        solutionCount++;
        System.out.println("\n--- Solution #" + solutionCount + " ---");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1) {
                    System.out.print(" Q ");
                } else {
                    System.out.print(" . ");
                }
            }
            System.out.println();
        }
    }

    // Solve N-Queens using Backtracking
    private static void solveNQueens(int[][] board, int col, int N) {
        // Base condition: All queens are placed successfully
        if (col >= N) {
            printBoard(board, N);
            return;
        }

        // Try placing queen in all rows of column 'col'
        for (int i = 0; i < N; i++) {
            if (isSafe(board, i, col, N)) {
                board[i][col] = 1; // Place queen

                // Recur to place the rest of the queens
                solveNQueens(board, col + 1, N);

                // Backtrack: Remove queen
                board[i][col] = 0;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter chessboard dimension N (e.g., 4): ");
        int N = sc.nextInt();

        int[][] board = new int[N][N];
        solutionCount = 0;

        solveNQueens(board, 0, N);

        if (solutionCount == 0) {
            System.out.println("\nNo solution exists for N = " + N);
        } else {
            System.out.println("\nTotal valid configurations found: " + solutionCount);
        }

        sc.close();
    }
}