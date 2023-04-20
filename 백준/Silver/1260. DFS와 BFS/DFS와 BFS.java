import java.util.*;
import java.io.*;
//방문할 수 있는 정점이 여러 개인 경우 정점 번호가 작은 것을 먼저 방문. 더 이상 방문할 수 없으면 종료

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    //4. DFS, BFS호출
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        //n, m, v입력
        int[] nmv = new int[3];

        for (int i = 0; i < 3; i++) {
            String token = st.nextToken();
            nmv[i] = Integer.parseInt(token);
        }
        int n = nmv[0];
        int m = nmv[1];
        int v = nmv[2];

        int[][] adj = new int[n][n];

        makeAdj(nmv, adj);

        //5. CHECK LIST 만들어서 이미 다녀간 정점이면 재방문하지 않도록 함.
        boolean[] chk = makeChk(n, v);
        dfs(v - 1, adj, chk);
        System.out.println();
        chk = makeChk(n, v);
        bfs(v - 1, adj, chk);
    }

    //1. M개 줄을 입력받아 인접행렬(양방향) 만들기
    private static void makeAdj(int[] nmv, int[][] adj) throws IOException {
        int n = nmv[0];
        int m = nmv[1];

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v1 = 0;
            int v2 = 0;
            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());
            adj[v1 - 1][v2 - 1] = 1;
            adj[v2 - 1][v1 - 1] = 1;
        }
    }

    //2. DFS구현. 한 정점을 방문했을 때 현재 노드 정보 출력
    private static void dfs(int start, int[][] adj, boolean[] chk) {
        System.out.printf("%d ", start + 1);
        for (int i = 0; i < adj.length; i++) {
            if (adj[start][i] == 1) {
                //검증
                if (chk[i]) {
                    continue;
                }
                chk[i] = true;
                dfs(i, adj, chk);
            }
        }

    }

    //3. BFS구현
    private static void bfs(int start, int[][] adj, boolean[] chk) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        while (!q.isEmpty()) {
            int next = q.poll();
            System.out.printf("%d ", next + 1);
            for (int i = 0; i < adj.length; i++) {
                if (adj[next][i] == 1) {
                    if (chk[i]) {
                        continue;
                    }
                    q.offer(i);
                    chk[i] = true;
                }
            }
        }
        //최초 1회 start enq
        //while -> q가 빌 때까지
        //q.poll -> 출력 -> 해당 next 행 탐색
        // 1이면 enqueue 단, 방문이력없을 때
    }

    //5. CHECK LIST 만들어서 이미 다녀간 정점이면 재방문하지 않도록 함.
    private static boolean[] makeChk(int n, int v) {
        boolean[] chk = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (i == v - 1) {
                chk[i] = true;
                return chk;
            }
        }
        return null;
    }
}

