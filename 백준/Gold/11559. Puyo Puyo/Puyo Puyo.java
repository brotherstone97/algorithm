import java.util.*;
import java.io.*;

class Main {
    private static final int MAX_ROW = 12;
    private static final int MAX_COL = 6;
    private static final char[][] grid = new char[MAX_ROW][MAX_COL];
    private static final int[] dy = {1, 0, -1, 0};
    private static final int[] dx = {0, 1, 0, -1};
    private static boolean isPopped;
    private static int answer;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //1.입력
        for (int i = 0; i < MAX_ROW; i++) {
            String input = br.readLine();
            for (int j = 0; j < MAX_COL; j++) {
                grid[i][j] = input.charAt(j);
            }
        }

        while (true) {
            isPopped = false;
            search();
            fall();
            //5. 모든 grid 요소 순회 시 연쇄가 없었다면 더 이상 진행할필요가 없으므로 종료.
            if (!isPopped) {
                break;
            }
            answer++;
        }

        System.out.println(answer);
    }

    //2. 순회하며 puyo발견 시 bfs호출
    private static void search() {
        boolean[][] visited = new boolean[MAX_ROW][MAX_COL];
        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COL; j++) {
                if (grid[i][j] != '.' && !visited[i][j]) {
                    visited[i][j] = true;
                    bfs(i, j, visited);
                }
            }
        }
    }

    //3.bfs수행하며 같은 뿌요가 상하좌우로 4개 이상 이어지는 지 확인. 이어진다면 해당하는 좌표를 .으로 바꾸고 연쇄flag에 true대입
    private static void bfs(int startY, int startX, boolean[][] visited) {
        Queue<Puyo> q = new LinkedList<>();
        q.offer(new Puyo(startY, startX, grid[startY][startX]));

        List<int[]> puyoCoord = new ArrayList<>();
        puyoCoord.add(new int[]{startY, startX});

        while (!q.isEmpty()) {
            Puyo polled = q.poll();
            int y = polled.y;
            int x = polled.x;
            char color = polled.color;

            for (int k = 0; k < 4; k++) {
                int _y = y + dy[k];
                int _x = x + dx[k];
                if (isValidCoord(_y, _x) && !visited[_y][_x] && grid[_y][_x] == color) {
                    visited[_y][_x] = true;
                    puyoCoord.add(new int[]{_y, _x});
                    q.offer(new Puyo(_y, _x, color));
                }
            }
        }

        if (puyoCoord.size() >= 4) {
            isPopped = true;
            puyoCoord.forEach(coord -> {
                int y = coord[0];
                int x = coord[1];
                grid[y][x] = '.';
            });
        }
    }

    //4. 가능한 모든 연쇄 수행 후 puyo가 중력에 의해 떨어지도록 함.
//    private static void fall() {
//        for (int c = 0; c < MAX_COL; c++) {
//            Queue<Character> q = new LinkedList<>();
//            for (int r = MAX_ROW - 1; r > 0; r--) {
//                if (grid[r][c] == '.') {
//                    continue;
//                }
//
//                q.offer(grid[r][c]);
//                grid[r][c] = '.';
//            }
//            if (!q.isEmpty()) {
//                int iter = q.size();
//                for (int k = MAX_ROW - 1; k > MAX_ROW - 1 - iter; k--) {
//                    grid[k][c] = q.poll();
//                }
//            }
//        }
//    }
    static void fall() {
        // 각 열 마다 내리는 연산 수행함
        for(int j=0; j<6; j++) {
            down(j);
        }
    }

    // 한 열에 있는 뿌요를 바닥까지 내림
    static void down(int j) {
        Queue<Puyo> puyo = new LinkedList<>();
        int idx = 11;

        /*
         * 뿌요의 위치를 큐에 넣음, 가장 아래에 있는 빈 칸의 인덱스를 구함
         * -> 가장 바닥에 있는 뿌요도 큐에 넣어서 모두 빈 칸으로 만든 뒤
         * 가장 아래부터 큐에 있는 뿌요들을 차례로 채워나감
         */
        for(int i=11; i>=0; i--) {
            if(grid[i][j] != '.') {
                puyo.add(new Puyo(i, j, grid[i][j]));
                grid[i][j] = '.';
            }
        }
        // 뿌요를 가장 밑에 있는 빈 칸에 채워나감
        while(!puyo.isEmpty()) {
            Puyo p = puyo.poll();

            char color = p.color;

            grid[idx][j] = color;

            idx--;
        }

    }

    private static boolean isValidCoord(int y, int x) {
        return 0 <= y && y < MAX_ROW && 0 <= x && x < MAX_COL;
    }

    static class Puyo {
        int y;
        int x;
        char color;

        Puyo(int y, int x, char color) {
            this.y = y;
            this.x = x;
            this.color = color;
        }
    }

}