import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    // TODO: Add any necessary instance variables.

    private boolean[][] openSites;   // grid of open sites
    private int n;                   // grid dimension
    private UnionFind uf; // union-find structure
    private int topVirtualSite;      // virtual top site index
    private int bottomVirtualSite;   // virtual bottom site index
    private int openSiteCount;

    /* definition a Percolation  - 1 represent not open and 1 represent open , 0 is not full   2 is full   */
    public Percolation(int N) {
        // TODO: Fill in this constructor.
        if (N <= 0) {
            throw new IllegalArgumentException("Grid dimension must be greater than 0");
        }

        this.n = N;
        openSites = new boolean[n][n];
        uf = new UnionFind(n * n + 2); // include two virtual sites
        topVirtualSite = n * n;                   // index of virtual top site
        bottomVirtualSite = n * n + 1;
        openSiteCount = 0;// index of virtual bottom site

        // connect virtual top site to top row and virtual bottom site to bottom row
        for (int i = 0; i < n; i++) {
            uf.union(topVirtualSite, getIndex(0, i));
            uf.union(bottomVirtualSite, getIndex(n-1, i));
        }
    }

    // if not open . just open already;
    public void open(int row, int col) {
        // TODO: Fill in this method.

        if (isOpen(row, col)) {
            return;
        }else {
            openSites[row][col] = true;
            openSiteCount++;
        }
        if (row > 0 && isOpen(row - 1, col)) {uf.union(getIndex(row, col), getIndex(row - 1, col));}
        if (row < n - 1 && isOpen(row + 1, col)) {uf.union(getIndex(row, col), getIndex(row + 1, col));}
        if (col > 0 && isOpen(row, col - 1)) {uf.union(getIndex(row, col), getIndex(row, col - 1));}
        if (col < n - 1 && isOpen(row, col + 1)) {uf.union(getIndex(row, col), getIndex(row, col + 1));}

    }

    private int getIndex(int row, int col) {
        return row * n + col;
    }
    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        validate(row, col);

        return openSites[row][col];
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        validate(row, col);

        return uf.connected(topVirtualSite, getIndex(row, col));
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.


        return openSiteCount;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        return uf.connected(topVirtualSite, bottomVirtualSite);
        }

    private void validate(int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            throw new IllegalArgumentException("row or col index out of bounds");
        }
    }

}
