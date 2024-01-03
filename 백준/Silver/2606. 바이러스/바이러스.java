import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Main {
    private static List<List<Integer>> adj;
    private static boolean[] visited;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        adj = new ArrayList<>(N+1);
        for(int i=0; i<N+1; i++){
            adj.add(new ArrayList<>());
        }
        visited = new boolean[N+1];

        for(int i=0; i<M; i++){
            String[] input = br.readLine().split(" ");
            int node1 = Integer.parseInt(input[0]);
            int node2 = Integer.parseInt(input[1]);

            adj.get(node1).add(node2);
            adj.get(node2).add(node1);
        }

        visited[1]=true;
        System.out.println(dfs(1, 0));
    }
    private static int dfs(int start, int cnt){
        for(int node : adj.get(start)){
            if(visited[node]){
                continue;
            }
            visited[node]=true;
            cnt++;

            cnt+=dfs(node, 0);
        }
        return cnt;
    }
}