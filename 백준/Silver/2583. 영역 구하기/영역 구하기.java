import java.util.*;
import java.io.*;

class Main {
    private static final List<Integer> areas = new ArrayList<>();
    private static final int[] dy = {-1, 0, 1, 0};
    private static final int[] dx = {0, 1, 0, -1};

    private static boolean[][] grid;
    private static int cnt;
    private static int M;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        M = Integer.parseInt(input[0]);
        N = Integer.parseInt(input[1]);
        int K = Integer.parseInt(input[2]);

        grid = new boolean[M][N];

        for (int i = 0; i < K; i++) {
            String[] coord = br.readLine().split(" ");
            //입력의 좌표는 x,y꼴
            int[] leftDown = {Integer.parseInt(coord[0]), Integer.parseInt(coord[1])};
            int[] rightUp = {Integer.parseInt(coord[2]), Integer.parseInt(coord[3])};
            //내 좌표는 y,x꼴 == row,col
            int[] widthStart = {M - leftDown[1] - 1, leftDown[0]};
            int[] heightStart = {M - rightUp[1], rightUp[0] - 1};
            int width = Math.abs(leftDown[0] - rightUp[0]);
            int height = Math.abs(leftDown[1] - rightUp[1]);

            fillRect(widthStart, width, heightStart, height);

        }
        search();

        System.out.println(cnt);
        areas.stream().sorted().forEach(e -> System.out.printf("%d ", e));
    }

    private static void search() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!grid[i][j]) {
                    grid[i][j] = true;
                    cnt++;
                    areas.add(bfs(i, j));
                }
            }
        }
    }

    private static void fillRect(int[] widthStart, int width, int[] heightStart, int height) {
        //사각형 가로 채우기
        for (int w = widthStart[1]; w < widthStart[1] + width; w++) {
            grid[widthStart[0]][w] = true;
        }
        //사각형 세로 채우기
        for (int h = heightStart[0]; h < heightStart[0] + height; h++) {
            for (int w = widthStart[1]; w < widthStart[1] + width; w++) {
                grid[h][w] = true;
            }
        }
    }

    private static int bfs(int startY, int startX) {
        int area = 0;
        Queue<EmptyArea> q = new LinkedList<>();
        q.offer(new EmptyArea(startY, startX));

        while (!q.isEmpty()) {
            EmptyArea e = q.poll();
            area++;

            for (int k = 0; k < 4; k++) {
                int _y = e.y + dy[k];
                int _x = e.x + dx[k];

                if (isValidCoord(_y, _x) && !grid[_y][_x]) {
                    q.offer(new EmptyArea(_y, _x));
                    grid[_y][_x] = true;
                }
            }
        }
        return area;
    }

    private static boolean isValidCoord(int y, int x) {
        return 0 <= y && y < M && 0 <= x && x < N;
    }

    static class EmptyArea {
        int y, x;

        EmptyArea(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}