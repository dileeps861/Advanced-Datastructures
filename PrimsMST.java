public class PrimsMST {
    private final int V;

    /**
     * Constructor to initialize the Prims mst.
     * @param v the number of vertexes in the graph
     */
    public PrimsMST(int v) {
        V = v;
    }


    /**
     * Find the minimum weight edge of the graph.
     * @param node the node
     * @param connection the edge set if contains the edge
     * @return
     */
    private int findMinimumEdge(int[] node, boolean[] connection) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        int vIndex = 0;
        while (vIndex < V) {
            if (connection[vIndex] == false && node[vIndex] < min) {
                min = node[vIndex];
                minIndex = vIndex;
            }
            vIndex++;
        }

        return minIndex;
    }
    private void printConstructedTree(int[] p, int[][] G) {
        System.out.println("E(u->v) \tW");
        int i = 1;
        while (i < V) {
            System.out.println(p[i] + " -> " + i + "\t" + G[i][p[i]]);
            i++;
        }
    }

    /**
     * Print the prims MST from the provided graph.
     * @param G the graph adjacency matrix
     */
    public void printConstructedTree(int[][] G) {
        int[] p = new int[V];
        int[] nodes = new int[V];
        boolean[] connections = new boolean[V];
        int i = 0;
        while (i < V) {
            nodes[i] = Integer.MAX_VALUE;
            connections[i] = false;
            i++;
        }
        nodes[0] = 0;
        p[0] = -1;
        for (int count = 0; count < V - 1; count++) {
            int u = findMinimumEdge(nodes, connections);
            connections[u] = true;
            for (int v = 0; v < V; v++)
                if (G[u][v] != 0 && connections[v] == false && G[u][v] < nodes[v]) {
                    p[v] = u;
                    nodes[v] = G[u][v];
                }
        }
        printConstructedTree(p, G);
    }
}
