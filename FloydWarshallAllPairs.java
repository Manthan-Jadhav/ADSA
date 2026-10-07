import java.util.Scanner;

public class FloydWarshallAllPairs {
    final static int INF = 99999; // Value representing infinity (no direct edge)

    public static void floydWarshall(int[][] dist, int V) {
        // Step-by-step intermediate vertex updates
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        // Print shortest distance matrix
        System.out.println("\n-------------------------------------------------");
        System.out.println("All-Pairs Shortest Path Distance Matrix (D^" + V + "):");
        System.out.println("-------------------------------------------------");
        
        System.out.print("      ");
        for (int j = 0; j < V; j++) {
            System.out.printf("V%-5d", j);
        }
        System.out.println();

        for (int i = 0; i < V; i++) {
            System.out.printf("V%-5d", i);
            for (int j = 0; j < V; j++) {
                if (dist[i][j] == INF) {
                    System.out.printf("%-6s", "INF");
                } else {
                    System.out.printf("%-6d", dist[i][j]);
                }
            }
            System.out.println();
        }
        System.out.println("-------------------------------------------------");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        int[][] matrix = new int[V][V];
        System.out.println("Enter the adjacency matrix (" + V + "x" + V + ") row by row:");
        System.out.println("(Use 0 for self-loops, and 99999 if no direct edge exists)");

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        floydWarshall(matrix, V);

        sc.close();
    }
}