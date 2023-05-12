import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for(int n=0; n<commands.length; n++){
            int i= commands[n][0];
            int j= commands[n][1];
            int k = commands[n][2];
            int[] arr = new int[j-i+1];
            arr = Arrays.copyOfRange(array, i-1, j);
            System.out.println(Arrays.toString(arr));
            Arrays.sort(arr);
            answer[n]=arr[k-1];
            System.out.println(answer[n]);
            
        }
        return answer;
    }
}