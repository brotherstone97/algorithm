import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] meeting = new int[n][2];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            int[] cur = new int[2];
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 2; j++) {
                cur[j] = Integer.parseInt(st.nextToken());
            }

            meeting[i] = cur;
        }

        Arrays.sort(meeting, (e1, e2) -> {
            if(e1[1]==e2[1]){
                return e1[0]-e2[0];
            }
            return e1[1] - e2[1];
        });

        int criteria = 0;
        int answer = 1;

        for (int i = 1; i < n; i++) {
            if (meeting[criteria][1] <= meeting[i][0]) {
                criteria = i;
                answer++;
            }
        }

        System.out.println(answer);
    }
}