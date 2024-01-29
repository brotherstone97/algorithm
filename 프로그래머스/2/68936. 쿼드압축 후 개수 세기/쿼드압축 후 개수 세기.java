class Solution {
    private int zeroCnt;
    private int oneCnt;
    
    public int[] solution(int[][] arr) {
        int N = calcLog(arr.length);
        int res = dnq(arr, N, 0, 0);
        
        if(res==0){
            zeroCnt++;
        }else if(res==1){
            oneCnt++;
        }
        
        return new int[]{zeroCnt, oneCnt}; 
    }
    
    //재귀
    private int dnq(int[][] arr, int n, int startR, int startC){
        if(n==0){
            return arr[startR][startC];
        }
        int[] res = new int[4];
        res[0] = dnq(arr, n-1, startR, startC); // 제 2사분면
        res[1] = dnq(arr, n-1, startR, startC+(int)Math.pow(2, n-1)); //제 1사분면
        res[2] = dnq(arr, n-1, startR+(int)Math.pow(2, n-1), startC); //제 3사분면
        res[3] = dnq(arr, n-1, startR+(int)Math.pow(2, n-1), startC+(int)Math.pow(2, n-1)); //제 4사분면

        
        if(res[0]==res[1]&&res[0]==res[2]&&res[0]==res[3]){
            return res[0];
        }
        for(int i=0; i<4; i++){
            if(res[i]==0){
                zeroCnt++;
                continue;
            }
            if(res[i]==1){
                oneCnt++;
            }
        }
        return -1;   
    }
    
    private int calcLog(int arrLength){
        return (int)(Math.log10((double)arrLength)/Math.log10(2.0));
    }
}