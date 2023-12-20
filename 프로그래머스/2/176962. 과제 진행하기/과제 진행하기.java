import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        int idx = 0;
        int freeTime=0;
        
        Homework[] homeworks = new Homework[plans.length];
        
        //1. Homework타입 배열에 삽입
        for(int i=0; i<plans.length; i++){
            String[] plan = plans[i];
            homeworks[i]=new Homework(plan[0], convertMinute(plan[1]),Integer.parseInt(plan[2]));
        }
        
        //2. 정렬
        Arrays.sort(homeworks);
        
        //3. stack생성
        Stack<Homework> progress = new Stack<>();
        Stack<Homework> pause = new Stack<>();
        
        Arrays.stream(homeworks).forEach(h->progress.push(h));
        
        //4.로직 수행
        while(progress.size()>1){
            Homework popped = progress.pop();
            if(popped.startTime + popped.restTime > progress.peek().startTime){
                popped.restTime -= (progress.peek().startTime - popped.startTime);
                pause.push(popped);
            }else{
                answer[idx++] = popped.subject;
                freeTime = progress.peek().startTime - (popped.startTime + popped.restTime);
                while(!pause.isEmpty() && freeTime>0){
                    if(pause.peek().restTime > freeTime){
                        pause.peek().restTime -= freeTime;
                        freeTime=0;
                        break;
                    }
                    else{
                        Homework poppedHomework = pause.pop();
                        answer[idx++] = poppedHomework.subject;
                        freeTime-=poppedHomework.restTime;
                    }
                }
            }
        }
        
        if(!progress.isEmpty()){
            while(!pause.isEmpty() && freeTime>0){
                if(pause.peek().restTime > freeTime){
                    break;
                }
                Homework poppedHomework = pause.pop();
                answer[idx++] = poppedHomework.subject;
                freeTime-=poppedHomework.restTime;
            }
            
            answer[idx++]=progress.pop().subject;
            
            while(!pause.isEmpty()){
                answer[idx++] = pause.pop().subject;
            }
        }
        
        return answer;
    }
    
    private int convertMinute(String time){
        String[] t = time.split(":");
        return Integer.parseInt(t[0])*60+Integer.parseInt(t[1]);
    }
    
    class Homework implements Comparable<Homework>{
        String subject;
        int startTime;
        int restTime;
        
        public Homework(String subject, int startTime, int restTime){
            this.subject = subject;
            this.startTime = startTime;
            this.restTime = restTime;
        }
        
        //내림차순
        @Override
        public int compareTo(Homework o){
            return o.startTime-this.startTime;
        }
    }
}