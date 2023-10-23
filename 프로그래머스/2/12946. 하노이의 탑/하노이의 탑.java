import java.util.*;

class Solution {
    private List<int[]> hist = new ArrayList<>();
    
    public int[][] solution(int n) {
        hanoi(n, 1,2,3);

        int[][] answer = new int[hist.size()][2];
        
        for(int i=0; i<hist.size(); i++){
            answer[i]=hist.get(i);
        }
        
        return answer;
    }
    
    private void hanoi(int n, int from, int via, int to){
        if(n==1){
            move(from, to);
            return;
        }
        hanoi(n-1, from, to, via);
        move(from, to);
        hanoi(n-1, via, from, to);
    }
    
    private void move(int from, int to){
        hist.add(new int[]{from, to});
    }
}