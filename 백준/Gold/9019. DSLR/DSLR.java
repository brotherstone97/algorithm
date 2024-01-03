/*
A와 레지스터에 저장된 값을 누산한다. 이 때 DFS로 풀 경우 결과값 B를 찾기 위해 depth가 무한히 깊어질 수 있으므로 BFS를 이용해 최단거리로 결과값 B를 구한다.
이 때 최적화를 위해 visited 배열을 사용하는데 현재 값과 레지스터의 값을 연산한 값이 이미 도출된 적 있는 값이면 continue한다.
*/

import java.util.*;
import java.io.*;

class Main {
    static class Register {
        int cnt;
        StringBuilder cmd;
        int current;

        public Register(int cnt, StringBuilder cmd, int current) {
            this.cnt = cnt;
            this.cmd = cmd;
            this.current = current;
        }
    }

    private static int idx = 0;
    private static String[] cmds;
    private static final String[] cmdMapper = {"D", "S", "L", "R"};
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        cmds = new String[T];

        for (int i = 0; i < T; i++) {
            String[] input = br.readLine().split(" ");
            int A = Integer.parseInt(input[0]);
            int B = Integer.parseInt(input[1]);
            visited = new boolean[10000];

            bfs(A, B);
            idx++;
        }

        Arrays.stream(cmds).forEach(System.out::println);
    }

    private static void bfs(int A, int answer) {
        Queue<Register> q = new LinkedList<>();
        q.offer(new Register(0, new StringBuilder(), A));

        while (!q.isEmpty()) {
            Register polled = q.poll();

            for (int i = 0; i < 4; i++) {
                int calculatedCurrent = calc(polled.current, i);

                if (visited[calculatedCurrent]) {
                    continue;
                }
                visited[calculatedCurrent] = true;

                //시간 복잡도 개선(q.poll시 검증 -> enqueue전 검증)
                if (calculatedCurrent == answer) {
                    cmds[idx] = polled.cmd.append(cmdMapper[i]).toString();
                    return;
                }

                q.offer(new Register(polled.cnt + 1,
                        new StringBuilder(polled.cmd.toString()).append(cmdMapper[i]),
                        calculatedCurrent));
            }
        }
    }

    private static int calc(int current, int flag) {
        //D
        if (flag == 0) {
            return current * 2 % 10000;
        }
        //S
        if (flag == 1) {
            if (current == 0) {
                return 9999;
            }
            return current - 1;
        }
        int d1 = current / 1000;
        int d2 = (current - d1 * 1000) / 100;
        int d3 = (current - d1 * 1000 - d2 * 100) / 10;
        int d4 = current % 10;

        //L
        if (flag == 2) {
            int res = current - d1 * 1000;
            return res * 10 + d1;
        }
        //R
        return d4 * 1000 + d1 * 100 + d2 * 10 + d3;
    }
}
