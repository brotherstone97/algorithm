import java.util.*;

class Solution {
    
    //6. 상하좌우 이동 좌표
    private int[] dy = {-1,0,1,0};
    private int[] dx = {0,-1,0,1};
    
    private int rowSize;
    private int colSize;
    
        
    public int[] solution(String[] maps) {
        List<Integer> answer = new ArrayList<>();
        rowSize = maps.length;
        colSize = maps[0].length();
        
        
        char[][] newArr = make2dArr(maps, rowSize, colSize);
        
        //5. chk배열 선언해 이미 간 곳은 다시 안가도록
        boolean[][] chk = new boolean[rowSize][colSize];
        
        //2. 2차원 배열 완탐
        for(int i=0; i<rowSize; i++){
            for(int j=0; j<colSize; j++){
                if(Character.isDigit(newArr[i][j])&&!chk[i][j]){
                    System.out.println("bfs호출 시킨 좌표 누구야? "+i+" "+j);
                    chk[i][j]=true;
                    //bfs호출
                    answer.add(bfs(chk, newArr, i, j));
                }
            }
        }
            
        Collections.sort(answer);
        
        int[] result = new int[answer.size()];
        for(int i=0; i<answer.size(); i++){
            result[i]=answer.get(i);
        }
        if(result.length==0){
            return new int[]{-1};
        }
        return result;
    }
    //3. 탐색 중 숫자 발견 시 bfs호출. 이어진 곳이 더이상 없을 때 종료. 각 칸에 적힌 숫자의 합 리턴
    private int bfs(boolean[][] chk, char[][] newArr, int i, int j){
        int sum=newArr[i][j]-'0';
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{i,j});
        
        while(!q.isEmpty()){
        
            int[] polled = q.poll();
            int y = polled[0];
            int x = polled[1];
            for(int k=0; k<4; k++){
                //범위 안에 들어가고, 이미간 곳이 아니라면 enqueue + 무인도인 좌표
                int _y = y+dy[k];
                int _x = x+dx[k];
                if(isValidCoord(_y, _x) && !chk[_y][_x] && Character.isDigit(newArr[_y][_x])){
                    System.out.println("다음 좌표 y: "+_y+" x: "+_x);
                    chk[_y][_x]=true;
                    sum+= newArr[_y][_x] - '0';
                    q.offer(new int[]{_y,_x});
                }
            }
        }
        return sum;
    }
    //4. 탐색 범위가 배열을 넘어가지 않도록 검증 메서드 정의
    private boolean isValidCoord(int y, int x){
        return (y>=0 && y<rowSize)&&(x>=0 && x<colSize);
    }
    
    
    //1. 2차원 배열 만들기
    private char[][] make2dArr(String[] maps, int rowSize, int colSize){

        char[][] arr = new char[rowSize][colSize];
        for(int i=0; i<rowSize; i++){
            char[] stringToCharArr = maps[i].toCharArray();
            for(int j=0; j<colSize; j++){
                arr[i][j]= stringToCharArr[j];
            }
        }
        for(int i=0; i<rowSize; i++){
            System.out.println(Arrays.toString(arr[i]));
        }
        
        return arr;
    }
    
}
//x:바다, 숫자: 무인도
//무인도는 상하좌우로 연결되며 숫자의 합은 최대 머물 수 있는 일 수
//각 섬에서 최대 며칠 머물 수 있는지 오름차순으로 정렬된 배열 return





//7. result정렬
