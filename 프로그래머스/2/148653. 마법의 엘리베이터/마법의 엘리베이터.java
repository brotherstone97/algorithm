//hard
class Solution {
    public int solution(int storey) {
        int answer=0;
        
        while(storey>0){
            int reminder = storey%10;
            if(reminder>5){
                storey+=10-reminder;
                answer+=10-reminder;
            }
            else if(reminder<5){
                storey-=reminder;
                answer+=reminder;
            }
            else{
                //앞자릿수가 있다면
                int quo = storey/10;
                if(quo>0){
                    //앞자릿수가 5 미만이면 뺄셈
                    if((storey/10)%10<5){
                        storey-=reminder;
                    }else{
                        //앞자릿수가 5 이상이면 덧셈
                        storey+=reminder;
                    }
                }else{
                    //storey가 1의 자리이면
                    storey-=reminder;
                }
                answer+=reminder;
            }
            storey/=10;
        }    
        
        return answer;
    }
}