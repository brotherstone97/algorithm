//hard

class Solution {
    private boolean[] visited;
    private int maxSheep=1;
    
    public int solution(int[] info, int[][] edges) {
        visited = new boolean[info.length];
        visited[0]=true;
        dfs(1, 0, info, edges);
        return maxSheep;
    }
    
    private void dfs(int sheep, int wolf, int[] info, int[][] edges){
        if(sheep<=wolf){
           return;
        }
        maxSheep = Math.max(maxSheep, sheep);
        
        for(int[] edge : edges){
            int parent = edge[0];
            int child = edge[1];
            
            if(visited[parent]&&!visited[child]){
                visited[child]=true;
                if(info[child]==0){
                    dfs(sheep+1, wolf, info, edges);
                }else{
                    dfs(sheep, wolf+1, info, edges);
                }
                visited[child]=false;
            }
        }
    }
}