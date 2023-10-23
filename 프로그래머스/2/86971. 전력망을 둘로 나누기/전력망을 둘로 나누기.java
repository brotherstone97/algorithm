class Solution {
    private int answer = Integer.MAX_VALUE;
    private boolean[] visited;
    private int[][] adj;
    private int[] cnts={1,1};
    
    public int solution(int n, int[][] wires) {
        visited= new boolean[n+1];
        
        createAdj(n, wires);
        
        for(int[] wire: wires){
            adj[wire[0]][wire[1]] = 0;
            adj[wire[1]][wire[0]] = 0;
            for(int i=1; i<visited.length; i++){
                if(!visited[i]){
                    visited[i]=true;
                    dfs(i, 0);
                    break;
                }
            }
            
            for(int i=1; i<visited.length; i++){
                if(!visited[i]){
                    visited[i]=true;
                    dfs(i,1);
                    break;
                }
            }
            answer = Math.min(answer, Math.abs(cnts[0]-cnts[1]));
            
            //back tracking
            adj[wire[0]][wire[1]] = 1;
            adj[wire[1]][wire[0]] = 1;
            visited=new boolean[n+1];
            cnts=new int[]{2,2};
        }
        
        return answer;
    }
    
    private void dfs(int start, int cntIdx){
        for(int i=1; i<adj.length; i++){
            if(adj[start][i]==1 && !visited[i]){
                visited[i]=true;
                cnts[cntIdx]++;
                dfs(i, cntIdx);
            }
        }
    }
    
    private void createAdj(int n, int[][] wires){
        adj = new int[n+1][n+1];
        
        for(int[] wire:wires){
            adj[wire[0]][wire[1]] = 1;
            adj[wire[1]][wire[0]] = 1;
        }
    }
}