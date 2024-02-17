import java.io.*;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] NM = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] trees = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int lt = 0;
        int rt = Arrays.stream(trees).max().orElse(-1);
        int res = 0;
        while (lt <= rt) {
            int mid = (lt + rt) / 2;
            if (cut(mid, trees) >= NM[1]) {
                lt = mid + 1;
                res = mid;
                continue;
            }
            rt = mid - 1;
        }

        System.out.println(res);
    }

    private static long cut(int mid, int[] trees) {
        long sum = 0;
        for (int tree : trees) {
            if (tree > mid) {
                sum += tree - mid;
            }
        }
        return sum;
    }
}