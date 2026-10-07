import java.util.Scanner;

public class DijkstraShortestPath {

    private static int minDistance(int[] dist, boolean[] sptSet, int V) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < V; v++) {
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    public static void dijkstra(int[][] graph, int src, int V) {
        int[] dist = new int[V];
        boolean[] sptSet = new boolean[V];

        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        dist[src] = 0;

        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, sptSet, V);
            if (u == -1) break;

            sptSet[u] = true;

            for (int v = 0; v < V; v++) {
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE
                        && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        System.out.println("\n-------------------------------------------");
        System.out.println("Shortest Distances from Source Vertex: " + src);
        System.out.println("Vertex \t Shortest Distance");
        for (int i = 0; i < V; i++) {
            System.out.println(i + " \t " + (dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]));
        }
        System.out.println("-------------------------------------------");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        int[][] graph = new int[V][V];
        System.out.println("Enter adjacency matrix (" + V + "x" + V + ") row by row (0 if no path):");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        System.out.print("Enter source vertex (0 to " + (V - 1) + "): ");
        int src = sc.nextInt();

        dijkstra(graph, src, V);

        sc.close();
    }
}