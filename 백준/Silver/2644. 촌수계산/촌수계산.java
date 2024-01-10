import java.io.*;
import java.util.*;

class Main {
    private static List<List<Integer>> adj;
    private static int answer;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        visited = new boolean[N+1];
        adj = new ArrayList<>(N + 1);
        for (int i = 0; i < N + 1; i++) {
            adj.add(new ArrayList<>());
        }

        String[] targets = br.readLine().split(" ");
        int start = Integer.parseInt(targets[0]);
        int dst = Integer.parseInt(targets[1]);

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            String[] input = br.readLine().split(" ");
            int n1 = Integer.parseInt(input[0]);
            int n2 = Integer.parseInt(input[1]);

            adj.get(n1).add(n2);
            adj.get(n2).add(n1);
        }

        visited[start] = true;
        dfs(0, start, dst);

        if (answer == 0) {
            System.out.println(-1);
            return;
        }

        System.out.println(answer);
    }

    private static void dfs(int depth, int start, int dst) {
        if (start == dst) {
            answer = depth;
            return;
        }

        for (int node : adj.get(start)) {
            if (visited[node]) {
                continue;
            }
            visited[node] = true;
            dfs(depth + 1, node, dst);
        }
    }
}