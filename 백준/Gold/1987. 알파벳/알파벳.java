import java.io.*;

class Main {
    private static final boolean[] visited = new boolean[26];
    private static char[][] grid;
    private static int R;
    private static int C;
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] RC = br.readLine().split(" ");
        R = Integer.parseInt(RC[0]);
        C = Integer.parseInt(RC[1]);

        grid = new char[R][C];

        for (int i = 0; i < R; i++) {
            grid[i] = br.readLine().toCharArray();
        }

        visited[grid[0][0]-65]=true;
        dfs(0, 0, 1);
        System.out.println(answer);
    }

    private static void dfs(int row, int col, int depth) {
        answer = Math.max(answer, depth);
        //상
        if (isValidCoord(row - 1, col)) {
            int idx = grid[row - 1][col] - 65;
            if (!visited[idx]) {
                visited[idx] = true;
                dfs(row - 1, col, depth + 1);
                visited[idx] = false;
            }
        }
        //하
        if (isValidCoord(row + 1, col)) {
            int idx = grid[row + 1][col] - 65;
            if (!visited[idx]) {
                visited[idx] = true;
                dfs(row + 1, col, depth + 1);
                visited[idx] = false;
            }
        }
        //좌
        if (isValidCoord(row, col - 1)) {
            int idx = grid[row][col - 1] - 65;
            if (!visited[idx]) {
                visited[idx] = true;
                dfs(row, col - 1, depth + 1);
                visited[idx] = false;
            }
        }
        //우
        if (isValidCoord(row, col + 1)) {
            int idx = grid[row][col + 1] - 65;
            if (!visited[idx]) {
                visited[idx] = true;
                dfs(row, col + 1, depth + 1);
                visited[idx] = false;
            }
        }
    }

    private static boolean isValidCoord(int y, int x) {
        return 0 <= y && y < R && 0 <= x && x < C;
    }
}