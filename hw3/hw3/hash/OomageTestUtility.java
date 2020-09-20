package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        double[] bucketNum = new double[M];
        int N = oomages.size();
        double thresholdLow = N/50;
        double thresholdHigh = N / 2.5;
        for (Oomage o : oomages) {
            int code = (o.hashCode() & 0x7FFFFFFF) % M;
            bucketNum[code]++;
        }
        for (int i = 0; i < M; i++) {
            if (bucketNum[i] >= thresholdHigh || bucketNum[i] <= thresholdLow) {
                return false;
            }
        }
        return true;
    }
}
