import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//hard

class Main {
    static int N;
    static int C;
    static int[] stables;

    public static void main(String[] args) throws IOException {
        //0. 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] NC = br.readLine().split(" ");
        N = Integer.parseInt(NC[0]);
        C = Integer.parseInt(NC[1]);
        stables = new int[N];

        for (int i = 0; i < N; i++) {
            stables[i] = Integer.parseInt(br.readLine());
        }

        //1. 정렬
        Arrays.sort(stables);

        int lt = 1;
        int rt = stables[stables.length - 1];
        int mid = 0;
        int res = 0;

        while (lt <= rt) {
            mid = (lt + rt) / 2;
            if (count(mid) >= C) {
                lt = mid + 1;
                res = mid;
                continue;
            }
            rt = mid - 1;
        }

        System.out.println(res);
    }

    private static int count(int mid) {
        int prev = 0;
        int cnt = 1;
        for (int i = 1; i < stables.length; i++) {
            if (stables[i] - stables[prev] >= mid) {
                cnt++;
                prev = i;
            }
        }
        return cnt;
    }

}
