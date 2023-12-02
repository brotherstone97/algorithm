import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class Main {
    private static int[] seq;
    private static List<List<Integer>> answer = new ArrayList<>();

    private static List<Integer> res = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        seq = new int[n];

        for (int i = 0; i < n; i++) {
            seq[i] = i + 1;
        }
        comb(seq, new boolean[n], n, m);

        StringBuilder sb = new StringBuilder();

        for (List<Integer> e : answer) {
            for (int number : e) {
                sb.append(number).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void comb(int[] arr, boolean[] visited, int n, int m) {
        if (m == 0) {
            ArrayList<Integer> copied = new ArrayList<>(res);
            answer.add(copied);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            res.add(arr[i]);
            comb(arr, visited, n, m - 1);
            res.remove(res.size()-1);
            visited[i] = false;
        }
    }
}