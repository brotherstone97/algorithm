import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        
        Arrays.stream(priorities).boxed().forEach(p -> q.add(p));
        
        while(!q.isEmpty()){
            for(int i=0; i<priorities.length; i++){
                if(q.peek() == priorities[i]){
                    answer++;
                    q.poll();
                if(i==location){
                    return answer;
                }
            }

            }
        }
        return -1;
    }

}

//순서는 배열을 이용
//