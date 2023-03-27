import java.util.*;
import java.util.stream.*;


class Solution {
    public int solution(int n, int m, int[] section) {
        int cnt=1;
        int roller = section[0];
        for(int i=1; i<section.length; i++){
            if(roller+m-1 < section[i]){
                cnt++;
                roller=section[i];
            }
        }
        return cnt;
    }
}