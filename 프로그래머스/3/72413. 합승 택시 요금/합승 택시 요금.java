import java.util.*;

class Solution {
    private int[][] adj;
    private int res=Integer.MAX_VALUE;
    private int[] minCost;
    private boolean[] visited;
    
    private int N;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        N=n;
        
        //1. 인접 행렬 생성
        adj = new int[N+1][N+1];
        
        for(int[] fare: fares){
            int n1 = fare[0];
            int n2 = fare[1];
            int weight = fare[2];
            
            adj[n1][n2] = weight;
            adj[n2][n1] = weight;
        }
        
        int[] startS = dijkstra(s);
        int[] startA = dijkstra(a);
        int[] startB = dijkstra(b);
        
        for(int i=1; i<N+1; i++){
            res = Math.min(res, startS[i]+startA[i]+startB[i]);
        }
        
        return res;
    }
    
    private int[] dijkstra(int departure){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(departure, 0));
        
        init(departure);
        
        while(!pq.isEmpty()){
            Node current = pq.poll();
            
            if(visited[current.end]){
                continue;
            }
            visited[current.end]=true;
            
            for(int i=1; i<N+1; i++){
                int nextWeight = adj[current.end][i];
                if(nextWeight == 0){
                    continue;
                }
                if(!visited[i] && minCost[i]> current.weight + nextWeight){
                    minCost[i] = current.weight + nextWeight;
                    pq.offer(new Node(i, minCost[i]));
                }
            }
        }
        return minCost;
    }
    
    private void init(int departure){
        minCost = new int[N+1];
        Arrays.fill(minCost, Integer.MAX_VALUE);
        minCost[departure] = 0;
        
        visited = new boolean[N+1];
    }
    
    class Node implements Comparable<Node>{
        int end;
        int weight;
        
        Node(int end, int weight){
            this.end = end;
            this.weight = weight;
        }
        
        @Override
        public int compareTo(Node o){
            return this.weight - o.weight;
        }
    }
}