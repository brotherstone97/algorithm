import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        //1.
        int[][] book_time_int = toIntArr(book_time);
        //2.
        sortBookTime(book_time_int);
        //3.
        answer=calcMinRoom(book_time_int);
        return answer;
    }
    
    //1. StringArr -> intArr. 이 때 퇴실시간 +10해서 저장하되, 더한 값이 60분 이상이라면, +100 -60
    private int[][] toIntArr(String[][] arr){
        int size = arr.length;
        int[][] newArr = new int[size][2];

        for(int i=0; i<size; i++){
            newArr[i][0] = Integer.parseInt(arr[i][0].replace(":",""));
            newArr[i][1] = Integer.parseInt(arr[i][1].replace(":",""))+10;
            if(newArr[i][1]%100>=60){
                newArr[i][1]+=(100-60);
            }
        }

        return newArr;
    }   
    
    //2. 입실시간 기준 오름차순 정렬. 이 때, 두 요소의 입실시간이 같다면 다음 기준은 퇴실시간으로.
    private void sortBookTime(int[][] arr){
        Arrays.sort(arr, (a,b)->{
            if(a[0] > b[0]){
                return 1;
            } 
            else if(a[0]<b[0]){
              return -1;  
            } 
            else{
                if(a[1]>b[1]){
                    return 1;
                }else{
                    return -1;
                }
            }
        });
    }
    
    //3. PriorityQueue사용, 요소는 퇴실시간+10으로 한다. 모든 예약타임을 pq의 peek와 비교하며 현재 입실시간이 peek보다 크거나 같다면 pq.poll() 및 add/ 반대라면 only add
    private int calcMinRoom(int[][] arr){
        PriorityQueue<Integer> occupied = new PriorityQueue<>();
        for(int[] time : arr){
            if(occupied.size()==0){
                occupied.add(time[1]);
                continue;
            }
            int earliestCheckout=occupied.peek();
            if(time[0] >= earliestCheckout){
                occupied.poll();
            }
            occupied.add(time[1]);
        }
        return occupied.size();
    }
    
}



