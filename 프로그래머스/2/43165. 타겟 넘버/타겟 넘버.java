class Solution {
    private int cnt;
    private int[] n;
    private int t;
    
    public int solution(int[] numbers, int target) {
        n = numbers;
        t = target;
        
        dfs(0,0);
        
        return cnt;
    }
    
    private void dfs(int depth, int sum){
        if(depth>=n.length){
            if(sum==t){
                cnt++;
            }
            return;
        }
        
        dfs(depth+1, sum+n[depth]);
        dfs(depth+1, sum-n[depth]);
        
    }
}