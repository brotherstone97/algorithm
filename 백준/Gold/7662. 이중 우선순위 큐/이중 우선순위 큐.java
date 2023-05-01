import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        for (int i = 0; i < tc; i++) {
            int N = Integer.parseInt(br.readLine());
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            Map<Integer, Integer> map = new HashMap<>();

            for (int j = 0; j < N; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String cmd = st.nextToken();
                int num = Integer.parseInt(st.nextToken());
                if (cmd.equals("I")) {
                    minHeap.offer(num);
                    maxHeap.offer(num);
                    map.put(num, map.getOrDefault(num, 0) + 1);
                    continue;
                }
                if (cmd.equals("D")) {
                    if(map.size()==0){
                        continue;
                    }
                    PriorityQueue<Integer> pq;
                    if (num == -1) {
                        pq = minHeap;
                    } else {
                        pq = maxHeap;
                    }
                    delete(pq, map);
                }
            }
            if (map.size() == 0)
                System.out.println("EMPTY");
            else {
                int n = delete(maxHeap, map);
                System.out.println(n + " " + (map.size() > 0 ? delete(minHeap, map) : n));
            }
        }

    }

    private static int delete(PriorityQueue<Integer> pq, Map<Integer, Integer> map) {
        int num;
        while (true) {
            num = pq.poll();
            int cnt = map.getOrDefault(num, 0);

            if (cnt == 0)
                continue;

            if (cnt == 1)
                map.remove(num);
            else
                map.put(num, cnt - 1);

            break;
        }

        return num;
    }
}
