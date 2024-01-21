import java.io.*;

class Main {
    private static final int[] dy = {-1, 0, 1, 0};
    private static final int[] dx = {0, -1, 0, 1};
    private static String[][] grid;
    private static int answer;
    private static int N;
    private static int M;
    private static Status current;

    public static void main(String[] args) throws IOException {
        init();
        start();
        System.out.println(answer);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        grid = new String[N][M];

        input = br.readLine().split(" ");
        int startR = Integer.parseInt(input[0]);
        int startC = Integer.parseInt(input[1]);
        //0: 북, 1: 동, 2: 남, 3: 서
        int startDir = Integer.parseInt(input[2]);
        current = new Status(startR, startC, startDir);

        for (int i = 0; i < N; i++) {
            String[] row = br.readLine().split(" ");
            grid[i] = row;
        }
    }

    private static void start() {
        while (true) {
            //1. 현재 칸이 빈칸이면 청소
            if (isEmpty(current.y, current.x)) {
                clean(current.y, current.x);
            }
            //2. 동서남북 중 하나 이상이 빈칸인지 확인
            if (hasEmpty()) {
                //동서남북 탐색
                for (int i = 0; i < 4; i++) {
                    rotate();
                    forward();

                    if (!isEmpty(current.y, current.x)) {
                        back();
                        continue;
                    }
                    break;
                }
            }
            //3. 동서남북 중 빈칸이 없으면
            else {
                if (canMoveBack()) {
                    back();
                    continue;
                }
                break;
            }
        }
    }

    private static boolean isEmpty(int y, int x) {
        return isValidCoord(y, x) && grid[y][x].equals("0");
    }

    private static void clean(int y, int x) {
        grid[y][x] = "2";
        answer++;
    }

    private static boolean hasEmpty() {
        for (int i = 0; i < 4; i++) {
            int _y = current.y + dy[i];
            int _x = current.x + dx[i];
            if (isValidCoord(_y, _x) && grid[_y][_x].equals("0")) {
                return true;
            }
        }
        return false;
    }

    private static boolean canMoveBack() {
        if (current.dir == 0) {
            return isValidCoord(current.y + 1, current.x) && (grid[current.y + 1][current.x].equals("0") || grid[current.y + 1][current.x].equals("2"));
        }
        if (current.dir == 1) {
            return isValidCoord(current.y, current.x - 1) && (grid[current.y][current.x - 1].equals("0") || grid[current.y][current.x - 1].equals("2"));
        }
        if (current.dir == 2) {
            return isValidCoord(current.y - 1, current.x) && (grid[current.y - 1][current.x].equals("0") || grid[current.y - 1][current.x].equals("2"));
        }
        return isValidCoord(current.y, current.x + 1) && (grid[current.y][current.x + 1].equals("0") || grid[current.y][current.x + 1].equals("2"));
    }

    private static void forward() {
        if (current.dir == 0) {
            current.y -= 1;
            return;
        }
        if (current.dir == 1) {
            current.x += 1;
            return;
        }
        if (current.dir == 2) {
            current.y += 1;
            return;
        }
        current.x -= 1;
    }

    private static void back() {
        if (current.dir == 0) {
            current.y += 1;
            return;
        }
        if (current.dir == 1) {
            current.x -= 1;
            return;
        }
        if (current.dir == 2) {
            current.y -= 1;
            return;
        }
        current.x += 1;
    }

    private static void rotate() {
        if (current.dir == 0) {
            current.dir = 4;
        }
        current.dir--;
    }

    private static boolean isValidCoord(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }

    static class Status {
        int y;
        int x;
        int dir;

        public Status(int y, int x, int dir) {
            this.y = y;
            this.x = x;
            this.dir = dir;
        }
    }
}