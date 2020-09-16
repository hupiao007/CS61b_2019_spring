package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int openSites;
    private WeightedQuickUnionUF gridN;
    private int N;
    private int[] colorList;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException("N should be >= 0");
        }
        this.N = N;
        gridN = new WeightedQuickUnionUF(N * N);
        colorList= new int[N * N];
        openSites = 0;
    }

    private void indexOutrange(int row, int col) {
        if (row < 0 || row > N-1 || col < 0 || col > N-1) {
            throw new java.lang.IndexOutOfBoundsException("index should be >=0 and <N");
        }
    }
    private int xyToID(int row, int col) {
        return row * N + col;
    }

    /** Open the site (row, col) if it is not open already.
     * In colorLIst, 0 means not open, and 1 means open, 2 means full.
     * Only change the color number of root. */
    public void open(int row, int col) {
        indexOutrange(row, col);
        if (!isOpen(row, col)) {
            int id = xyToID(row, col);
            if (row == 0) {
                colorList[id] = 2;
            } else {
                colorList[id] = 1;
            }
            if (row > 0 && isOpen(row - 1, col)) {
                gridN.union(id, xyToID(row - 1, col));
            }
            if (col > 0 && isOpen(row, col - 1)) {
                gridN.union(id, xyToID(row, col - 1));
            }
            if (col < N - 1 && isOpen(row, col + 1)) {
                gridN.union(id, xyToID(row, col + 1));
            }
            if (row < N - 1 && isOpen(row + 1, col)) {
                gridN.union(id, xyToID(row + 1, col));
            }
            if (colorList[id] == 2) {
                int rootID = gridN.find(id);
                colorList[rootID] = 2;
            }
            openSites++;
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        indexOutrange(row, col);
        int id = xyToID(row, col);
        return colorList[id] != 0;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        indexOutrange(row, col);
        int id = xyToID(row, col);
        int rootID = gridN.find(id);
        return colorList[rootID] == 2;
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int i = 0; i < N; i++) {
            if (isFull(N - 1, i)) {
                return true;
            }
        }
        return false;
    }
    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args){

    }
}
