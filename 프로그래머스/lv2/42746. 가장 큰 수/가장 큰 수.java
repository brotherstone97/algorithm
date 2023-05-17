import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        //1. 문자열 배열로 변환
        String[] str = new String[numbers.length];
        for(int i=0; i<numbers.length; i++){
            str[i] = String.valueOf(numbers[i]);
        }
        Arrays.sort(str, (o1, o2)->{
            String c1 = o1+o2;
            String c2 = o2+o1;
            if(Integer.parseInt(c1) > Integer.parseInt(c2)){
                return -1;
            }else if(Integer.parseInt(c1) < Integer.parseInt(c2)){
                return 1;
            }
            return 0;
        });
        if(str[0].equals("0")){
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for(String s : str){
            sb.append(s);
        }
                    
        return sb.toString();
    }
}
