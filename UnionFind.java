import java.util.Arrays;

public class UnionFind {
    private final int[] connections;
    private final int[] height;
    private final int V;
    private int n;

    public UnionFind(int V) {
        this.V = V;
        connections = new int[V];
        for (int i = 0; i < V; i++){
            connections[i] = i;
        }
        height = new int[V];
        for (int i = 0; i < V; i++){
            height[i] = 1;
        }
        n = V;
    }

    public int count(){
        return n;
    }

    public int find(int u){
        if(u< 0 || connections.length <= u){
            return  -1;
        }
        while (connections[u] != u){
            u = connections[u];

        }
        return u;
    }

    public boolean connected(int u, int v){
        if(u< 0 || connections.length <= u){
            return  false;
        }
        if(v< 0 || connections.length <= v){
            return  false;
        }
        return  find(u) == find(v);

    }

    public void union(int u, int v){
        int i = find(u);
        int j = find(v);
        if (i == j) return;

        if(height[i] < height[j] ){
            connections[i] = j;
            height[j] += height[i];
        }
        else{
            connections[j] = i;
            height[i] += height[j];
        }
        n--;
    }

    // Drivers and debugging.
    public static void main(String[] args) {
        UnionFind uf = new UnionFind(3);
        uf.union(0,1);
        uf.union(1,2);

        System.out.println(uf.connected(0,2));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UnionFind{");
        sb.append("connections=").append(Arrays.toString(connections));
        sb.append(", height=").append(Arrays.toString(height));
        sb.append(", V=").append(V);
        sb.append(", n=").append(n);
        sb.append('}');
        return sb.toString();
    }
}
