import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        long sum1 = 0;
        long sum2 = 0;
        
        for(int i=0; i<queue1.length; i++){
            sum1+=queue1[i];
            sum2+=queue2[i];
            
            q1.offer(queue1[i]);
            q2.offer(queue2[i]);
        }
        int maxCount = queue1.length*3;
        
        while(sum1!=sum2){
            if(maxCount-answer==0){
                return -1;
            }
            answer++;
            
            if(sum1>sum2){
                int polled = q1.poll();
                sum1-=polled;
                sum2+=polled;
                
                q2.offer(polled);
                continue;
            }
            int polled = q2.poll();
            sum2-=polled;
            sum1+=polled;
            
            q1.offer(polled);
        }
        return answer;
        
    }
    
}


//1.    q1, q2의 원소의 합(long)이 구하기, 만약 홀수이면 -1 return. 아니라면 각 큐의 원소합은 (q1+q2)/2

//2. dfs로 구현, 각 큐의 원소합이 조건에 맞을경우 answer = min(answer, 현재)