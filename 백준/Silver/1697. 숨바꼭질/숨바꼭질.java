import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 1. 입력
        Scanner sc = new Scanner(System.in);
        int start = sc.nextInt();
        int end = sc.nextInt();
        int result = bfs(start, end);
        System.out.println(result);
    }

    //2. bfs구현. 현재 점 기준 x-1, x+1, 2x 인 곳 enqueue 하고 이미 간 곳이라면 제외. enqueue 시 배열로
    private static int bfs(int start, int end) {
        Queue<int[]> q = new LinkedList<>();
        boolean[] chk = new boolean[100001];

        int[] init = {start, 0};
        q.offer(init);

        while (!q.isEmpty()) {
            int[] polled = q.poll();
//            System.out.println(Arrays.toString(polled));
            if (polled[0] == end) {
                return polled[1];
            }
            if (polled[0] - 1 >= 0 && !chk[polled[0] - 1]) {
                int[] walkingToLeft = {polled[0] - 1, polled[1] + 1};
                chk[polled[0] - 1] = true;
                q.offer(walkingToLeft);
            }
            if (polled[0] + 1 <= 100000 && !chk[polled[0] + 1]) {
                int[] walkingToRight = {polled[0] + 1, polled[1] + 1};
                chk[polled[0] + 1] = true;
                q.offer(walkingToRight);
            }
            if (polled[0] * 2 <= 100000 && !chk[polled[0] * 2]) {
                int[] teleport = {polled[0] * 2, polled[1] + 1};
                chk[polled[0] * 2] = true;
                q.offer(teleport);
            }
        }
        return -1;
    }
}

//최단거리
//수빈이가 동생을 찾을 수 있는 가장 빠른 시간은 몇 초인지?

/*
걷기: t초 후 -> x-t or x+t
순간이동: t초후 -> 2t
 */

/*
일단 완탐x
dp로 풀어야할듯

 */

