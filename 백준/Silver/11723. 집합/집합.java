import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());

        Set<Integer> S = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();

            if (cmd.equals("all")) {
                List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
                S = new HashSet<>(list);
                continue;
            }
            if (cmd.equals("empty")) {
                S.clear();
                continue;
            }
            int num = Integer.parseInt(st.nextToken());
            
            if (cmd.equals("add")) {
                S.add(num);
                continue;
            }
            if (cmd.equals("remove")) {
                S.remove(num);
                continue;
            }
            if (cmd.equals("check")) {
                if (S.contains(num)) {
                    sb.append("1\n");
                } else {
                    sb.append("0\n");
                }
                continue;
            }
            if (S.contains(num)) {
                S.remove(num);
            } else {
                S.add(num);
            }
        }
        System.out.print(sb.toString());
    }
}

