import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int target;
    static Set<Integer> excluded;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = new int[9];
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int num = Integer.parseInt(br.readLine());
            sum += num;
            arr[i] = num;
        }
        
        target = sum - 100;

        combination(0, 9, 2, new HashSet<>(), 0, arr);

        int[] answer = Arrays.stream(arr).filter(e->!excluded.contains(e)).sorted().toArray();
        Arrays.stream(answer).forEach(System.out::println);
    }

    private static void combination(int start, int n, int r, Set<Integer> selected, int sum, int[] arr) {
        if (r == 0) {
            if (sum == target) {
                excluded = new HashSet<>(selected);
                return;
            }
        }

        for (int i = start; i < n; i++) {
            selected.add(arr[i]);
            combination(i + 1, n, r - 1, selected, sum + arr[i], arr);
            selected.remove(arr[i]);
        }
    }
}