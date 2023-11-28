import java.util.*;

class Solution {
    private int hIndex;
    
    public int solution(int[] citations) {
        Integer[] newCitations = Arrays.stream(citations).boxed().toArray(Integer[]::new);
        //1. 내림차순 정렬(요소를 빠르게 파악하기 위함)
        Arrays.sort(newCitations, Collections.reverseOrder());
        
        //2. citations수 만큼 반복, n개 논문 중 h이상인 논문이 h개 이상인지 파악
        for(int h=1; h<=newCitations.length; h++){
            int cnt=0;
            for(int c : newCitations){
                if(c>=h){
                    cnt++;
                    continue;
                }
                break;
            }
            if(cnt>=h){
                hIndex = h;
                continue;
            }
            break;
        }
        return hIndex;
    }
}