class Solution {
    private int[] answer;
    
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        answer = new int[balls.length];
        
        for(int i=0; i<balls.length; i++){
            int temp=Integer.MAX_VALUE;
            for(int f=0; f<4; f++){
                if(f==0){
                    //target볼과 start볼의 y값이 일치하면서 start의 x가 더 클경우 y축대칭 생략
                    if(balls[i][1]==startY&&balls[i][0]<startX){
                        continue;
                    }
                    
                    int[] filpedBall = flipY(balls[i]);
                    temp = Math.min(temp, calcDistance(filpedBall, new int[]{startX, startY}));
                    answer[i]=temp;
                    continue;
                }
                if(f==1){
                    // target볼과 start볼의 x값이 일치할 경우 x축대칭 생략
                    if(balls[i][0]==startX&&balls[i][1]<startY){
                        continue;
                    }
                    int[] filpedBall = flipX(balls[i]);
                    
                    temp = Math.min(temp, calcDistance(filpedBall, new int[]{startX, startY}));
                    answer[i]=temp;
                    continue;
                }
                if(f==2){
                    //target볼과 start볼의 x값이 일치하면서 start의 y가 더 작을 경우 y축대칭 생략
                    if(balls[i][0]==startX&&balls[i][1]>startY){
                        continue;
                    }
                    int[] filpedBall = flipYEqualN(n, balls[i]);                    
                    temp = Math.min(temp, calcDistance(filpedBall, new int[]{startX, startY}));
                    answer[i]=temp;
                    continue;
                }
                //target볼과 start볼의 y값이 일치하면서 start의 x가 더 작을 경우 y축대칭 생략
                if(balls[i][1]==startY&&balls[i][0]>startX){
                    continue;
                }
                int[] filpedBall = flipXEqualM(m, balls[i]);                
                temp = Math.min(temp, calcDistance(filpedBall, new int[]{startX, startY}));
                answer[i]=temp;
            }
        }
        return answer;
    }
    
    // 대칭이동된 target볼과 start볼 사이의 거리를 구하는 메서드
    private int calcDistance(int[] target, int[] start){
        return (int)(Math.pow((double)Math.abs(start[0]-target[0]), 2.0) 
            + Math.pow((double)Math.abs(start[1]-target[1]), 2.0));
    }
    
    //x축, y축, y=n, x=m에 현재 target볼을 대칭시킨 뒤 좌표를 리턴하는 메서드
    private int[] flipY(int[] coord){
        return new int[]{-coord[0],coord[1]};
    }
    
    private int[] flipX(int[] coord){
        return new int[]{coord[0], -coord[1]};
    }
    
    private int[] flipYEqualN(int n, int[] coord){
        return new int[]{coord[0], n + Math.abs(coord[1]-n)};
    }
    
    private int[] flipXEqualM(int m, int[] coord){
        return new int[]{m+Math.abs(coord[0]-m), coord[1]};
    }
}