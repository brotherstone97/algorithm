class Solution {
    int[][] tri;
    public int[] solution(int n) {
        tri = new int[n][n];
        
        int[] answer = new int[n*(n+1)/2];
        
        int y = -1;
        int x = 0;
        int cnt=1;
        
        for(int i=0; i<n; i++){
            for(int j=i; j<n; j++){
                if(i%3==0){
                    y++;
                }else if(i%3==1){
                    x++;
                } else{
                    x--;
                    y--;
                }
                tri[y][x] = cnt++;
            }
        }
        
        int idx=0;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(tri[i][j]==0){
                    break;
                }
                answer[idx++]=tri[i][j];
            }
        }
    
        return answer;
    }
}