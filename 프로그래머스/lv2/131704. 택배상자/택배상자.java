import java.util.*;

class Solution {
    private Stack<Integer> st = new Stack<>();
    private int[] asc;
    private int pointer;
    public int solution(int[] order) {
        int answer = 0;
        
        //1. order깊은 복사 -> 오름차순 정렬
        asc = order.clone();
        Arrays.sort(asc);
        
        for(int e : order){
            while(pointer<order.length||!st.isEmpty()){
                if(pointer<order.length && e==asc[pointer]){
                    answer++;
                    pointer++;
                    break;
                }
                if(!st.isEmpty()){
                    if(e==st.peek()){
                        st.pop();
                        answer++;
                        break;
                    }
                    if(pointer>=order.length){
                        return answer;
                    }
                }
                st.push(asc[pointer]);
                pointer++;
            }
        }
        return answer;
    }
}