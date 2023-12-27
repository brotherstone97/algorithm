import java.util.*;

class Solution {
    private int[] dy = {1,0,-1,0};
    private int[] dx = {0,1,0,-1};
    private int maxY;
    private int maxX;
    private int answer = Integer.MAX_VALUE;
    private int[] rPoint;
    
    private boolean[][] visited;
    
    public int solution(String[] board) {
        //init
        maxY = board.length;
        maxX = board[0].length();
        getRPoint(board);
        visited = new boolean[maxY][maxX];        
        visited[rPoint[0]][rPoint[1]]=true;
        
        bfs(board);
        
        if(answer==Integer.MAX_VALUE){
            return -1;
        }
        
        return answer;
    }

    //2. BFS
    private void bfs(String[] board){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{rPoint[0], rPoint[1], 0});
        
        while(!q.isEmpty()){
            int[] polled = q.poll();
            int y = polled[0];
            int x = polled[1];
            int cnt = polled[2];
            
            for(int i=0; i<4; i++){
                int _y = y;
                int _x = x;
                while(isValidCoordi(_y+dy[i], _x+dx[i], board)){
                    _y+=dy[i];
                    _x+=dx[i];
                }
                //처음부터 y에서 걸러져 제자리라면
                if(_y==y&&_x==x){
                    continue;
                }
                if(visited[_y][_x]==true){
                    continue;
                }
                if(board[_y].charAt(_x)=='G'){
                    answer = Math.min(answer, cnt+1);
                    visited[_y][_x]=true;
                    continue;
                }
                //끝까지 이동한 좌표를 enQ
                q.offer(new int[]{_y, _x, cnt+1});
                visited[_y][_x]=true;
            }
            
        }
    }
    
    //1. 시작포인트
    private void getRPoint(String[] board){
        for(int i=0; i<maxY; i++){
            for(int j=0; j<maxX; j++){
                if(board[i].charAt(j)=='R'){
                    rPoint = new int[]{i,j};
                    return;
                }
            }
        }
    }
    
    private boolean isValidCoordi(int y, int x, String[] board){
        if(0<=y&&y<maxY&&0<=x&&x<maxX){
            if(board[y].charAt(x)!='D'){
                return true;
            }
        }
        return false;
    }
}