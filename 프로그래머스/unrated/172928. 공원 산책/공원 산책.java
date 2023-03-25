import java.util.Arrays;

class Solution {
    private int parkLength=0;
    
    public int[] solution(String[] park, String[] routes) {
        
       int[] answer = {};
        park = park;
        int [] robotLocation={0,0};
        //로봇 초기 위치
        for(int i=0; i<park.length; i++){
            boolean flag=false;
            for (int j=0; j<park[0].length(); j++){
                if(park[i].charAt(j)=='S'){
                    robotLocation[0]=i;
                    robotLocation[1]=j;
                    flag=true;
                    break;
                }
                if(flag){
                    break;
                }
            }
        }
        
    //방향, 거리 분리
        parkLength=park[0].length();
        for(String route:routes){
            String[] splited = route.split(" ");
            String direction = splited[0];
            int count = Integer.parseInt(splited[1]);
            System.out.println("before moving="+ Arrays.toString(robotLocation));
            robotLocation = move(direction, count, robotLocation, park);
            System.out.println("after moving="+ Arrays.toString(robotLocation));
        }
        return robotLocation;
    }
    
    private int[] move(String dir, int cnt, int[] robotLocation, String[] park){
        int[] tempLocation= Arrays.copyOf(robotLocation, robotLocation.length);
        for(int i=0; i<cnt; i++){
            if(dir.equals("N")){
                tempLocation[0]-= 1;   
            }else if(dir.equals("S")){
                tempLocation[0]+= 1;   
            }else if(dir.equals("W")){
                tempLocation[1]-= 1;   
            }else{
                tempLocation[1]+= 1; 
            }
            if(!validCoordi(tempLocation[0], tempLocation[1], park)){
                return robotLocation; 
            }
        }        
        return tempLocation;
     }
    private boolean validCoordi(int y, int x, String[] park){
        if((y>=0 && y<parkLength) && (x>=0 && x<parkLength)){
            System.out.println(park[y].charAt(x));
            if(park[y].charAt(x)=='O'||park[y].charAt(x)=='S'){
                return true;
            }
        }
        return false;
    }
}       
//가는 길을 계산을 안함
//0. dy,dx 이동 좌표계 생성
//1. routes를 루프 돌며 공백기준으로 문자열 스플릿 -> 방향, 거리를 분리
//2. 이동 전 현재 로봇의 위치 + 이동 거리 했을 때 공원 경계를 벗어나는지, 이동 중 장애물을 만나는지 체크하고 둘중 하나라고 해당되면 continue
//3. 이동 완료 후 로봇의 위치(answer)갱신