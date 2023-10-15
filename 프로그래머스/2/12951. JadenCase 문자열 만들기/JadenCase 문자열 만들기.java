import java.util.StringTokenizer;

class Solution {
    public String solution(String s) {
        return toJaden(s);
    }
    
    private String toJaden(String s){
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(s," ", true);
        
        while(st.hasMoreTokens()){
            String tokened = st.nextToken();
            System.out.println(tokened);
            char[] token = tokened.toCharArray();
            
            for(int i=0; i<token.length; i++){
                if(i==0&&Character.isAlphabetic(token[i])){
                    token[i]=Character.toUpperCase(token[i]);
                    continue;
                }
                token[i]=Character.toLowerCase(token[i]);
            }
            sb.append(token);
        }
        
        return sb.toString();
    }
}