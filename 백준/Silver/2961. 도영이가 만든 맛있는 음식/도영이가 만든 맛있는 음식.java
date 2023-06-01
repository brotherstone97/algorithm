import java.util.*;
import java.io.*;

public class Main {
    private static int N;
    private static int[] s_arr;
    private static int[] b_arr;
    private static int mul = 1;
    private static int sum = 0;
    private static int _min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        s_arr = new int[N];
        b_arr = new int[N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int sour = Integer.parseInt(st.nextToken());
            int bitter = Integer.parseInt(st.nextToken());
            s_arr[i] = sour;
            b_arr[i] = bitter;
        }
        dfs(0);
        System.out.println(_min);
    }

    private static void dfs(int d) {
        if (d >= N) {
            //재료를 하나도 사용안한 경우
            if (sum == 0) {
                return;
            }
            _min = Math.min(_min, Math.abs(mul - sum));
            return;
        }
        mul *= s_arr[d];
        sum += b_arr[d];
        dfs(d + 1);
        mul /= s_arr[d];
        sum -= b_arr[d];
        dfs(d + 1);
    }
}