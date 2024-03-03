import java.util.*;
import java.util.stream.Collectors;

class Solution {
    private long q1Sum;
    private long q2Sum;
    private long target;
    
    public int solution(int[] queue1, int[] queue2) {
        int q1Max = 0;
        int q2Max = 0;
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        
        for(int i=0; i<queue1.length; i++){
            q1Sum+=(long)queue1[i];
            q1Max = Math.max(q1Max,queue1[i]);
            q1.offer(queue1[i]);
            
            q2Sum+=(long)queue2[i];
            q2Max = Math.max(q2Max,queue2[i]);
            q2.offer(queue2[i]);
        }
        target = (q1Sum+q2Sum)/2;
        
        //합이 홀수일 때 -1
        if((q1Sum+q2Sum)%2==1){
            return -1;
        }
        
        //target보다 큰 요소가 있으면 -1리턴
        if(q1Max>target||q2Max>target){
            return -1;
        }
        
        return calcCnt(q1, q2);
    }
    
    private int calcCnt(Queue<Integer> q1, Queue<Integer> q2){
        int cnt=0;
        int limit = q1.size()*3;

        while(q1Sum!=q2Sum&&cnt<limit){
            cnt++;
            if(q1Sum>q2Sum){
                int polled = q1.poll();
                q2.offer(polled);
                q1Sum-=polled;
                q2Sum+=polled;
                continue;
            }
            int polled = q2.poll();
            q1.offer(polled);
            q1Sum+=polled;
            q2Sum-=polled;
        }
        
        if(cnt>=limit){
            return -1;
        }
        
        return cnt;
    }
}