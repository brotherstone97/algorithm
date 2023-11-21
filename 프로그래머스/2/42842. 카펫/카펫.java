import java.util.List;
import java.util.ArrayList;

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = {};
        
        List<int[]> yellowPair = new ArrayList<>();
        
        for(int i=1; i<=yellow; i++){
            if(yellow % i == 0 && (yellow / i >= i)){
                yellowPair.add(new int[]{yellow/i, i});
            }
        }
        
        for(int[] pair : yellowPair){
            int width = pair[0];
            int height = pair[1];
            
            if(2 * (width + 2) + 2 * height == brown){
                answer = new int[]{width+2, height+2};
                break;
            }
        }
        
        return answer;
    }
}