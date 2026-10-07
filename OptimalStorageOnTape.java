import java.util.Arrays;
import java.util.Scanner;

public class OptimalStorageOnTape {
    static class Program implements Comparable<Program> {
        int id;
        int length;

        Program(int id, int length) {
            this.id = id;
            this.length = length;
        }

        @Override
        public int compareTo(Program other) {
            return Integer.compare(this.length, other.length);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter total number of programs: ");
        int n = sc.nextInt();

        Program[] programs = new Program[n];
        System.out.println("Enter the lengths of " + n + " programs (space-separated):");
        for (int i = 0; i < n; i++) {
            programs[i] = new Program(i + 1, sc.nextInt());
        }

        // Greedy strategy: Sort in ascending order of length
        Arrays.sort(programs);

        System.out.println("\n-------------------------------------------");
        System.out.print("Optimal Storage Order: ");
        for (int i = 0; i < n; i++) {
            System.out.print("P" + programs[i].id + (i == n - 1 ? "" : " -> "));
        }
        System.out.println();

        System.out.println("\nRetrieval Time Breakdown:");
        double totalRetrievalTime = 0;
        int cumulativeTime = 0;

        for (int i = 0; i < n; i++) {
            cumulativeTime += programs[i].length;
            totalRetrievalTime += cumulativeTime;
            System.out.println("Time to retrieve P" + programs[i].id + " (length " + programs[i].length + "): " + cumulativeTime);
        }

        double mrt = totalRetrievalTime / n;

        System.out.println("-------------------------------------------");
        System.out.printf("Total Retrieval Time : %.2f\n", totalRetrievalTime);
        System.out.printf("Mean Retrieval Time (MRT): %.2f\n", mrt);
        System.out.println("-------------------------------------------");

        sc.close();
    }
}