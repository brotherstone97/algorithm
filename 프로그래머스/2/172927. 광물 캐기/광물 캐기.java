import java.util.*;

class Solution {
    private int fatigue=25*50+1;
    private List<Integer> combination;
    private List<List<Integer>> seq = new ArrayList<>();
    private Stack<String> st = new Stack<>();
    
    public int solution(int[] picks, String[] minerals) {
        for(int i=minerals.length-1; i>=0; i--){
            st.add(minerals[i]);
        }
        
        int need = 0;
        int[] needs = new int[3];
        if(minerals.length%5==0){
            need = minerals.length/5;
        }else{
            need = minerals.length/5 + 1;
        }

        int sum = 0;
        for(int i=0; i<picks.length; i++){
            if(picks[i]>=need){
                needs[i]=need;
                sum+=need;
                break;
            }
            needs[i] = picks[i];
            sum+= picks[i];
            need-=picks[i];
        }
        
        combination = new ArrayList<>(sum);
        
        createCombi(needs);
        getSeq(combination.size(), combination.size(), new ArrayList<>(), new boolean[combination.size()]);
        getMinFatigue(minerals);
        
        return fatigue;
    }
    
    private void fillStack(String[] minerals){
        for(int i=minerals.length-1; i>=0; i--){
            st.add(minerals[i]);
        }
    }
    private void getMinFatigue(String[] minerals){
        for(List<Integer> list : seq){
            int _fatigue = 0;
            for(int pick : list){
                if(pick==0){
                    _fatigue+=calcFatigue(0);
                    continue;
                }
                if(pick==1){
                    _fatigue+=calcFatigue(1);
                    continue;
                }
                _fatigue+=calcFatigue(2);
                continue;
            }
            fatigue = Math.min(fatigue, _fatigue);
            fillStack(minerals);
        }
    }
    
    private int calcFatigue(int currentPick){
        int _fatigue = 0;
        
        if(currentPick==0){
            for(int i=0; i<5; i++){
                if(!st.isEmpty()){
                    String popped = st.pop();
                    _fatigue++;
                }
            }
        }
        else if(currentPick==1){
            for(int i=0; i<5; i++){
                if(!st.isEmpty()){
                    String popped = st.pop();
                    if(popped.equals("diamond")){
                        _fatigue+=5;
                    }else{
                        _fatigue++;
                    }
                }
            }
        }
        else {
            for(int i=0; i<5; i++){
                if(!st.isEmpty()){
                    String popped = st.pop();
                    if(popped.equals("diamond")){
                        _fatigue+=25;
                    } else if(popped.equals("iron")){
                        _fatigue+=5;
                    }else{
                        _fatigue++;
                    }
                }
            }
        }
        
        return _fatigue;
    }
    private void getSeq(int n, int r, List<Integer> list, boolean[] visited){
        if(r==0){
            List<Integer> temp = new ArrayList<>();
            list.forEach(e->temp.add(e));
            seq.add(temp);
            return;
        }
        for(int i=0; i<n; i++){
            if(visited[i]){
                continue;
            }
            visited[i]=true;
            list.add(combination.get(i));
            getSeq(n, r-1, list, visited);
            list.remove(list.size()-1);
            visited[i]=false;
        }
    }
    
    private void createCombi(int[] needs){
        for(int i=0; i<needs.length; i++){
            int cnt = needs[i];
            for(int j=0; j<cnt; j++){
                combination.add(i);
            }
        }
    }
}