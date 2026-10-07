import java.util.*;

public class BST_Traversals {
    static class Node {
        int data;
        Node left, right;
        Node(int val) { data = val; }
    }

    // BST Insertion: Left < Root < Right
    public static Node insert(Node root, int val) {
        if (root == null) return new Node(val);
        if (val < root.data) {
            root.left = insert(root.left, val);
        } else if (val > root.data) {
            root.right = insert(root.right, val);
        }
        return root;
    }

    // Traversals
    public static void inorder(Node root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    public static void preorder(Node root) {
        if (root == null) return;
        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }

    public static void postorder(Node root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data + " ");
    }

    // Tree height calculation
    public static int getHeight(Node root) {
        if (root == null) return 0;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

    // Grid-based recursive branch and node placement
    private static void fillCanvas(char[][] canvas, Node root, int row, int left, int right) {
        if (root == null) return;

        int mid = (left + right) / 2;
        String valStr = String.valueOf(root.data);
        
        // Center the number on the mid position
        int textStart = mid - valStr.length() / 2;
        for (int i = 0; i < valStr.length(); i++) {
            canvas[row][textStart + i] = valStr.charAt(i);
        }

        // Draw left child and connection
        if (root.left != null) {
            int leftMid = (left + mid - 1) / 2;
            canvas[row + 1][mid - 1] = '/';
            for (int col = leftMid + 1; col < mid - 1; col++) {
                canvas[row + 1][col] = '_';
            }
            fillCanvas(canvas, root.left, row + 2, left, mid - 1);
        }

        // Draw right child and connection
        if (root.right != null) {
            int rightMid = (mid + 1 + right) / 2;
            canvas[row + 1][mid + 1] = '\\';
            for (int col = mid + 2; col < rightMid; col++) {
                canvas[row + 1][col] = '_';
            }
            fillCanvas(canvas, root.right, row + 2, mid + 1, right);
        }
    }

    // Renders the aligned tree canvas
    public static void printBST(Node root) {
        if (root == null) {
            System.out.println("Tree is empty!");
            return;
        }

        int height = getHeight(root);
        int rows = height * 2 - 1;
        int cols = (1 << height) * 6; // Dynamic width based on depth

        char[][] canvas = new char[rows][cols];
        for (char[] row : canvas) {
            Arrays.fill(row, ' ');
        }

        fillCanvas(canvas, root, 0, 0, cols - 1);

        for (char[] row : canvas) {
            String line = new String(row).replaceAll("\\s+$", ""); // Trim trailing whitespace
            if (!line.isEmpty()) {
                System.out.println(line);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter values to insert into BST (space-separated on a single line):");
        System.out.println("Example: 50 30 70 20 40 60 80");
        System.out.print("Input: ");

        String line = sc.nextLine().trim();
        String[] values = line.split("\\s+");

        Node root = null;
        for (String v : values) {
            if (!v.isEmpty()) {
                root = insert(root, Integer.parseInt(v));
            }
        }

        System.out.println("\n--- Binary Search Tree Diagram ---");
        printBST(root);

        System.out.print("\nPre-order Traversal  : ");
        preorder(root);
        System.out.print("\nIn-order Traversal   : ");
        inorder(root);
        System.out.print("\nPost-order Traversal : ");
        postorder(root);
        System.out.println();

        sc.close();
    }
}