import java.util.*;
import java.io.*;

class Main {
    private static final int[] dy = {1, 0, -1, 0};
    private static final int[] dx = {0, 1, 0, -1};
    private static boolean[][] grid;
    private static boolean[][] visited;
    private static int currentRowSize;
    private static int currentColSize;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String[] input = br.readLine().split(" ");
            int rowSize = Integer.parseInt(input[1]);
            int colSize = Integer.parseInt(input[0]);
            int K = Integer.parseInt(input[2]);

            grid = new boolean[rowSize][colSize];
            visited = new boolean[rowSize][colSize];
            currentRowSize=rowSize;
            currentColSize=colSize;

            for (int j = 0; j < K; j++) {
                String[] loc = br.readLine().split(" ");
                int row = Integer.parseInt(loc[1]);
                int col = Integer.parseInt(loc[0]);

                grid[row][col] = true;
            }
            System.out.println(cntCabbage());
        }
    }

    private static int cntCabbage() {
        int cnt = 0;
        for (int i = 0; i < currentRowSize; i++) {
            for (int j = 0; j < currentColSize; j++) {
                if (grid[i][j]&&!visited[i][j]) {
                    visited[i][j] = true;
                    bfs(i, j);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static void bfs(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r, c});

        while (!q.isEmpty()) {
            int[] polled = q.poll();
            int y = polled[0];
            int x = polled[1];

            for (int k = 0; k < 4; k++) {
                int _y = y + dy[k];
                int _x = x + dx[k];

                if (isValidCoord(_y, _x) && !visited[_y][_x] && grid[_y][_x]) {
                    q.offer(new int[]{_y, _x});
                    visited[_y][_x] = true;
                }
            }
        }
    }

    private static boolean isValidCoord(int y, int x) {
        return 0 <= y && y < currentRowSize && 0 <= x && x < currentColSize;
    }
}