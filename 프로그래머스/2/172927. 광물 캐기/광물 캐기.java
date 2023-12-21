import java.util.Map;
import java.util.HashMap;

class Solution {
    private int minFatigue=Integer.MAX_VALUE;
    private int currentMineral;
    private Map<String, Integer> ironPick = new HashMap<>();
    private Map<String, Integer> stonePick = new HashMap<>();
    
    public int solution(int[] picks, String[] minerals) {
        putData();
        dfs(0, picks, minerals);
        return minFatigue;
    }
    
    private void dfs(int fatigue, int[] picks, String[] minerals){
        if(fatigue>=minFatigue){
            return;
        }
        
        if((picks[0]==0&&picks[1]==0&&picks[2]==0) || currentMineral>=minerals.length){
            minFatigue=Math.min(minFatigue, fatigue);
            return;
        }
        
        for(int i=0; i<3; i++){
            if(picks[i]==0){
                continue;
            }
            
            int[] result = mine(i, minerals);
            picks[i]--;
            currentMineral+=result[1];
            dfs(fatigue+result[0], picks, minerals);
            picks[i]++;
            currentMineral-=result[1];
        }
    }
    
    private int[] mine(int pick, String[] minerals){
        int fatigue=0;
        int cnt=0;
        
        if(minerals.length-currentMineral>=5){
            if(pick==0){
                for(int i=currentMineral; i<currentMineral+5; i++){
                    fatigue++;
                }   
            } else if(pick==1){
                for(int i=currentMineral; i<currentMineral+5; i++){
                    fatigue+=ironPick.get(minerals[i]);
                }   
            } else{
                for(int i=currentMineral; i<currentMineral+5; i++){
                    fatigue+=stonePick.get(minerals[i]);
                }   
            }
            
            cnt+=5;
        } else{
            if(pick==0){
                for(int i=currentMineral; i<minerals.length; i++){
                    fatigue++;
                }   
            } else if(pick==1){
                for(int i=currentMineral; i<minerals.length; i++){
                    fatigue+=ironPick.get(minerals[i]);
                }   
            } else{
                for(int i=currentMineral; i<minerals.length; i++){
                    fatigue+=stonePick.get(minerals[i]);
                }   
            }         
            cnt+=minerals.length-currentMineral;
        }
        
        return new int[]{fatigue, cnt};
    }
    
    private void putData(){
        ironPick.put("diamond", 5);
        ironPick.put("iron", 1);
        ironPick.put("stone", 1);
        stonePick.put("diamond", 25);
        stonePick.put("iron", 5);
        stonePick.put("stone", 1);
    }
}