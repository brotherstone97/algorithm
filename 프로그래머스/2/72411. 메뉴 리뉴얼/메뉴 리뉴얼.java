import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.*;

class Solution {
    private Map<String, Integer> map = new HashMap<>();
    private List<String> list = new ArrayList<>();
    
    public String[] solution(String[] orders, int[] course) {
        for(int c : course){
            calcCombi(orders, c);
        }        
        
        for(int c:course){
            getCourseMenu(c);
        }
        //3. list 오름차순 -> 리턴        
        Collections.sort(list);
        
        return list.toArray(new String[0]);
    }
    
        //1. course의 모든 요소에 해당하는 조합을 구함 ex) key(단품 메뉴 조합): ABC, value(주문한 손님 수):2 이때, 2명 이상의 손님이 주문한 단품만 map.add()
    private void calcCombi(String[] orders, int r){
        for(String order: orders){
            recurse(order, r, new boolean[order.length()],0);
        }
    }
    private void recurse(String str, int r, boolean[] visited, int start){
        if(r==0){
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<str.length(); i++){
                if(visited[i]){
                    sb.append(str.charAt(i));
                }
            }
            char[] charArr = sb.toString().toCharArray();
            Arrays.sort(charArr);
            String key = new String(charArr);
            map.put(key, map.getOrDefault(key,0)+1);
            return;
        }

        for(int i=start; i< str.length(); i++){
            if(visited[i]){
                continue;
            }

            visited[i]=true;
            recurse(str, r-1, visited, i+1);
            visited[i]=false;
        }
    }
    //2. 각 course의 요소에 해당하는 길이를 가진 key 집합을 추출하고 가장 큰 조합들을 선별함 -> max value구한 다음 각 조합의 value가 max-value와 같은지 판단. 이후 해당하는 조합만 list.add()
    private void getCourseMenu(int menuSize){
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        Map<String, Integer> _map = new HashMap<>();
        
        for(Map.Entry<String, Integer> entry: entries){
            if(entry.getKey().length()==menuSize){
                _map.put(entry.getKey(), entry.getValue());
            }
        }
        
        int max = _map.values().stream().mapToInt(e->e).max().orElse(-1);
        List<String> filtered = _map.entrySet()
            .stream()
            .filter(e->e.getValue()==max&&e.getValue()>1)
            .map(e->e.getKey())
            .collect(Collectors.toList());
        
        list.addAll(filtered);
    }
}