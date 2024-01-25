import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 1. 현재 상어의 위치가 본인보다 더 큰 물고기들로 둘러싸여있다면 종료(isLockedDown)
 * 2. grid를 순회하며 이동가능한 물고기가 발견되면 그 물고기까지의 거리를 구함.(calcDistance) 단,가는 도중 막혀있다면 continue
 * 3. 위를 통해 현재 도달가능하고 먹을 수 있는 물고기 중 최단거리를 구하게 되었고, 최단거리에 있는 먹을 수있는 물고기를 list에 모두 담음
 * 4. list의 길이가 1이상이면 갈 수 있는 곳이 2곳 이상이기 때문에 제일 위에 있는 물고기로 이동하게 하며, 그마저도 둘 이상이면 가장 왼쪽 물고기 즉, 높이 우선순위가 같다면 그 중 가장 왼쪽에 있는 물고기로 이동(getTargetFish)
 * 5. target이 확정되면 레벨업 조건인지 확인하고(leveling), 현재 상어의 위치를 0으로, 타겟의 위치를 9로 바꾼 뒤 answer+=최단거리로 갱신해준다.
 */
class Main {
    private static final int[] dy = {1, 0, -1, 0};
    private static final int[] dx = {0, 1, 0, -1};
    private static int[][] grid;
    private static int[] currentShark;
    private static int sharkLevel = 2;
    private static int eatenFishes;
    private static int N;
    private static boolean hasHelpCall;
    private static int answer;

    public static void main(String[] args) throws IOException {
        init();
        while (!hasHelpCall) {
            if (isLockedDown()) {
                break;
            }
            move();
        }

        System.out.println(answer);
    }

    private static boolean isLockedDown() {
        int y = currentShark[0];
        int x = currentShark[1];

        for (int i = 0; i < 4; i++) {
            int _y = y + dy[i];
            int _x = x + dx[i];

            if (isValidCoord(_y, _x) && grid[_y][_x] <= sharkLevel) {
                return false;
            }
        }
        return true;
    }

    private static void move() {
        int nearest = Integer.MAX_VALUE;
        List<int[]> minDistances = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] > 0 && grid[i][j] < sharkLevel) {
                    int distance = calcDistance(new int[]{i, j});
                    if (distance == -1) {
                        continue;
                    }
                    if (distance < nearest) {
                        minDistances = new ArrayList<>();
                        minDistances.add(new int[]{i, j});
                        nearest = distance;
                        continue;
                    }

                    if (distance == nearest) {
                        minDistances.add(new int[]{i, j});
                    }
                }
            }
        }
        
        if (nearest == Integer.MAX_VALUE) {
            hasHelpCall = true;
            return;
        }

        int[] target = null;

        if (minDistances.size() > 1) {
            target = getTargetFish(minDistances);
        } else {
            target = minDistances.get(0);
        }

        leveling();
        grid[currentShark[0]][currentShark[1]] = 0;
        grid[target[0]][target[1]] = 9;
        currentShark = target;
        answer += nearest;
    }

    private static void leveling() {
        eatenFishes++;
        if (sharkLevel == eatenFishes) {
            eatenFishes = 0;
            sharkLevel++;
        }
    }

    private static int[] getTargetFish(List<int[]> minDistances) {
        int minY = N;
        int minX = N;
        List<int[]> topFishes = new ArrayList<>();
        int[] leftFish = null;

        for (int[] dist : minDistances) {
            if (dist[0] < minY) {
                minY = dist[0];
                topFishes = new ArrayList<>();
                topFishes.add(dist);
                continue;
            }
            if (dist[0] == minY) {
                topFishes.add(dist);
                continue;
            }
            if (dist[1] < minX) {
                minX = dist[1];
                leftFish = dist;
            }
        }

        if (topFishes.size() > 1) {
            List<int[]> sorted = topFishes.stream().sorted((e1, e2) -> e1[1] - e2[1]).collect(Collectors.toList());
            return sorted.get(0);
        }
        return topFishes.get(0);
    }

    //target에서 상어까지의 거리 구하는
    private static int calcDistance(int[] target) {
        boolean[][] visited = new boolean[N][N];
        visited[target[0]][target[1]] = true;

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{target[0], target[1], 0});

        while (!q.isEmpty()) {
            int[] polled = q.poll();
            int y = polled[0];
            int x = polled[1];
            int cnt = polled[2];

            for (int i = 0; i < 4; i++) {
                int _y = y + dy[i];
                int _x = x + dx[i];

                if (isValidCoord(_y, _x) && !visited[_y][_x] && (grid[_y][_x] == 9 || grid[_y][_x] <= sharkLevel)) {
                    if (grid[_y][_x] == 9) {
                        return cnt + 1;
                    }
                    visited[_y][_x] = true;
                    q.offer(new int[]{_y, _x, cnt + 1});
                }

            }
        }

        return -1;
    }

    private static int[] whereIsShark() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 9) {
                    return new int[]{i, j};
                }
            }
        }
        throw new NoSuchElementException("상어 없음");
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        grid = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            grid[i] = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();
        }

        currentShark = whereIsShark();
    }

    private static boolean isValidCoord(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }
}