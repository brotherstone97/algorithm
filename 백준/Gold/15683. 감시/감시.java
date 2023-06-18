import java.util.*;
import java.io.*;

public class Main {
    private static int N;
    private static int M;
    private static int cctvCnt;
    private static List<List<Integer>> cctvCoordi = new ArrayList<>();
    private static String[][] grid;
    private static int answer = Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new String[N][M];
        ////1. 입력받아 2차원 grid
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                grid[i][j] = st.nextToken();
                if (countCCTV(grid[i][j])) {
                    cctvCoordi.add(List.of(i, j));
                }
            }
        }
        dfs(0, 0);
        System.out.println(answer);
    }

    //2차원 배열 클론
    private static String[][] cloneGrid() {
        String[][] newGrid = new String[N][M];
        for (int i = 0; i < N; i++) {
            newGrid[i] = grid[i].clone();
        }
        return newGrid;
    }

    //cctv개수 카운트
    private static boolean countCCTV(String input) {
        char[] charArr = input.toCharArray();
        if (Character.isDigit(charArr[0])) {
            int num = charArr[0] - 48;
            if (1 <= num && num <= 5) {
                cctvCnt++;
                return true;
            }
        }
        return false;
    }

    ////2. dfs로 구현, 이중 for문 돌며 1~5를 발견했을 때, grid에 #을 찍고 depth+1. 다음 숫자 찾아서 반복.
    // 그리드를 전부 탐색(=주어진 cctv개수만큼 depth를 들어왔을 때) 했을 때 0의 개수 카운트해서 min값 갱신
    // 주의! dfs가 return됐을 때 이전 그리드 상태로 돌려놔야함
    private static void dfs(int d, int iter) {
        if (d == cctvCnt) {
            answer = Math.min(answer, cntZero());
            return;
        }
        for (int i = iter; i < cctvCoordi.size(); i++) {
            List<Integer> currentCoordi = cctvCoordi.get(i);
            int kindOfCctv = Integer.parseInt(grid[currentCoordi.get(0)][currentCoordi.get(1)]);
            if (kindOfCctv == 1) {
                for (int k = 0; k < 4; k++) {
                    //그리기 전 이전 상태 저장
                    String[][] newGrid = cloneGrid();
                    //90도 씩 네번
                    drawRange(k, currentCoordi, kindOfCctv);
                    dfs(d + 1, i + 1);
                    //dfs리턴 후 원상복구
                    grid = newGrid;
                }
            } else if (kindOfCctv == 2) {
                for (int k = 0; k < 2; k++) {
                    String[][] newGrid = cloneGrid();
                    //90도 씩 두번
                    drawRange(k, currentCoordi, kindOfCctv);
                    dfs(d + 1, i + 1);
                    grid = newGrid;
                }
            } else if (kindOfCctv == 3) {
                for (int k = 0; k < 4; k++) {
                    String[][] newGrid = cloneGrid();
                    //90도 씩 네번
                    drawRange(k, currentCoordi, kindOfCctv);
                    dfs(d + 1, i + 1);
                    grid = newGrid;
                }
            } else if (kindOfCctv == 4) {
                for (int k = 0; k < 4; k++) {
                    String[][] newGrid = cloneGrid();
                    //90도 씩 네번
                    drawRange(k, currentCoordi, kindOfCctv);
                    dfs(d + 1, i + 1);
                    grid = newGrid;
                }
            } else {
                String[][] newGrid = cloneGrid();
                //90도 씩 한번
                drawRange(5, currentCoordi, kindOfCctv);
                dfs(d + 1, i + 1);
                grid = newGrid;
            }
            dfs(d + 1, i + 1);
            grid = cloneGrid();
        }
    }

    //3. 1~5일 때, grid에 #을 찍는 함수
    private static void drawRange(int angle, List<Integer> currentCoordi, int cctv) {
        int currentRow = currentCoordi.get(0);
        int currentCol = currentCoordi.get(1);

        //필요한 것 현재 좌표
        if (cctv == 1) {
            //우
            if (angle == 0) {
                rightDraw(currentRow, currentCol);
            }//좌
            else if (angle == 1) {
                leftDraw(currentRow, currentCol);
            }//하
            else if (angle == 2) {
                downDraw(currentRow, currentCol);
            } //상
            else {
                upDraw(currentRow, currentCol);
            }
            return;
        }
        if (cctv == 2) {
            if (angle == 0) {
                //우
                leftDraw(currentRow, currentCol);
                //좌
                rightDraw(currentRow, currentCol);

            } else if (angle == 1) {
                //하
                downDraw(currentRow, currentCol);
                //상
                upDraw(currentRow, currentCol);
            }
            return;
        }
        if (cctv == 3) {
            if (angle == 0) {
                //상, 우
                upDraw(currentRow, currentCol);
                rightDraw(currentRow, currentCol);
            } else if (angle == 1) {
                //우, 하
                rightDraw(currentRow, currentCol);
                downDraw(currentRow, currentCol);
            } else if (angle == 2) {
                //좌, 하
                leftDraw(currentRow, currentCol);
                downDraw(currentRow, currentCol);
            } else {
                //좌, 상
                leftDraw(currentRow, currentCol);
                upDraw(currentRow, currentCol);
            }
            return;
        }
        if (cctv == 4) {
            //좌, 상, 우
            if (angle == 0) {
                leftDraw(currentRow, currentCol);
                upDraw(currentRow, currentCol);
                rightDraw(currentRow, currentCol);
            }
            //상, 우, 하
            else if (angle == 1) {
                upDraw(currentRow, currentCol);
                rightDraw(currentRow, currentCol);
                downDraw(currentRow, currentCol);
            }//우, 하, 좌
            else if (angle == 2) {
                rightDraw(currentRow, currentCol);
                downDraw(currentRow, currentCol);
                leftDraw(currentRow, currentCol);
            }
            //하, 좌, 상
            else {
                downDraw(currentRow, currentCol);
                leftDraw(currentRow, currentCol);
                upDraw(currentRow, currentCol);
            }
            return;
        }
        //상, 하, 좌, 우
        upDraw(currentRow, currentCol);
        downDraw(currentRow, currentCol);
        leftDraw(currentRow, currentCol);
        rightDraw(currentRow, currentCol);
    }

    private static void leftDraw(int currentRow, int currentCol) {
        for (int i = currentCol - 1; i >= 0; i--) {
            if (grid[currentRow][i].equals("0")) {
                grid[currentRow][i] = "#";
                continue;
            }
            if (grid[currentRow][i].equals("6")) {
                break;
            }
        }
    }

    private static void rightDraw(int currentRow, int currentCol) {
        for (int i = currentCol + 1; i < M; i++) {
            if (grid[currentRow][i].equals("0")) {
                grid[currentRow][i] = "#";
                continue;
            }
            if (grid[currentRow][i].equals("6")) {
                break;
            }
        }
    }

    private static void downDraw(int currentRow, int currentCol) {
        for (int i = currentRow + 1; i < N; i++) {
            if (grid[i][currentCol].equals("0")) {
                grid[i][currentCol] = "#";
                continue;
            }
            if (grid[i][currentCol].equals("6")) {
                break;
            }
        }
    }

    private static void upDraw(int currentRow, int currentCol) {
        for (int i = currentRow - 1; i >= 0; i--) {
            if (grid[i][currentCol].equals("0")) {
                grid[i][currentCol] = "#";
                continue;
            }
            if (grid[i][currentCol].equals("6")) {
                break;
            }
        }
    }


    private static int cntZero() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j].equals("0")) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
//    /**
//     * cctv 방향을 적절하게 정해서 사각 지대의 최소 크기를 구하자.
//     * 사각지대 = cctv가 감시하는 방향을 최대로 설정했을 때 남는 0의 개수
//     */