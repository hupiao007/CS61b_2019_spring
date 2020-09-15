package hw2;

import edu.princeton.cs.algs4.StdStats;
import  edu.princeton.cs.introcs.StdRandom;

public class PercolationStats {
    private int T;
    private double[] fractionList;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <+ 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException("invalid N or T");
        }
        this.T = T;
        fractionList = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation x = pf.make(N);
            int[] randomRow = StdRandom.permutation(N);
            int[] randomCol = StdRandom.permutation(N);
            for (int j = 0; j < N * N; j++) {
                x.open(randomRow[j], randomCol[j]);
                if (x.percolates()) {
                    break;
                }
            }
            fractionList[i] = x.numberOfOpenSites() / N / N;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(fractionList);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(fractionList);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        double confLow = mean() - 1.96 * stddev() / Math.sqrt(T);
        return confLow;
    }
    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        double confHigh = mean() + 1.96 * stddev() /Math.sqrt(T);
        return confHigh;
    }
}
