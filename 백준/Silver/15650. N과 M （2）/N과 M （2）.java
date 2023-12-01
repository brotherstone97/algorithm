import java.io.*;
import java.util.*;

class Main {
    private static int[] seq;
    private static List<List<Integer>> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        seq = new int[n];

        for (int i = 0; i < n; i++) {
            seq[i] = i + 1;
        }
        comb(seq, new boolean[n], n, m, 0);

        StringBuilder sb = new StringBuilder();

        for(List<Integer> e : answer){
            for(int number : e){
                sb.append(number).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void comb(int[] arr, boolean[] visited, int n, int m, int start) {
        if (m == 0) {
            saveCombi(arr, visited);
            return;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            comb(arr, visited, n, m - 1, i + 1);
            visited[i] = false;
        }
    }

    private static void saveCombi(int[] arr, boolean[] visited) {
        List<Integer> temp = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            if (visited[i]) {
                temp.add(arr[i]);
            }
        }
        answer.add(temp);
    }
}