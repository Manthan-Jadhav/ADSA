import java.util.*;

public class BFS_Traversal {
    private int V;
    private LinkedList<Integer>[] adj;

    @SuppressWarnings("unchecked")
    public BFS_Traversal(int v) {
        this.V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int u, int v, boolean isDirected) {
        adj[u].add(v);
        if (!isDirected) {
            adj[v].add(u);
        }
    }

    public void bfs(int startVertex) {
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();

        visited[startVertex] = true;
        queue.add(startVertex);

        System.out.print("\nBFS Traversal Order: ");

        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");

            for (int neighbor : adj[current]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter total number of vertices: ");
        int v = sc.nextInt();
        BFS_Traversal graph = new BFS_Traversal(v);

        System.out.print("Enter total number of edges: ");
        int e = sc.nextInt();

        System.out.print("Is the graph directed? (true/false): ");
        boolean isDirected = sc.nextBoolean();

        System.out.println("Enter each edge as: <source> <destination> (0 to " + (v - 1) + "):");
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int dest = sc.nextInt();
            graph.addEdge(u, dest, isDirected);
        }

        System.out.print("Enter start vertex for BFS traversal: ");
        int start = sc.nextInt();

        graph.bfs(start);

        sc.close();
    }
}