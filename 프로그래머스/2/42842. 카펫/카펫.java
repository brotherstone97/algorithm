import java.util.*;

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = {};
        List<int[]> measures = calcMeasure(yellow);
        return calcWH(brown, yellow, measures);
    }
    
    private int[] calcWH(int brown, int yellow, List<int[]> measures){
        for(int[] measure: measures){
            int brownWidth = measure[0]+2;
            int brownHeight = measure[1]+2;
            if(brownWidth * brownHeight - yellow == brown){
                return new int[]{brownWidth,brownHeight};
            }
        }
        return null;
    }
    
    private List<int[]> calcMeasure(int yellow){
        List<int[]> measures = new ArrayList<>();
        for(int i=1; i<=yellow; i++){
            if(yellow%i==0){
                //i(제수)와 몫은 yellow의 약수
                //i<=몫
                int quo = yellow/i;
                measures.add(new int[]{quo,i});
                if(i>=quo){
                    return measures;
                }
            }
        }
        return measures;
    }
}