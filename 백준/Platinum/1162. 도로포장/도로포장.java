import java.util.*;
import java.io.*;

class Main {
    private static int N;
    private static int M;
    private static int K;
    private static List<List<Edge>> adj;
    private static long[][] cost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        K = Integer.parseInt(input[2]);

        adj = new ArrayList<>(N + 1);
        cost = new long[N + 1][K + 1];
        for (int i = 0; i < N + 1; i++) {
            adj.add(new ArrayList<>());
            Arrays.fill(cost[i], Long.MAX_VALUE);
        }

        for (int i = 0; i < M; i++) {
            String[] temp = br.readLine().split(" ");
            int node1 = Integer.parseInt(temp[0]);
            int node2 = Integer.parseInt(temp[1]);
            int weight = Integer.parseInt(temp[2]);

            adj.get(node1).add(new Edge(node2, weight, 0));
            adj.get(node2).add(new Edge(node1, weight, 0));
        }
        dijkstra();

        System.out.println(calcAnswer());
    }

    private static long calcAnswer() {
       return Arrays.stream(cost[N]).min().getAsLong();
    }

    private static void dijkstra() {
        cost[1][0] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> Long.compare(e1.cost, e2.cost));
        pq.offer(new Edge(1, 0, 0));

        while (!pq.isEmpty()) {
            Edge polled = pq.poll();
            //가지치기
            if (polled.cost > cost[polled.end][polled.cnt]) {
                continue;
            }

            for (Edge current : adj.get(polled.end)) {
                long nextCost = polled.cost + current.cost;
                if (cost[current.end][polled.cnt] > nextCost) {
                    cost[current.end][polled.cnt] = nextCost;
                    pq.offer(new Edge(current.end, nextCost, polled.cnt));
                }
                if (polled.cnt < K && polled.cost < cost[current.end][polled.cnt + 1]) {
                    cost[current.end][polled.cnt + 1] = polled.cost;
                    pq.offer(new Edge(current.end, polled.cost, polled.cnt + 1));
                }
            }
        }
    }

    static class Edge {
        int end;
        long cost;
        int cnt;

        Edge(int end, long cost, int cnt) {
            this.end = end;
            this.cost = cost;
            this.cnt = cnt;
        }
    }
}