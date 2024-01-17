import java.util.*;
import java.io.*;

class Main {
    private static int[] dy = {-2, -1, 1, 2, 2, 1, -1, -2};
    private static int[] dx = {1, 2, 2, 1, -1, -2, -2, -1};
    private static boolean[][] visited;
    private static int currentL;
    private static int[] currentStart;
    private static int[] currentTarget;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int l = Integer.parseInt(br.readLine());
            String[] start = br.readLine().split(" ");
            String[] target = br.readLine().split(" ");

            visited = new boolean[l][l];
            currentL = l;
            currentStart = new int[]{Integer.parseInt(start[0]), Integer.parseInt(start[1])};
            currentTarget = new int[]{Integer.parseInt(target[0]), Integer.parseInt(target[1])};

            if (currentStart[0] == currentTarget[0] && currentStart[1] == currentTarget[1]) {
                System.out.println(0);
                continue;
            }

            System.out.println(bfs());
        }
    }

    private static int bfs() {
        Queue<Moving> q = new LinkedList<>();
        q.offer(new Moving(currentStart[0], currentStart[1], 0));

        while (!q.isEmpty()) {
            Moving moving = q.poll();

            for (int k = 0; k < 8; k++) {
                int _y = moving.y + dy[k];
                int _x = moving.x + dx[k];

                if (currentTarget[0] == _y && currentTarget[1] == _x) {
                    return moving.cnt + 1;
                }
                if (isValidCoord(_y, _x) && !visited[_y][_x]) {
                    visited[_y][_x] = true;
                    q.offer(new Moving(_y, _x, moving.cnt + 1));
                }
            }
        }
        return -1;
    }

    private static boolean isValidCoord(int y, int x) {
        return 0 <= y && y < currentL && 0 <= x && x < currentL;
    }

    static class Moving {
        int y;
        int x;
        int cnt;

        Moving(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }
}