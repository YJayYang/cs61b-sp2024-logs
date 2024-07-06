import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class UnionFind {
    // TODO: Instance variables
    private int[]  array;
    private int capacity;

    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        // TODO: YOUR CODE HERE
        array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = -1;
        }
        capacity = N;
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        // TODO: YOUR CODE HERE
        return -parent(find(v));

    }




    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        // TODO: YOUR CODE HERE
        checkRange(v);

        return array[v];
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO: YOUR CODE HERE
        int p = find(v1);
        int q = find(v2);
        return p == q;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        // TODO: YOUR CODE HERE
        checkRange(v);
         int root = v;
        while (parent(root) > 0) {
            root = parent(root);
        }
        while (parent(v) > 0) {
            int parent = parent(v);
            array[v] = root;
            v = parent;
        }
        return root;
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        // TODO: YOUR CODE HERE
        int root1 = find(v1);
        int root2 = find(v2);
        if (root1 == root2) {
            return;
        }
        if (parent(root1) >= parent(root2)) {
            array[root2] += parent(root1);
            array[root1] = root2;
        } else {
            array[root1] += parent(root2);
            array[root2] = root1;
        }
        capacity--;
    }




    private void checkRange(int v) {
        if (v < 0 || v >= array.length) {
            throw new IllegalArgumentException("Cannot find an out of range vertex!");
        }
    }

}
