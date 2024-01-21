import java.util.*;
import java.io.*;

class Main {
    private static int currentA;
    private static int currentB;
    private static boolean[] visited;
    private static final String[] cmdMapper = {"D", "S", "L", "R"};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String[] input = br.readLine().split(" ");
            currentA = Integer.parseInt(input[0]);
            currentB = Integer.parseInt(input[1]);
            visited = new boolean[10000];

            System.out.println(bfs());
        }

    }

    private static String bfs() {
        Queue<Command> q = new LinkedList<>();
        q.offer(new Command(new StringBuilder(), currentA));

        while (!q.isEmpty()) {
            Command polled = q.poll();

            for (int i = 0; i < 4; i++) {
                int res = calc(polled.register, i);
                if (res == currentB) {
                    return new StringBuilder(polled.cmd).append(cmdMapper[i]).toString();
                }
                if (visited[res]) {
                    continue;
                }
                visited[res] = true;
                q.offer(new Command(new StringBuilder(polled.cmd).append(cmdMapper[i]), res));
            }
        }
        return null;
    }

    private static int calc(int num, int i) {
        //D
        if (i == 0) {
            return num * 2 % 10000;
        }
        //S
        if (i == 1) {
            if (num == 0) {
                return 9999;
            }
            return num - 1;
        }
        int d1 = num / 1000;
        int d2 = num % 1000 / 100;
        int d3 = num % 100 / 10;
        int d4 = num % 10;
        //L
        if (i == 2) {
            return d2 * 1000 + d3 * 100 + d4 * 10 + d1;
        }
        //R
        return d4 * 1000 + d1 * 100 + d2 * 10 + d3;
    }

    static class Command {
        StringBuilder cmd;
        int register;

        public Command(StringBuilder cmd, int register) {
            this.cmd = cmd;
            this.register = register;
        }
    }
}