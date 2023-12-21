import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        int answer=0;
        Stack<Character> st = new Stack<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        for(int i=0; i<input.length(); i++){
            char current = input.charAt(i);
            if(current=='('){
                st.push(current);
                continue;
            }

            st.pop();
            //laser
            if(input.charAt(i-1)=='('){
                answer+=st.size();
                continue;
            }
            answer++;
        }

        System.out.println(answer);
    }
}