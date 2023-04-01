import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

//두 배열을 합치고 정렬
public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int lengthA = Integer.parseInt(st.nextToken());
        int lengthB = Integer.parseInt(st.nextToken());

        List<Long> a = new ArrayList<>();
        List<Long> b = new ArrayList<>();

        List<Long> result = new ArrayList<>();

        createString(a, lengthA);
        createString(b, lengthB);

        //a.forEach(e->System.out.println(e));
//        a.forEach(System.out::println);
//        b.forEach(System.out::println);


        //피드백: 나는 포인터 하나로 구현했는데 해설은 배열별 포인터 하나씩 총 두개로 함

        int idxA = 0;
        int idxB = 0;

        while (idxA < lengthA && idxB < lengthB) {
            Long elementA = a.get(idxA);
            Long elementB = b.get(idxB);
            if (elementA <= elementB) {
                result.add(elementA);
                idxA++;
                continue;
            }
            result.add(elementB);
            idxB++;
        }

        if (idxA == lengthA) {
            for (int i = idxB; i < lengthB; i++)
                result.add(b.get(i));
        }else{
            for (int i = idxA; i < lengthA; i++)
                result.add(a.get(i));
        }

//        for (int i = 0; i < result.size(); i++) {
//            System.out.printf("%d ", result.get(i));
//        }

        StringBuilder sb = new StringBuilder();
        for (Long e : result) {
            sb.append(e + " ");
        }
        System.out.println(sb.toString());
    }

    private static void createString(List<Long> list, int listLength) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= listLength; i++) {
            String s = st.nextToken();
            list.add(Long.parseLong(s));
        }
    }
}


//이미 정렬 되어있기 때문에
//반복문 돌며 가장 앞에 있는 두 요소 비교
//그렇게 하면 O(N)으로 끝낼 수 있다.

//        a.addAll(b);
//        for (Long aLong : b) {
//            a.add(aLong);
//        }

