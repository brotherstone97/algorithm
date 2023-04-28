class Solution {
    private int answer=0;
    public int solution(int storey) {
        while(storey!=0){
            System.out.println("함수 호출 전 storey: "+ storey);
            System.out.println("함수 호출 전 answer: "+ answer);
            storey = calcStorey(storey);
            System.out.println("함수 호출 후 storey: "+ storey);
            System.out.println("함수 호출 후 answer: "+ answer);
        }
        return answer;
    }
    //자릿수 계산 하여 갱신된 층수 반환
    private int calcStorey(int storey){
        int div = storey%10;
        if(div<5){
            storey-=div;
            calcAnswer(div);
        }else if(div>5){
            storey+=10-div;
            calcAnswer(10-div);
        }else{
            if(hasToAdd(storey)){
                System.out.println("하잇");
                storey+=div;
            }else{
                storey-=div;
            }
            calcAnswer(div);
        }
        if(storey/10<=0){
            return 0;
        }
        return storey/10;
    }
    //answer누적합
    private void calcAnswer(int num){
        answer+=num;
    }
    
    //현재 자릿수가 5일 때 앞자리를 보고 판단하는 함수
    private boolean hasToAdd(int storey){
        if(storey<10){
            return false;
        }
        if(storey/10%10<5){
            System.out.println(storey/10%10<5);
            return false;
        }
        return true;
    }
}

//자릿수가 5일 때는 앞자리 수를 보고 판단. 앞자리 수가 5이상이면 현재 자릿수를 더하고 5보다 작으면 현재 자릿수를 빼는 게 더 적은 층을 이동할 수 있다.
    
//입력받은 현재 층수에 대해 일의 자릿수를 조사, 5보다 크면 10-일의 자릿수 만큼 더하고, 작으면 뺄셈하여 10의 약수로 맞춘다. 현재 층수의 일의 자릿수부터 조사, 층수를 반올림하고 이동한 칸만큼 answer+=, 다음 자릿수를 조사하기 위해 storey//10. 반복
  
    
//TC
// +4: 20 -> +2 : 0
//4+2=6
    
// +4: 2550 -> +5: 2600 -> +4: 3000 -> +3: 0
// +4: 2550 -> +5 :2500 -> +5: 2000 -> +2: 0
//4+5+4+3 = 16
    
