

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//두 배열을 합치고 정렬
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer> aList = new ArrayList<>();
        List<Integer> bList = new ArrayList<>();

        List<Integer> result = new ArrayList<>();
        StringTokenizer A = new StringTokenizer(br.readLine());
        StringTokenizer B = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            aList.add(Integer.parseInt(A.nextToken()));
        }
        for (int i = 0; i < M; i++) {
            bList.add(Integer.parseInt(B.nextToken()));
        }

        int i = 0;
        int j = 0;

        while (i < N && j < M) {
            int a = aList.get(i);
            int b = bList.get(j);
            if (a <= b) {
                result.add(a);
                i++;
                continue;
            }
            result.add(b);
            j++;
        }

        if (i == N) {
            for (int k = j; k < M; k++) {
                result.add(bList.get(k));
            }
        } else {
            for (int k = i; k < N; k++) {
                result.add(aList.get(k));
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int e : result){
            sb.append(e+" ");
        }

        System.out.println(sb.toString());

//        result.forEach(e -> System.out.printf("%d ", e));
//        aList.forEach(e-> System.out.println(e));
//        bList.forEach(e-> System.out.println(e));


    }

}