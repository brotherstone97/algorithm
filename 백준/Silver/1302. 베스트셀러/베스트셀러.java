import java.util.*;

//카운터를 이용하는 문제 -> Hash를 이용한 문제

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tc = Integer.parseInt(scanner.nextLine());

        Map<String, Integer> booksCount = new HashMap<>();

        //1.이미 있는 키이면 ++ / 없으면 1 python에서의 dict.get()과 같은 기능
        for (int i = 0; i < tc; i++) {
            String bookName = scanner.nextLine();
            if (booksCount.containsKey(bookName)) {
                booksCount.put(bookName, booksCount.get(bookName) + 1);
                continue;
            }
            booksCount.put(bookName, 1);
        }

        Collection<Integer> values = booksCount.values();

        //max추출
        int maxCount = Collections.max(values);
        List<String> bestSeller = new ArrayList<>();
        Set keys = booksCount.keySet();
        for (Object key : keys){
            if (booksCount.get((String)key)==maxCount){
                bestSeller.add((String)key);
            }
        }

        //2. 공동 1등은 사전순
        Collections.sort(bestSeller);
        System.out.println(bestSeller.get(0));
    }
}
