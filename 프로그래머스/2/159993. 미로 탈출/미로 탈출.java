import java.util.*;

class Solution {
    private int[] dy = {1,0,-1,0};
    private int[] dx = {0,1,0,-1};
    private boolean[][] visited;
    
    private int maxY;
    private int maxX;

    public int solution(String[] maps) {
        maxY = maps.length;
        maxX = maps[0].length();
        
        visited = new boolean[maxY][maxX];
        
        int[] startPoint = findPoint('S', maps);
        int[] leverPoint = findPoint('L', maps);
        
        int lever = bfs(startPoint, 'L', maps);
        
        if(lever==-1){
            return -1;
        }
        
        visited = new boolean[maxY][maxX];
                
        int end = bfs(leverPoint, 'E', maps);
        
        if(end==-1){
            return -1;
        }
        
        return lever+end;
    }
    
    private int[] findPoint(char target, String[] maps){
        for(int i=0; i<maxY; i++){
            for(int j=0; j<maxX; j++){
                if(maps[i].charAt(j)==target){
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("target 찾을 수 없음");
    }
    
    private int bfs(int[] start, char target, String[] maps){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{start[0],start[1],0,0});
        
        while(!q.isEmpty()){
            int[] polled = q.poll();
            
            int y = polled[0];
            int x = polled[1];
            int cnt=polled[2];
            
            for(int k=0; k<4; k++){
                int _y = y+dy[k];
                int _x = x+dx[k];
                
                if(isValidCoordi(_y, _x) && !visited[_y][_x] && maps[_y].charAt(_x)!='X'){
                    visited[_y][_x]=true;
                    if(maps[_y].charAt(_x)==target){
                        return cnt+1;
                    } else{
                        q.add(new int[]{_y,_x, cnt+1});
                    }
                }
            }
        }
        return -1;
    }
    
    private boolean isValidCoordi(int y, int x){
        return 0<=y&&y<maxY&&0<=x&&x<maxX;
    }
}
                   