import java.util.Scanner;

public class LCS_DynamicProgramming {

    public static void computeLCS(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        // Build DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Backtrack to reconstruct the LCS string
        StringBuilder lcsBuilder = new StringBuilder();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                lcsBuilder.append(s1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        String lcsString = lcsBuilder.reverse().toString();

        // Display results
        System.out.println("\n-------------------------------------------");
        System.out.println("Length of Longest Common Subsequence: " + dp[m][n]);
        System.out.println("Longest Common Subsequence (LCS)    : " + lcsString);
        System.out.println("-------------------------------------------");

        // Display the DP matrix
        System.out.println("\n--- Dynamic Programming Table ---");
        System.out.print("      ");
        for (int col = 0; col < n; col++) {
            System.out.print(s2.charAt(col) + "  ");
        }
        System.out.println();

        for (int r = 0; r <= m; r++) {
            if (r == 0) System.out.print("   ");
            else System.out.print(s1.charAt(r - 1) + "  ");

            for (int c = 0; c <= n; c++) {
                System.out.printf("%2d ", dp[r][c]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first string : ");
        String s1 = sc.next();

        System.out.print("Enter second string: ");
        String s2 = sc.next();

        computeLCS(s1, s2);

        sc.close();
    }
}