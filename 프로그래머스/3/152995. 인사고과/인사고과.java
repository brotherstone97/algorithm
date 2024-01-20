import java.util.*;

class Solution {
    private Score[] scoreArr;
    
    public int solution(int[][] scores) {
        init(scores);
        return calcRank(new Score(scores[0][0],scores[0][1]));
    }
    
    private void init(int[][] scores){
        scoreArr = new Score[scores.length];

        for(int i=0; i<scores.length; i++){
            scoreArr[i] = new Score(scores[i][0], scores[i][1]);
        }
        
        Arrays.sort(scoreArr);
    }
    private int calcRank(Score wanho){
        int maxPeer=0;
        int rank=1;
        
        for(Score e : scoreArr){
            if(maxPeer > e.peer){
                if(wanho.equals(e)){
                    return -1;
                }
                continue;
            }
            maxPeer = Math.max(maxPeer, e.peer);
            if(wanho.getSum()<e.getSum()){
                rank++;
            }
        }
        
        return rank;
    }
    
    class Score implements Comparable<Score>{
        int attitude;
        int peer;
        
        Score(int attitude, int peer){
            this.attitude=attitude;
            this.peer=peer;
        }
        
        @Override
        public int compareTo(Score o){
            if(o.attitude==this.attitude){
                return this.peer - o.peer;
            }
            return o.attitude-this.attitude;
        }
        
        public boolean equals(Score o){
            return this.attitude==o.attitude&&this.peer==o.peer;
        }
        
        public int getSum(){
            return this.attitude+this.peer;
        }
    }
}