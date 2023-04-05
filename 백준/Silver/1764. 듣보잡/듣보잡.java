import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        TreeSet<String> hear = new TreeSet<>();
        TreeSet<String> see = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            hear.add(br.readLine());
        }
        for (int i = 0; i < m; i++) {
            see.add(br.readLine());
        }
        hear.retainAll(see);
        System.out.println(hear.size());

        while (!hear.isEmpty()){
            String element = hear.pollFirst();
            System.out.println(element);
        }
    }
}
