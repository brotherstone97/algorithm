import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        Stack st = new Stack();
        boolean flag;

        for (int i = 0; i < tc; i++) {
            st.clear();
            flag = false;
            String line = br.readLine();
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == '(') {
                    st.push('(');
                } else {
                    if (st.isEmpty()) {
                        System.out.println("NO");
                        flag = true;
                        break;
                    }
                    st.pop();
                }
            }
            if (st.isEmpty() && !flag) {
                System.out.println("YES");
                continue;
            }
            if (!st.isEmpty() && !flag) {
                System.out.println("NO");
            }
        }
    }
}
