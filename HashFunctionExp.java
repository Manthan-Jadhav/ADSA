import java.util.Scanner;

public class HashFunctionExp {
    private int[] table;
    private int capacity;

    public HashFunctionExp(int capacity) {
        this.capacity = capacity;
        this.table = new int[capacity];
        for (int i = 0; i < capacity; i++) table[i] = -1; // -1 denotes empty slot
    }

    // Hash function: Division method
    private int hash(int key) {
        return (key % capacity + capacity) % capacity;
    }

    // Linear probing for collision resolution
    public void insert(int key) {
        int index = hash(key);
        int start = index;
        while (table[index] != -1) {
            index = (index + 1) % capacity;
            if (index == start) {
                System.out.println("Hash Table is full, cannot insert " + key);
                return;
            }
        }
        table[index] = key;
        System.out.println("Inserted " + key + " at index " + index);
    }

    public void display() {
        System.out.println("\nHash Table Contents:");
        for (int i = 0; i < capacity; i++) {
            System.out.println("Index " + i + ": " + (table[i] == -1 ? "Empty" : table[i]));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size of hash table: ");
        int size = sc.nextInt();
        HashFunctionExp ht = new HashFunctionExp(size);

        System.out.print("Enter number of keys to insert: ");
        int k = sc.nextInt();
        System.out.println("Enter " + k + " integer keys:");
        for (int i = 0; i < k; i++) {
            ht.insert(sc.nextInt());
        }

        ht.display();
        sc.close();
    }
}