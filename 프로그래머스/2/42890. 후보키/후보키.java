import java.util.*;

class Solution {
    private List<String> combinations = new ArrayList<>();
    private List<String> candidateKeys = new ArrayList<>();
    
    public int solution(String[][] relation) {
        int columnSize = relation[0].length;
        //1. 컬럼의 모든 조합 구하기
        for(int i=1; i<=columnSize; i++){
            combi(new boolean[columnSize], columnSize, i, 0);
        }
        
        //2. 각 조합의 중복유무를 검증
        for(String e : combinations){
            if(isCandidateKey(e, relation)){
                candidateKeys.add(e);
            }
        }
        
        System.out.println(candidateKeys);
        return candidateKeys.size();
    }
    
    //3. current에서 문자를 하나씩 제거했을 때 한번이라도 최소성을 위배하는지 확인하고 유일성을 충족하는지 확인 
    private boolean isCandidateKey(String current, String[][] relation){
        StringBuilder sb = new StringBuilder(current);
        int len = sb.length();
        
        for(int i=0; i<len; i++){
            char cur = sb.charAt(i);
            sb.deleteCharAt(i);
            //중복이 없다면 최소성을 위배한 것이다.
            if(!hasDistinct(sb.toString().toCharArray(), relation)){
                return false;
            }
            
            sb.insert(i, cur);
        }
        char[] index = current.toCharArray();
        //최소성 검사 후 전체 컬럼을 놓고 봤을 때 중복이 있는지 확인. 없다면 후보키이다.
        return !hasDistinct(sb.toString().toCharArray(), relation);
    }
    
    //주어진 모든 컬럼의 값을 더해 하나의 컬럼으로 만들고 이 중 중복이 있는지 검사
    private boolean hasDistinct(char[] columnArr, String[][] relation){
        String[] temp = new String[relation.length];
        Arrays.fill(temp, "");
        
        for(int i=0; i<relation.length; i++){
            for(int j=0; j<columnArr.length; j++){
                temp[i]+=relation[i][columnArr[j]-'0'];
            }
        }
        
        int cnt = (int)Arrays.stream(temp).distinct().count();
        return cnt < relation.length;
    }
    
    private void combi(boolean[] visited, int n, int r, int start){
        if(r==0){
            saveCombi(visited);
            return;
        }
        
        for(int i=start; i<n; i++){
            visited[i]=true;
            combi(visited, n, r-1, i+1);
            visited[i]=false;
        }
    }
    
    private void saveCombi(boolean[] visited){
        StringBuilder temp = new StringBuilder();
        for(int i=0; i<visited.length; i++){
            if(visited[i]){
                temp.append(i);
            }
        }
        combinations.add(temp.toString());
    }
}