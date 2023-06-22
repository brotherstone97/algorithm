import java.util.*;

class Solution {
    private int _min=Integer.MAX_VALUE;
    private int mineralsSize;
    private Map<String, Integer> diaPickax = new HashMap<>();
    private Map<String, Integer> ironPickax = new HashMap<>();
    private Map<String, Integer> stonePickax = new HashMap<>();
    private int currentMineral;
    
    public int solution(int[] picks, String[] minerals) {
        putData();
        mineralsSize=minerals.length;
        dfs(0, picks, minerals);
        return _min;
    }
    
    private void dfs(int sumDegree, int[] picks, String[] minerals){
        //모든 곡괭이 0개 || minerals배열의 끝에 다달았을 때.
        if((picks[0]==0&&picks[1]==0&&picks[2]==0)
          ||currentMineral>=mineralsSize){
            _min = Math.min(_min, sumDegree);
            return;
        }
        
        for(int i=0; i<3; i++){
            if(picks[i]==0){
                continue;
            }
            int[] calculated = calc(i, minerals);
            picks[i]-=1;
            dfs(sumDegree+calculated[0], picks, minerals);
            currentMineral -= calculated[1];
            picks[i]+=1;
            }
        }
        private int[] calc(int pick, String[] minerals){
        int degreeOfFatigue=0;
        int restMinerals = mineralsSize-currentMineral;
        int iter=0;
        if(restMinerals>=5){
            iter=5;
        }else{
            iter=restMinerals;
        }
        if(pick==0){
            for(int i=currentMineral; i<currentMineral+iter; i++){
                int degree = diaPickax.get(minerals[i]);
                degreeOfFatigue+=degree;
            }
        }else if(pick==1){
            for(int i=currentMineral; i<currentMineral+iter; i++){
                int degree = ironPickax.get(minerals[i]);
                degreeOfFatigue+=degree;
            }
        }else{
            for(int i=currentMineral; i<currentMineral+iter; i++){
                    int degree = stonePickax.get(minerals[i]);
                    degreeOfFatigue+=degree;
            }
        }
        currentMineral += iter;
        return new int[]{degreeOfFatigue,iter};
    }
    
    private void putData(){
        diaPickax.put("diamond", 1);
        diaPickax.put("iron", 1);
        diaPickax.put("stone", 1);
        ironPickax.put("diamond", 5);
        ironPickax.put("iron", 1);
        ironPickax.put("stone", 1);
        stonePickax.put("diamond", 25);
        stonePickax.put("iron", 5);
        stonePickax.put("stone", 1);
    }
    }
//최소한의 피로도 -> 완탐

//곡괭이는 아무거나 선택해서 사용할 수 없을 때까지 사용.
//광물은 반드시 순서대로 캘 수 있음
//더 이상 사용할 곡괭이가 없거나, 모든 광물을 다 캘 때까지 반복

/**
흐름
dfs(0, 0)호출 -> 다이아, 철, 돌 곡괭이 중 하나 선택. -> minerals 5개 캐고 피로도 합산 -> dfs(1, sum+??) 재귀 호출
dfs리턴 시 차감시킨 곡괭이 개수 원상복귀, 
종료 조건: 더 이상 사용할 곡괭이가 없거나, 모든 광물을 다 캔 경우
**/

//아니면 삼진트리?