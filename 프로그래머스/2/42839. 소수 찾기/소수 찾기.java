import java.util.*;

class Solution {
    private Set<Integer> set = new HashSet<>();
    
    public int solution(String numbers) {
        for(int i=1; i<=numbers.length(); i++){
            dfs(1, new boolean[numbers.length()], i, "", numbers);
        }
        
        return set.size();
    }
    
    private void dfs(int depth, boolean[] visited, int maxDepth, String str, String numbers){
        if(depth>maxDepth){
            int number = Integer.parseInt(str);
            if(isPrimeNumber(number)){
                set.add(number);
            }
            
            return;
        }
        
        for(int i=0; i<numbers.length(); i++){
            if(!visited[i]){
                visited[i]=true;
                dfs(depth+1, visited, maxDepth, str+numbers.charAt(i), numbers);
                visited[i]=false;
                
            }
        }
    }
    
    private boolean isPrimeNumber(int num){
    if(num<=1){
        return false;
    }
    if(num==2){
        return true;
    }
        
    for(int i=2; i<num; i++){
        if(num%i==0){
            return false;
        }
    }

    return true;
}
}