import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] size = br.readLine().split(" ");
        int answer = 0;
        int H = Integer.parseInt(size[0]);
        int W = Integer.parseInt(size[1]);

        String[] heights = br.readLine().split(" ");
        int[] blocks = new int[W];
        for (int i = 0; i < heights.length; i++) {
            blocks[i] = Integer.parseInt(heights[i]);
        }

        for (int i = 1; i < W - 1; i++) {
            int lm = 0;
            int rm = 0;

            int current = blocks[i];

            for (int j = i - 1; j >= 0; j--) {
                lm = Math.max(lm, blocks[j]);
            }
            for (int j = i + 1; j < W; j++) {
                rm = Math.max(rm, blocks[j]);
            }

            if (lm > current && rm > current) {
                answer += Math.min(lm, rm) - current;
            }
        }

        System.out.println(answer);
    }
}