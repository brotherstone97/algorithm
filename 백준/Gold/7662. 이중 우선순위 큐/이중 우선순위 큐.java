//hard: TreeSet의 특징을 놓쳐 틀림, compareTo연산 시 두 수의 차 연산 값이 integer범위를 넘어버리는 경우를 간과함.
/**
 * 특징1. TreeSet도 set이므로 중복된 요소가 존재할 수 없음.
 * <p>
 * 특징2. Treeset이 compareTo의 리턴값을 기준으로 정렬하도록 했을 때
 * compareTo가 특정 객체의 필드 value값을 비교한다고 하면
 * this.value-o.value=0 즉, 비교 대상인 두 값이 같은 때는 Treeset에 값을 add할 수 없다.
 * 따라서 같은 값을 TreeSet에 삽입해야 할 때는 위 조건일 때 양수 또는 음수를 리턴하도록 compareTo의 로직을 수정해야한다.
 **/

import java.io.*;
import java.util.*;

//중복 요소가 있는 경우 엣지 케이스  체크
class Main {
    static class Data implements Comparable<Data> {
        int value;

        public Data(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        @Override
        public int compareTo(Data o) {
            if (this.value <= o.value) {
                return -1;
            }
            return 1;
        }
    }

    static TreeSet<Data> treeSet;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        //기본적으로 오름차순 정렬
        for (int i = 0; i < T; i++) {
            treeSet = new TreeSet<>();
            int K = Integer.parseInt(br.readLine());

            for (int j = 0; j < K; j++) {
                String[] input = br.readLine().split(" ");
                String cmd = input[0];
                int number = Integer.parseInt(input[1]);
                process(cmd, number);
            }
            printResult();
        }
    }

    static void process(String cmd, int number) {
        Data current = new Data(number);
        //추가
        if (cmd.equals("I")) {
            treeSet.add(current);
            return;
        }

        //삭제
        if (number == -1) {
            if (!treeSet.isEmpty()) {
                treeSet.pollFirst();
                return;
            }
        }
        if (!treeSet.isEmpty()) {
            treeSet.pollLast();
        }
    }

    static void printResult() {
        if (treeSet.isEmpty()) {
            System.out.println("EMPTY");
            return;
        }
        System.out.printf("%d %d\n", treeSet.last().value, treeSet.first().value);
    }
}