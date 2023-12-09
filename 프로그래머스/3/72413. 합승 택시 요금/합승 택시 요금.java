import java.util.PriorityQueue;
import java.util.Arrays;

class Solution {
    private int globalLength;
    private int[][] adj;
    private boolean[] visited;
    private int answer;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        globalLength = n+1;
        adj = new int[globalLength][globalLength];
        visited = new boolean[globalLength];
        
        for(int i=0; i<fares.length; i++){
            int start = fares[i][0];
            int end = fares[i][1];
            int weight = fares[i][2];
            
            adj[start][end] = weight;
            adj[end][start] = weight;
        }
        
        int[] startS = dijkstra(s);
        int[] startA = dijkstra(a);
        int[] startB = dijkstra(b);
        
        answer = startS[a]+startS[b];
        
        for(int i=1; i<globalLength; i++){
            answer = Math.min(answer, startS[i]+startA[i]+startB[i]);
        }
        
        return answer;
    }
    
    private int[] dijkstra(int start){
        int[] minCost = new int[globalLength];
        visited = new boolean[globalLength];
        Arrays.fill(minCost, Integer.MAX_VALUE);
        minCost[start]=0;
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        
        while(!pq.isEmpty()){
            Node curNode = pq.poll();
            int cur = curNode.next;
            int weight = curNode.weight;
            
            if(visited[cur]){
                continue;
            }
            
            visited[cur]=true;
            
            for(int i=1; i<globalLength; i++){
                if(adj[cur][i]==0){
                    continue;
                }
                
                if(minCost[i]>weight+adj[cur][i]){
                    minCost[i] = weight + adj[cur][i];
                    pq.offer(new Node(i, minCost[i]));
                }
            }
        }
        return minCost;
    }
    
    class Node implements Comparable<Node>{
        int next;
        int weight;
        
        Node(int next, int weight){
            this.next = next;
            this.weight = weight;
        }
        
        @Override
        public int compareTo(Node o){
            return this.weight - o.weight;
        }
    }
}