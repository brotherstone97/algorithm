import java.util.*;
import java.io.*;


public class Main {
    private static List<List<Node>> adj;
    private static int[] minCost;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        adj = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            adj.add(new ArrayList<>());
        }

        minCost = new int[N + 1];
        Arrays.fill(minCost, Integer.MAX_VALUE);

        visited = new boolean[N + 1];

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adj.get(start).add(new Node(end, weight));
        }
        st = new StringTokenizer(br.readLine());
        int departure = Integer.parseInt(st.nextToken());
        int destination = Integer.parseInt(st.nextToken());

        minCost[departure] = 0;

        dijkstra(departure, destination);

        System.out.println(minCost[destination]);
    }

    private static void dijkstra(int departure, int destination) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(departure, 0));

        while (!pq.isEmpty()) {
            Node polled = pq.poll();
            int currentNode = polled.end;

            if (!visited[currentNode]) {
                visited[polled.end] = true;

                for (Node node : adj.get(currentNode)) {
                    if (!visited[node.end] && minCost[node.end] > minCost[currentNode] + node.weight) {
                        minCost[node.end] = minCost[currentNode] + node.weight;
                        pq.offer(new Node(node.end, minCost[node.end]));
                    }
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int end;
        int weight;

        Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
}