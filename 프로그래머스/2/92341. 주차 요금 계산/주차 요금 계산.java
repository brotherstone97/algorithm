import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {        
        int[] answer = {};
        Map<String, String> entranceTime = new HashMap<>();
        Map<String, Integer> feeOfNumber = new HashMap<>();
        Map<String, Integer> accTime = new HashMap<>();
        
        for(String record: records){
            StringTokenizer st = new StringTokenizer(record);
            String time = st.nextToken();
            String number = st.nextToken();
            String inOut = st.nextToken();
            
            if(inOut.equals("IN")){
                entranceTime.put(number, time);
                continue;
            }
            
            int diff = calcTimeDiff(entranceTime.get(number), time);
            accTime.put(number, accTime.getOrDefault(number, 0)+diff);
            
            entranceTime.remove(number);
        }
        
        if(!entranceTime.isEmpty()){
            System.out.println(entranceTime);
            for(Map.Entry<String, String> entry : entranceTime.entrySet()){
                String number = entry.getKey();
                String entryTime = entry.getValue();
                
                int diff = calcTimeDiff(entryTime, "23:59");
                // entranceTime.remove(number);
                
                accTime.put(number, accTime.getOrDefault(number,0)+diff);
            }
        }
        for(Map.Entry<String, Integer> entry: accTime.entrySet()){
            String number = entry.getKey();
            int time = entry.getValue();
            
            if(time <= fees[0]){
                feeOfNumber.put(number, fees[1]);
                continue;
            }
            feeOfNumber.put(number, fees[1] + (int)(Math.ceil((double)(time-fees[0])/fees[2]))*fees[3]);
        }
        
        List<Map.Entry<String,Integer>> entryList = new ArrayList<>(feeOfNumber.entrySet());
        Collections.sort(entryList, (e1, e2) -> 
                         Integer.parseInt(e1.getKey())-Integer.parseInt(e2.getKey()));
        answer = new int[entryList.size()];
        for(int i=0; i<entryList.size(); i++){
            answer[i] = entryList.get(i).getValue();
        }
        return answer;
    }
    
    private int calcMinute(String time){
        StringTokenizer token = new StringTokenizer(time, ":");
        int hour = Integer.parseInt(token.nextToken());
        int minute = Integer.parseInt(token.nextToken());
        
        return (hour*60) + minute;
    }
    
    private int calcTimeDiff(String entranceTime, String exitTime){
        return calcMinute(exitTime)-calcMinute(entranceTime);
    }
}