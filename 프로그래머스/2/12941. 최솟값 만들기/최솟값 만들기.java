import java.util.*;

class Solution
{
    public int solution(int []A, int []B)
    {
        int answer = 0;
        
        PriorityQueue<Integer> _A = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> _B = new PriorityQueue<>();
        
        for(int i=0; i<A.length; i++){
            _A.add(A[i]);
            _B.add(B[i]);
        }

        while(!_A.isEmpty()){
            answer+=_A.poll()*_B.poll();
        }
        
        return answer;
    }
}