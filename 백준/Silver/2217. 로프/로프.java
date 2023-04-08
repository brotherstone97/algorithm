import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        int maxWeight = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> rope = new ArrayList<>();

        //1.입력
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int ln = Integer.parseInt(br.readLine());
            rope.add(ln);
        }
        //2. 정렬
        Collections.sort(rope);

        //3. 반복
        for (int i = 0; i < N; i++) {
            maxWeight = Math.max(rope.get(i) * (N - i), maxWeight);
        }
        System.out.println(maxWeight);
    }


}
