import java.util.Scanner;

public class Prims_Algorithm {

    private static int findMinKeyVertex(int[] key, boolean[] inMST, int V) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < V; v++) {
            if (!inMST[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    public static void primMST(int[][] graph, int V) {
        int[] parent = new int[V];
        int[] key = new int[V];
        boolean[] inMST = new boolean[V];

        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            inMST[i] = false;
        }

        key[0] = 0;
        parent[0] = -1;

        for (int count = 0; count < V - 1; count++) {
            int u = findMinKeyVertex(key, inMST, V);
            inMST[u] = true;

            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !inMST[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        System.out.println("\n--- Minimum Spanning Tree (MST) ---");
        System.out.println("Edge \tWeight");
        int totalWeight = 0;
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
            totalWeight += graph[i][parent[i]];
        }
        System.out.println("-----------------------------------");
        System.out.println("Total MST Weight: " + totalWeight);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        int[][] graph = new int[V][V];
        System.out.println("Enter the adjacency matrix (" + V + "x" + V + ") row by row (0 if no edge):");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        primMST(graph, V);

        sc.close();
    }
}