import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;

    static int find(int u) {
        if(parent[u] == u) return u;
        return parent[u] = find(parent[u]);
    }

    static boolean union(int u, int v) {
        u = find(u);
        v = find(v);
        if(u == v) return false;
        parent[u] = v;
        return true;
    }

    static class Edge {
        int from;
        int to;
        int weight;
        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numOfNodes = Integer.parseInt(st.nextToken());
        parent = new int[numOfNodes+1];
        for(int idx = 1; idx <= numOfNodes; idx++) {
            parent[idx] = idx;
        }
        int numOfEdges = Integer.parseInt(st.nextToken());
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        for(int idx = 0; idx < numOfEdges; idx++) {
            st = new StringTokenizer(br.readLine());
            pq.offer(new Edge(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())));
        }
        int cnt = 0;
        int sum = 0;
        while(cnt != numOfNodes-1) {
            Edge edge = pq.poll();
            if(union(edge.from, edge.to)) {
                sum += edge.weight;
                cnt++;
            }
        }
        System.out.println(sum);
    }
}