import java.util.*;

class Solution {
    private final List<int[]> list = new ArrayList<>();
    
    public int[][] solution(int n) {
        hanoi(n, 1,3,2);

        return list.toArray(new int[0][]);
    }
    
    private void hanoi(int n, int from, int to, int via){
        if(n==1){
            move(from, to);
            return;
        }
        hanoi(n-1, from, via, to);
        move(from, to);
        hanoi(n-1, via, to, from);
    }
    
    private void move(int from, int to){
        list.add(new int[]{from, to});
    }
}