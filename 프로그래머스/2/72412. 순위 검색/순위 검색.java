import java.util.*;

class Solution {
    private Map<String, List<Integer>> map = new HashMap<>();
    
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        //1. info를 split
        for(int i=0; i<info.length; i++){
            String[] splitedInfo = info[i].split(" ");
            makeSentence(splitedInfo, "", 0);
        }
        
        //3. value를 오름차순으로 정렬(점수가 복수개일 때 이분탐색하기 위함)
        for(String key : map.keySet()){
            Collections.sort(map.get(key));
        }
        
        //4. 쿼리를 순회하며 조건과 점수를 배열의 한 요소로 만듦
        for(int i=0; i<query.length; i++){
            //쿼리의 키를 map의 키 형식으로 바꿈
            query[i] = query[i].replace(" and ", "");
            String[] splitedQuery = query[i].split(" ");
            
            //검색결과가 존재하면 그 키의 value를 이분탐색해서 쿼리에 입력된 점수 이상이 몇개인지 계산 후 저장
            answer[i] = map.containsKey(splitedQuery[0]) ? binarySearch(splitedQuery[0],Integer.parseInt(splitedQuery[1])) : 0;
        }
        
        return answer;
    }
    
    //4. map의 key의 value에 해당하는 리스트를 이분탐색
    private int binarySearch(String key, int score){
        List<Integer> values = map.get(key);
        int lt = 0, rt = values.size() - 1;
        
        while(lt<=rt){
            int mid = (lt + rt)/2;
            
            if(values.get(mid) < score){
                lt = mid + 1;
                continue;
            }
            rt = mid - 1;
        }
        return values.size() - lt;
    }
    
    //2. 주어진 info를 이용해 각 요소에서 나올 수 있는 쿼리의 경우의 수를 모두 구해 map에 저장.
    //ex: key(java backend junior pizza 150) -> javabackendjuniorpizza: [150]
    //-backendjuniorpizza:[150] ....
    private void makeSentence(String[] splited, String key, int cnt){
        if(cnt==4){
            if(!map.containsKey(key)){
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(Integer.parseInt(splited[4]));
            return;
        }
        makeSentence(splited, key + "-", cnt+1);
        makeSentence(splited, key + splited[cnt], cnt+1);
    }
}