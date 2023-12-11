import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        //1. target정렬 e기준 오름차순
        Arrays.sort(targets, (e1,e2)-> e1[1]-e2[1]);
        
        int criteria=0;
        int answer = 0;
        
        //기준 target의 end보다 현재 target의 start가 크거나 같다면 기준을 현재 target으로 바꾸고 answer++
        for(int i=1; i<targets.length; i++){
            if(targets[criteria][1]<=targets[i][0]){
                criteria=i;
                answer++;
            }
        }
        //마지막 요격 미사일 개수 반영
        return ++answer;
    }
}