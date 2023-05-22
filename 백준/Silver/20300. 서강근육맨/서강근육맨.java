import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        long[] arr = new long[N];
        for (int i = 0; i < N; i++) {
            long e = Long.parseLong(st.nextToken());
            arr[i] = e;
        }

        long M = 0;

        Arrays.sort(arr);

        if (N % 2 == 0) {
            for (int i = 0; i < N / 2; i++) {
                long loss = arr[i] + arr[N - i - 1];
                M = Math.max(M, loss);
            }
        } else {
            M = Math.max(M, arr[N - 1]);
            for (int i = 0; i < N / 2; i++) {
                long loss = arr[i] + arr[N - i - 2];
                M = Math.max(M, loss);
            }
        }
        System.out.println(M);
    }
}