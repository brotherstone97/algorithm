import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //1. n입력, Xn입력받기
        //1-1. 입력받아서 배열 복사 -> set으로 중복제거 한 결과 -> 찍어야할 좌표값 구함.


        //기존 배열을 기반으로 완탐하며 그 요소를 map의 key로 설정하여 얻음
        //value출력 souf("%d ", v)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        List<Integer> originalCoordi = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            originalCoordi.add(Integer.parseInt(st.nextToken()));
        }

        Map<Integer, Integer> map = makeNewCoordi(originalCoordi);

        //3. X'i 출력

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int originalValue = originalCoordi.get(i);
            sb.append(map.get(originalValue)+" ");
//            System.out.printf("%d ", map.get(originalValue));
        }
        System.out.println(sb.toString().strip());
    }

    //2. 압축 알고리즘 구현
    //2-1 서로 다른 좌표 구하기
    // set으로 중복제거 -> 개수 카운트해서 n의 값 구하기 (n은 X'i에 찍을 좌표 개수)
    // set요소로 list만들어 정렬 -> k,v값 완성!
    //2-2 중복이 걸러진 set을 기반으로 map(k:원래 좌표값, v: 압축된 좌표값) 생성(2:2, 4:3, -10:0, -9: 1)
    private static Map<Integer, Integer> makeNewCoordi(List<Integer> origin) {
        Set<Integer> set = new HashSet<>(origin);
        //setToList
        List<Integer> setToList = new ArrayList<>(set);
//        for (int e : set) {
//            setToList.add(e);
//        }

        //sorting
        Collections.sort(setToList);

        Map<Integer, Integer> map = new HashMap<>();

        //create Map
        for (int i = 0; i < setToList.size(); i++) {
            map.put(setToList.get(i), i);
        }

        return map;
    }
}

//새로 알게된 사실 HashSet의 생성자는 Collection받아 모든 요소 추가하는 ㅇㅇ
//배열말고 리스트쓰자

//Collection끼리는 생성자에 집어넣어서 모든 요소 옮길 수 있음