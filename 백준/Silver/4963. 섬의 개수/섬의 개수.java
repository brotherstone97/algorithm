import java.util.*;
import java.io.*;

class Main {
    private static boolean[][] grid;
    private static boolean[][] visited;
    private static int[] currentSize;
    private static final int[] dy = {1, 0, -1, 0};
    private static final int[] dx = {0, 1, 0, -1};

    private static final int[] crossY = {-1, -1, 1, 1};
    private static final int[] crossX = {-1, 1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            int localAnswer = 0;
            String[] size = br.readLine().split(" ");

            currentSize = new int[2];

            int row = Integer.parseInt(size[1]);
            int col = Integer.parseInt(size[0]);
            currentSize[0] = row;
            currentSize[1] = col;

            if (col == 0 && row == 0) {
                break;
            }

            grid = new boolean[row][col];
            visited = new boolean[row][col];

            //fill grid
            for (int r = 0; r < row; r++) {
                String[] input = br.readLine().split(" ");
                for (int c = 0; c < col; c++) {
                    if (input[c].equals("0")) {
                        continue;
                    }
                    grid[r][c] = true;
                }
            }

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (grid[i][j] && !visited[i][j]) {
                        bfs(i, j);
                        localAnswer++;
                    }
                }
            }
            System.out.println(localAnswer);
        }
    }

    private static void bfs(int startY, int startX) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{startY, startX});

        while (!q.isEmpty()) {
            int[] polled = q.poll();
            int y = polled[0];
            int x = polled[1];

            for (int k = 0; k < 4; k++) {
                int _y = y + dy[k];
                int _x = x + dx[k];
                if (isValidCoord(_y, _x) && !visited[_y][_x] && grid[_y][_x]) {
                    visited[_y][_x] = true;
                    q.offer(new int[]{_y, _x});
                }

                int cY = y + crossY[k];
                int cX = x + crossX[k];
                if (isValidCoord(cY, cX) && !visited[cY][cX] && grid[cY][cX]) {
                    visited[cY][cX] = true;
                    q.offer(new int[]{cY, cX});
                }
            }
        }
    }

    private static boolean isValidCoord(int y, int x) {
        return 0 <= y && y < currentSize[0] && 0 <= x && x < currentSize[1];
    }
}