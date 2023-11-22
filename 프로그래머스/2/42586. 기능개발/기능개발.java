import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        
        Stack<Integer> proSt = new Stack();
        Stack<Integer> spSt = new Stack();
        
        for(int i=progresses.length-1; i>-1; i--){
            proSt.push(progresses[i]);
            spSt.push(speeds[i]);
        }
        
        while(!proSt.isEmpty()){
            addSpeeds(proSt, spSt);
            int deployRes = deploy(proSt, spSt);
            if(deployRes>0){
                answer.add(deployRes);
            }
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    
    private int deploy(Stack<Integer> proSt, Stack<Integer> spSt){
        int cnt=0;
        
        while(!proSt.isEmpty() && proSt.peek()>=100){
            proSt.pop();
            spSt.pop();
            cnt++;
        }
        
        return cnt;
    }
    
    private void addSpeeds(Stack<Integer> proSt, Stack<Integer> spSt){
        for(int i=0; i<proSt.size(); i++){
            proSt.set(i, proSt.get(i)+spSt.get(i));
        }
    }
}