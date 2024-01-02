class Solution {
    public int solution(int n, long l, long r) {
        return recurse(n, l, r, 1);
    }
    
    private int recurse(int n, long l, long r, long criteria){
        if(n==0){
            return 1;
        }
        
        int currentCnt=0;
        //현재 n에서의 한 덩어리의 크기 는 5^n-1 즉, 5등분한 값이다.
        long part = (long)Math.pow(5.0,(double)n-1);
        
        for(int i=0; i<5; i++){
            //가운데 덩어리는 0이므로 continue. 현재 덩어리의 끝값이 l보다 작거나, 현재 덩어리의 시작값이 r보다 커도 continue 
            if(i==2||l>criteria+part*(i+1)-1||r<criteria+part*i){
                continue;
            }
            currentCnt += recurse(n-1, l, r, criteria+part*i);
        }
        return currentCnt;
    }
}