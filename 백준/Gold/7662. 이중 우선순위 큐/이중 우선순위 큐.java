import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());
        for (int i = 0; i < tc; i++) {
            int N = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> map = new TreeMap<>();
            for (int j = 0; j < N; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String cmd = st.nextToken();
                int num = Integer.parseInt(st.nextToken());

                if (cmd.equals("I")) {
                    map.put(num, map.getOrDefault(num, 0) + 1);
                    continue;
                }
                if (map.isEmpty()) {
                    continue;
                }
                // TreeMap이 제공하는 firstKey()와 lastKey() 이용
                int n = (num == 1) ? map.lastKey() : map.firstKey();
                if (map.put(n, map.get(n) - 1) == 1) {  // 해당 정수의 개수를 -1 해주면서 만약 0개가 된다면 삭제
                    map.remove(n);
                }


            }
            if (map.size() == 0) {
                sb.append("EMPTY\n");
                continue;
            }
            sb.append(map.lastKey()).append(" ").append(map.firstKey()).append("\n");
        }
        System.out.println(sb.toString());
    }
}