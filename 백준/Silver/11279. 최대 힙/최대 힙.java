
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(br.readLine());
            if (input > 0) {
                pq.offer(-input);
                continue;
            }
            if (pq.isEmpty()) {
                System.out.println(0);
                continue;
            }
            System.out.println(-pq.poll());
        }
    }
}