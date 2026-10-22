import java.util.Arrays;
import java.util.Scanner;

public class HamiltonianCycle {
    private int V;
    private int[] path;
    private int[][] graph;
    private boolean hasCycle = false;

    public HamiltonianCycle(int[][] graph, int v) {
        this.graph = graph;
        this.V = v;
        this.path = new int[v];
        Arrays.fill(path, -1);
    }

    // Check if the vertex v can be added at index 'pos' in the Hamiltonian Cycle
    private boolean isSafe(int v, int pos) {
        // Check if this vertex is an adjacent vertex of the previously added vertex
        if (graph[path[pos - 1]][v] == 0) {
            return false;
        }

        // Check if the vertex has already been included
        for (int i = 0; i < pos; i++) {
            if (path[i] == v) {
                return false;
            }
        }

        return true;
    }

    // Backtracking function to solve the Hamiltonian Cycle problem
    private void findHamiltonianCycle(int pos) {
        // Base case: If all vertices are included in the path
        if (pos == V) {
            // Check if there is an edge from the last vertex to the first vertex
            if (graph[path[pos - 1]][path[0]] == 1) {
                printCycle();
                hasCycle = true;
            }
            return;
        }

        // Try different vertices as next candidate in Hamiltonian Cycle
        for (int v = 1; v < V; v++) {
            if (isSafe(v, pos)) {
                path[pos] = v;

                // Recur to construct rest of the path
                findHamiltonianCycle(pos + 1);

                // Backtrack: Remove candidate
                path[pos] = -1;
            }
        }
    }

    private void printCycle() {
        System.out.print("Hamiltonian Cycle found: ");
        for (int i = 0; i < V; i++) {
            System.out.print(path[i] + " -> ");
        }
        System.out.println(path[0]);
    }

    public void solve() {
        // Fix starting vertex as 0 to eliminate circular rotation duplicates
        path[0] = 0;

        System.out.println("\n-------------------------------------------");
        findHamiltonianCycle(1);

        if (!hasCycle) {
            System.out.println("No Hamiltonian Cycle exists for the given graph.");
        }
        System.out.println("-------------------------------------------");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        int[][] graph = new int[V][V];
        System.out.println("Enter adjacency matrix (" + V + "x" + V + ") row by row (1 if edge exists, 0 otherwise):");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        HamiltonianCycle hc = new HamiltonianCycle(graph, V);
        hc.solve();

        sc.close();
    }
}