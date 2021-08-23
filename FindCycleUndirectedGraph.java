import java.util.HashSet;

public class FindCycleUndirectedGraph {
    public static void main(String[] args) {
        UnionFind uf = new UnionFind(3);
        int[][] arr = new int[][]{
                {0, 1, 2}, {1, 0, 1}, {1, 1, 0}
        };
        HashSet<String> visitedEdges = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 0) continue;
                if (uf.connected(i, j) && !visitedEdges.contains(j + "" + i)) {
                    System.out.println(0);
                    return;
                } else if (!visitedEdges.contains(j + "" + i) &&
                        !visitedEdges.contains(i + "" + j)
                ) {
                    visitedEdges.add(i + "" + j);
                    uf.union(i, j);
                }
            }
        }
        System.out.println(1);
    }
}
