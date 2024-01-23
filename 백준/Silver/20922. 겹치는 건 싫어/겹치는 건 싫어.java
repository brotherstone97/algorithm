import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NK = br.readLine().split(" ");
        int N = Integer.parseInt(NK[0]);
        int K = Integer.parseInt(NK[1]);
        int answer = 0;

        String[] seqStr = br.readLine().split(" ");
        int[] seq = new int[N];

        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(seqStr[i]);
        }

        int s = 0;
        int e = 0;

        Map<Integer, Integer> map = new HashMap<>();

        while (s <= e && e < N) {
            if (map.getOrDefault(seq[e], 0) < K) {
                map.put(seq[e], map.getOrDefault(seq[e], 0) + 1); //증가
                answer = Math.max(answer, e - s + 1);
                e++;
                continue;
            }
            map.put(seq[s], map.get(seq[s]) - 1); //감소
            s++;
        }

        System.out.println(answer);
    }
}