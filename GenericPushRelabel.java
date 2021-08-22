import java.util.ArrayList;
import java.util.List;

/**
 * The local class represents the edge of the network.
 */
class Edge {
    protected int flow;
    protected int capacity;
    protected int u;
    protected int v;

    public Edge(int flow, int capacity, int u, int v) {
        this.flow = flow;
        this.capacity = capacity;
        this.u = u;
        this.v = v;
    }
}

/**
 * The local class represents the vertex of the network.
 */
class Vertex {
    protected int h;
    protected int vertexFlow;

    public Vertex(int h, int vertexFlow) {
        this.h = h;
        this.vertexFlow = vertexFlow;
    }
}

/**
 * This class implements the generic push relable algorithm of CLRS book.
 */
class GenericPushRelabel {
    private int V; // Number of vertices
    private List<Vertex> vertices;
    private List<Edge> edges;


    /**
     * Constructor to initialize the push relabel object with given number of vertices.
     * @param V the number of vertices
     */
    public GenericPushRelabel(int V) {
        this.V = V;
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();

        // all vertices are initialized with 0 height
        // and 0 excess flow
        for (int i = 0; i < V; i++)
            vertices.add(new Vertex(0, 0));
    }

    /**
     * Add edge to the network.
     * @param u the start vertex
     * @param v the end vertex
     * @param capacity the capacity of the edge
     */
    public void addUV(int u, int v, int capacity) {
        // flow is initialized with 0 for all edge
        edges.add(new Edge(0, capacity, u, v));
    }

    /**
     * Preflow the network.
     * @param s the source.
     */
    private void preflow(int s) {
        // Make height of source Vertex equal to number  of vertices.
        // Height of other vertices along with sink is 0.
        vertices.get(s).h = vertices.size();

        for (int i = 0; i < edges.size(); i++) {
            // If current edge goes from source
            if (edges.get(i).u == s) {
                // Flow is equal to capacity
                edges.get(i).flow = edges.get(i).capacity;

                // Initialize excess flow for adjacent v
                vertices.get(edges.get(i).v).vertexFlow += edges.get(i).flow;

                // Adding back edge from v to s in residual Graph with capacity equal to 0
                edges.add(new Edge(-edges.get(i).flow, 0, edges.get(i).v, s));
            }
        }
    }

    /**
     * The look for the overflow vertices.
     * @param vertices the vertices list
     * @return return the overflowing vertex
     */
   private int overFlowVertex(List<Vertex> vertices) {
        for (int i = 1; i < vertices.size() - 1; i++)
            if (vertices.get(i).vertexFlow > 0)
                return i;

        // -1 if no overflowing Vertex
        return -1;
    }

    /**
     * Update the reverse flow edge for residual graph.
     * @param i the index of the flow
     * @param flow the flow
     */
    private  void updateReverseEdgeFlow(int i, int flow) {
        int u = edges.get(i).v, v = edges.get(i).u;

        for (int j = 0; j < edges.size(); j++) {
            if (edges.get(i).v == v && edges.get(i).u == u) {
                edges.get(i).flow -= flow;
                return;
            }
        }

        // adding reverse Edge in residual GenericPushRelabel
        Edge e = new Edge(0, flow, u, v);
        edges.add(e);
    }

    /**
     * Push on u is applied when u is an overflowing vertex.
     * @param u the overflowing vertex
     * @return the true if push applicable
     */
    public boolean push(int u) {
        // Traverse through all edges to find an adjacent of u
        // to which flow can be pushed
        for (int i = 0; i < edges.size(); i++) {
            // Checks u of current edge is same as given
            // overflowing vertex
            if (edges.get(i).u == u) {
                // if flow is equal to capacity then no push
                // is possible
                if (edges.get(i).flow == edges.get(i).capacity)
                    continue;

                // Push is only possible if height of adjacent
                // is smaller than height of overflowing vertex
                if (vertices.get(u).h > vertices.get(edges.get(i).v).h) {
                    // Flow to be pushed is equal to minimum of
                    // remaining flow on edge and excess flow.
                    int flow = Math.min(edges.get(i).capacity - edges.get(i).flow,
                            vertices.get(u).vertexFlow);

                    // Reduce excess flow for overflowing vertex
                    vertices.get(u).vertexFlow -= flow;

                    // Increase excess flow for adjacent
                    vertices.get(edges.get(i).v).vertexFlow += flow;

                    // Add residual flow (With capacity 0 and negative flow)
                    edges.get(i).flow += flow;

                    updateReverseEdgeFlow(i, flow);

                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Relabel the network if the relabeling is applicable.
     * @param u the u vertex
     */
    public  void relabel(int u) {
        // Initialize minimum height of an adjacent
        int mh = Integer.MAX_VALUE;

        // Find the adjacent with minimum height
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).u == u) {
                // DO not relabel the if the flow of u and v are equal
                if (edges.get(i).flow == edges.get(i).capacity)
                    continue;

                // Update minimum height
                if (vertices.get(edges.get(i).v).h < mh) {
                    mh = vertices.get(edges.get(i).v).h;

                    // updating height of u
                    vertices.get(u).h = mh + 1;
                }
            }
        }
    }

    /**
     * The method to get the maximum flow of the graph.
     * @param s the source
     * @param t the sink
     * @return the maximum flow of the graph
     */
    public int getMaxFlow(int s, int t) {
        preflow(s);

        // Iterate until none of the Vertex overflows.
        while (overFlowVertex(vertices) != -1) {
            int u = overFlowVertex(vertices);
            if (!push(u)){
                relabel(u);
            }

        }

        // returns last Vertex, whose flow will be final maximum flow.
        return vertices.get(vertices.size()-1).vertexFlow;
    }

    public static void main(String[] arr){
        GenericPushRelabel pushRelabel = new GenericPushRelabel(6);
        pushRelabel.addUV(0, 1, 16);
        pushRelabel.addUV(0, 2, 13);
        //pushRelabel.addUV(1, 2, 10);
        pushRelabel.addUV(2, 1, 4);
        pushRelabel.addUV(1, 3, 12);
        pushRelabel.addUV(2, 4, 14);
        pushRelabel.addUV(3, 2, 9);
        pushRelabel.addUV(3, 5, 20);
        //pushRelabel.addUV(4, 3, 7);
        pushRelabel.addUV(4, 5, 4);

        // Initialize source and sink
        int s = 0, t = 5;

        System.out.println("Max flow: "+  pushRelabel.getMaxFlow(s, t));

    }
}