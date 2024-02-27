import java.util.*;

class Solution {
    int[] dy={1,0,-1,0};
    int[] dx={0,1,0,-1};
    
    int[] R;
    int[] G;
    
    String[] board;
    boolean[][] visited;
    
    //2. Queue에 들어갈 status class
    static class Status{
        int y;
        int x;
        int cnt;
        
        Status(int y, int x, int cnt){
            this.y=y;
            this.x=x;
            this.cnt=cnt;
        }
    }
    
    public int solution(String[] _board) {
        board = _board;
        visited = new boolean[board.length][board[0].length()];
        searchRG();
        return bfs();
    }
//0.R과 G를 찾는다.
    void searchRG(){
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length(); j++){
                if(R!=null&&G!=null){
                    return;
                }
                if(board[i].charAt(j)=='R'){
                    R = new int[]{i,j};
                    continue;
                }
                if(board[i].charAt(j)=='G'){
                    G=new int[]{i,j};
                }
            }
        }        
    }
    
    //1. 미끄러져 이동한다. 제한조건: 현재위치 기준 가려는 방향이 장애물로 막혀있거나 경계선이라 더이상 갈 수 없는 경우는 제외
    int[] slide(Status cur, int dir){
        
        //하
        if(dir==0){
           while(cur.y<board.length && board[cur.y].charAt(cur.x)!='D'){
               cur.y++;
           }
            return new int[]{cur.y-1, cur.x};
        }
        //우
        if(dir==1){
           while(cur.x<board[0].length() && board[cur.y].charAt(cur.x)!='D'){
               cur.x++;
           }
            return new int[]{cur.y, cur.x-1};            
        }
        //상
        if(dir==2){
           while(cur.y>-1 && board[cur.y].charAt(cur.x)!='D'){
               cur.y--;
           }
            return new int[]{cur.y+1, cur.x};                  
        }
        //좌
           while(cur.x>-1 && board[cur.y].charAt(cur.x)!='D'){
               cur.x--;
           }
            return new int[]{cur.y, cur.x+1};           
    }

        
    //3. bfs
    int bfs(){
        Queue<Status> q = new LinkedList<>();
        q.offer(new Status(R[0], R[1], 0));
        
        while(!q.isEmpty()){
            Status polled = q.poll();
            for(int i=0; i<4; i++){
                int _y = polled.y+dy[i];
                int _x = polled.x+dx[i];
                
                if(isValidCoord(_y, _x)&&board[_y].charAt(_x)!='D'){
                    int[] slidingRes = slide(new Status(_y, _x, -1), i);
                    
                    if(!visited[slidingRes[0]][slidingRes[1]]){
                        if(board[slidingRes[0]].charAt(slidingRes[1])=='G'){
                            return polled.cnt+1;
                        }
                        visited[slidingRes[0]][slidingRes[1]]=true;
                        q.offer(new Status(slidingRes[0], slidingRes[1], polled.cnt+1));
                    }
                }
            }
        }
        return -1;
    }
    
    //4. 현재 좌표가 grid범위 내 좌표인지 체크
    boolean isValidCoord(int y, int x){
        return 0<=y&&y<board.length&&0<=x&&x<board[0].length();
    }
}