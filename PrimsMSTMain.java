public class PrimsMSTMain {
    public static void main(String[] args) {
        PrimsMST primsMST = new PrimsMST(7);
        int[][] G = new int[][]{
                {0, 28, 0, 0, 0, 10, 0},
                {28, 0, 16, 0, 0, 0, 14},
                {0, 16, 0, 12, 0, 0, 0},
                {0, 0, 12, 0, 22, 0, 18},
                {0, 0, 0, 22, 0, 25, 24},
                {10, 0, 0, 0, 25, 0, 0},
                {0, 14, 0, 18, 24, 0, 0}
        };
        primsMST.printConstructedTree(G);
    }
}
