import java.io.*;
import java.util.*;

public class Main {
    static int[][] arr;
    static int[] answers = new int[3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 1) 입력 받아 2차원 배열에 삽입
        int N = Integer.parseInt(br.readLine());

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dq(0, 0, N);

        StringBuilder sb = new StringBuilder();

        Arrays.stream(answers).forEach(ans -> sb.append(ans).append("\n"));
        System.out.println(sb);
    }


    private static void dq(int rowStart, int colStart, int size) {
        if (size == 1) {
            calcAnswer(rowStart, colStart);
            return;
        }

        if (check(rowStart, colStart, size)) {
            calcAnswer(rowStart, colStart);
            return;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                dq(rowStart + (size / 3) * i, colStart + (size / 3) * j, size / 3);
            }
        }
    }

    private static boolean check(int rowStart, int colStart, int size) {
        for (int i = rowStart; i < rowStart + size; i++) {
            for (int j = colStart; j < colStart + size; j++) {
                if (arr[i][j] != arr[rowStart][colStart]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void calcAnswer(int rowStart, int colStart) {
        if (arr[rowStart][colStart] == -1) {
            answers[0]++;
            return;
        }
        if (arr[rowStart][colStart] == 0) {
            answers[1]++;
            return;
        }
        answers[2]++;
    }
}