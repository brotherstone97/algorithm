import java.util.TreeSet;
import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        TreeSet<Integer> ts = new TreeSet<>(new Comparator<>(){
            @Override
            public int compare(Integer o1, Integer o2){
                if(o1.equals(o2)){
                    return -1;
                }
                return o1-o2;
            }
        });
        Arrays.stream(scoville).forEach(e->ts.add(e));

        while(ts.size()>=2 && ts.first() < K){
            int food1 = ts.pollFirst();
            int food2 = ts.pollFirst();
            int res = food1 + food2 * 2;
            
            answer++;
            
            ts.add(res);
        }
        
        if(ts.size()==1 && ts.first()<K){
            return -1;
        }
        
        return answer;
    }
}