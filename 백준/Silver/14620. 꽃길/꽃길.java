import java.util.*;
import java.io.*;

public class Main {
    private static int _min = Integer.MAX_VALUE;
    private static int N;
    private static int[][] grid;
    private static int[] dy = {-1, 0, 1, 0};
    private static int[] dx = {0, -1, 0, 1};
    private static boolean[][] chk;

    public static void main(String[] args) throws IOException {
        //1. 크기, 2차원 배열 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        grid = new int[N][N];
        chk = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);
        System.out.println(_min);
    }

    //2. dfs -> 조건에 만족하면서 세개 씨앗을 모두 탐색하는,, 종료 조건-> 꽃 세개 심었을 때 _min갱신 후 return.
    // dfs return시에 적용시킨 chk 되돌려놓기
    private static void dfs(int planted, int sum) {
        if (planted == 3) {
            _min = Math.min(_min, sum);
            return;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (isValid(i, j)) {
                    int _sum = 0;
                    _sum += calcSum(i, j);
                    refreshChk(true, i, j);
                    dfs(planted + 1, sum + _sum);
                    refreshChk(false, i, j);
                }
            }
        }
    }

    private static void refreshChk(boolean toggle, int i, int j) {
        //4. 체크리스트 -> 선택한 좌표 + 상,하,좌,우를 이미 방문한 곳으로 간주.
        if (toggle) {
            chk[i][j] = true;
            for (int k = 0; k < 4; k++) {
                int _i = i + dy[k];
                int _j = j + dx[k];
                chk[_i][_j] = true;
            }
            return;
        }
        chk[i][j] = false;
        for (int k = 0; k < 4; k++) {
            int _i = i + dy[k];
            int _j = j + dx[k];
            chk[_i][_j] = false;
        }
    }

    private static int calcSum(int i, int j) {
        int _sum = 0;
        _sum += grid[i][j];
        for (int k = 0; k < 4; k++) {
            _sum += grid[i + dy[k]][j + dx[k]];
        }
        return _sum;
    }

    //3. 좌표 검증 -> 씨앗을 심은 좌표 기준 상,하,좌,우가 격자를 벗어나지 않아야하며, 다른 꽃과도 닿지 않아야 함.
    private static boolean isValid(int y, int x) {
        if (chk[y][x]) {
            return false;
        }
        for (int i = 0; i < 4; i++) {
            int _y = y + dy[i];
            int _x = x + dx[i];
            if ((0 > _y || _y >= N) || (0 > _x || _x >= N)) {
                return false;
            }
            if (chk[_y][_x]) {
                return false;
            }
        }
        return true;
    }
}