import java.util.*;

public class Kruskal {


    public static List<Connections> minCost(int n, List<Connections> connection){

        List<Connections> results = new ArrayList<>();
        PriorityQueue<Connections> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        pq.addAll(connection);
        UnionFind unionFind = new UnionFind(n);
        Map<String, Integer> map = new HashMap<>();
        int count = 0;

        while(!pq.isEmpty()){
            Connections node = pq.poll();
            if(!map.containsKey(node.u)){
                map.put(node.u, count++);
            }
            if(!map.containsKey(node.v)){
                map.put(node.v, count++);
            }
            int u = map.get(node.u);
            int v = map.get(node.v);

            if(unionFind.connected(u,v)){

                continue;
            }
            unionFind.union(map.get(node.u), map.get(node.v));
            results.add(node);
        }
        return results;
    }

    public static void main(String[] args) {
        int num = 3;
        List<Connections> store = new ArrayList<>();
        store.add(new Connections("A", "B", 1));
        store.add(new Connections("B", "C", 4));
        store.add(new Connections("A", "C", 5));
//        store.add(new Connections("D", "E", 5));
//        store.add(new Connections("C", "E", 1));
        //store.add(new Connections("B", "C", 5));
        //store.add(new Connections("C", "D", 6));

        System.out.println(Kruskal.minCost(num, store));
    }
}

class Connections{
    String u, v;
    int cost;
    public Connections(String u, String v, int cost){
        this.u = u;
        this.v = v;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "[ " + v + " " + u + " " + cost + " ]";
    }
}
