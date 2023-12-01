import java.util.*;
import java.util.stream.*;

class Solution {
    private List<String> answer = new ArrayList<>();
    private int[] max;
    private Map<String, Integer> menu = new HashMap<>();
    
    public String[] solution(String[] orders, int[] course) {
        max = new int[course.length];
        
        //1. orders내 알파벳 정렬
        for(int i=0; i<orders.length; i++){
            orders[i] = sortOrder(orders[i]);
        }
        
        //2. order요소별 조합 구해 Map에 저장
        for(int c:course){
            for(int i=0; i<orders.length; i++){
                combi(orders[i], new boolean[orders[i].length()], 0, orders[i].length(), c);
            }
        }
        
        for(int i=0; i<course.length; i++){
            final int _i=i;
            //3. 길이가 course[i]인 menu중 max를 구함
            menu.entrySet().stream()
                .filter(menu->menu.getKey().length()==course[_i])
                .forEach(filteredMenu -> {
                    max[_i]=Math.max(max[_i], filteredMenu.getValue());
                });
            
            //4. 길이가 course[i]인 menu중 value==max(가장 많이 주문받은)인 값에 대해 그 key를 List의 저장. 단 1명만 주문한 메뉴는 제외
            menu.entrySet().stream()
                .filter(menu->menu.getKey().length()==course[_i])
                .forEach(filteredMenu -> {
                            if(filteredMenu.getValue()>1&&filteredMenu.getValue()==max[_i]){
                            answer.add(filteredMenu.getKey());
                           }
                });            
        }
        
        //5. course별 최대 주문 메뉴를 저장한 list를 정렬한 뒤 배열로 반환
        return answer.stream().sorted().toArray(String[]::new);
    }
    
    private void combi(String order, boolean[] visited, int start, int n, int r){
        if(r==0){
            saveCombi(order, visited, n);
            return;
        }
        
        for(int i=start; i<n; i++){
            visited[i]=true;
            combi(order, visited, i+1, n, r-1);
            visited[i]=false;
        }
    }
    
    private void saveCombi(String order, boolean[] visited, int n){
        StringBuilder temp = new StringBuilder();
        
        for(int i=0; i<n; i++){
            if(visited[i]){
                temp.append(order.charAt(i));
            }
        }
        menu.put(temp.toString(),menu.getOrDefault(temp.toString(), 0)+1);
    }
    
    private String sortOrder(String order){
        char[] charArr = order.toCharArray();
        Arrays.sort(charArr);
        return String.valueOf(charArr);
    }
}