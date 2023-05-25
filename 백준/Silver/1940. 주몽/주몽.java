import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int cnt = 0;
        int i = 0;
        int j = N - 1;

        while (i < j) {
            if (arr[i] + arr[j] < M) {
                i++;
                continue;
            }
            if (arr[i] + arr[j] > M) {
                j--;
                continue;
            }
            cnt++;
            i++;
            j--;
        }
        System.out.println(cnt);
    }

}
