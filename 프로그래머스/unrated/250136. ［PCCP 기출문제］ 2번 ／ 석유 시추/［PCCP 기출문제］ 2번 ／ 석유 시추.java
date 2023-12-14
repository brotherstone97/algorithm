import java.util.*;

class Solution {
    private final int[] dy = {1,0,-1,0};
    private final int[] dx = {0,1,0,-1};
    
    private boolean[][] visited;
    private int[] reserves;
    
    private int maxY;
    private int maxX;

    private int answer = 0;
    
    public int solution(int[][] land) {
        maxY= land.length;
        maxX= land[0].length;
        
        visited = new boolean[maxY][maxX];
        reserves = new int[maxX];

        for(int i=0; i<maxY; i++){
            for(int j=0; j<maxX; j++){
                if(!visited[i][j]&&land[i][j]==1){
                    visited[i][j]=true;
                    bfs(i, j, land);
                }
            }
        }
        Arrays.sort(reserves);
        
        return reserves[maxX-1];
    }
    
    //주어진 좌표탐색 하여 각 좌표에 매장된 석유량 map에 추가
    private void bfs(int i, int j, int[][] land){
        int oilCnt=1;
        List<int[]> oilCoordi = new ArrayList<>();
        oilCoordi.add(new int[]{i, j});
        
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{i, j});
        
        while(!q.isEmpty()){
            int[] coordi = q.poll();
            int y = coordi[0];
            int x = coordi[1];
                        
            for(int k=0; k<4; k++){
                int _y = y + dy[k];
                int _x = x + dx[k];
                
                if(isValidCoordi(_y, _x) && land[_y][_x]==1  && !visited[_y][_x]){
                    visited[_y][_x]=true;
                    q.offer(new int[]{_y, _x});
                    oilCoordi.add(new int[]{_y, _x});
                    oilCnt++;
                }
            }
        }
        
        boolean[] visitedX = new boolean[maxX];
        final int fixedOilCnt = oilCnt;
        
        oilCoordi.forEach(e->{
            if(!visitedX[e[1]]){
                visitedX[e[1]]=true;
                reserves[e[1]]+=fixedOilCnt;
            }
        });
    }
    
    private boolean isValidCoordi(int y, int x){
        return (0<=y&&y<maxY)&&(0<=x&&x<maxX);
    }
}