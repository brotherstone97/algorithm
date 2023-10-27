import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        Arrays
            .stream(priorities)
            .boxed()
            .forEach(p->pq.add(p));
        
        while(!pq.isEmpty()){
            for(int i=0; i<priorities.length; i++){
                if(pq.peek()==priorities[i]){
                    answer++;
                    pq.poll();
                    
                    if(i==location){
                        return answer;
                    }
                }
            }
        }
        return answer;
    }
}