import java.util.Arrays;

class Solution {
    private static final int OUT_OF_REACH = 1_000_001;
    
    public int solution(int x, int y, int n) {
        int[] dp = new int[y+1];
        Arrays.fill(dp, OUT_OF_REACH);
        dp[y]=0;
        
        for(int i=y; i>=0; i--){
            if(i%2==0&&i/2>=x){
                dp[i/2] = Math.min(dp[i/2], dp[i]+1);
            }
            if(i%3==0&&i/3>=x){
                dp[i/3] = Math.min(dp[i/3], dp[i]+1);
            }
            if(i-n>=x){
                dp[i-n] = Math.min(dp[i-n], dp[i]+1);
            }
        }
        
        if(dp[x]==OUT_OF_REACH){
            return -1;
        }
        
        return dp[x];
    }
}