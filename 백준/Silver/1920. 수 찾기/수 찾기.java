import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.io.BufferedReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        Set<Integer> hashSet = new HashSet<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        while (st.hasMoreTokens()) {
            String s = st.nextToken();
//            System.out.println(s);
            hashSet.add(Integer.parseInt(s));
        }

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int s = Integer.parseInt(st.nextToken());
//            System.out.println(s);
            boolean isContain = hashSet.contains(s);
            if(isContain){
                System.out.println(1);
            }else{
                System.out.println(0);
            }
        }

//
//        hashSet.add()
    }
}
