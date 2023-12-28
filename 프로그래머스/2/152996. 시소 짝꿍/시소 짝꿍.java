import java.util.*;

class Solution {
    public long solution(int[] weights) {
        Arrays.sort(weights);
        Map<Double, Integer> map = new HashMap<>();
        long answer = 0;
        
        for(int e: weights){
            double a = e*1.0;
            double b = (e*1.0)/2;
            double c = (e*2.0)/3;
            double d = (e*3.0)/4;
            
            if(map.containsKey(a)){
                answer+=map.get(a);
            }
            if(map.containsKey(b)){
                answer+=map.get(b);
            }
            if(map.containsKey(c)){
                answer+=map.get(c);
            }
            if(map.containsKey(d)){
                answer+=map.get(d);
            }
            
            map.put(e*1.0, map.getOrDefault(e*1.0, 0)+1);
        }
        
        return answer;
    }
}