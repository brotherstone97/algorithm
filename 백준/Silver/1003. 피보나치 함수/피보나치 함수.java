import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        for (int i = 0; i < tc; i++) {
            int N = Integer.parseInt(br.readLine());
            int[] fiboResult = new int[41];
            int[][] cnt = new int[41][2];
            //초기값
            cnt[0][0] = 1;
            cnt[0][1] = 0;
            cnt[1][0] = 0;
            cnt[1][1] = 1;


            fiboResult[0] = 0;
            fiboResult[1] = 1;

            if (N < 2) {
                System.out.println(cnt[N][0] + " " + cnt[N][1]);
                continue;
            }
            //bottom-up
            for (int j = 2; j <= N; j++) {
                fiboResult[j] = fiboResult[j - 2] + fiboResult[j - 1];
                cnt[j][0] = cnt[j - 2][0] + cnt[j - 1][0];
                cnt[j][1] = cnt[j - 2][1] + cnt[j - 1][1];
            }
            System.out.println(cnt[N][0] + " " + cnt[N][1]);
        }
    }
}

