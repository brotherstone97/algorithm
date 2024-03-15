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

    static TreeSet<Data> _max;
    static TreeSet<Data> _min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        //기본적으로 오름차순 정렬

        for (int i = 0; i < T; i++) {
            _max = new TreeSet<>();
            _min = new TreeSet<>();
            int K = Integer.parseInt(br.readLine());

            for (int j = 0; j < K; j++) {
                String[] input = br.readLine().split(" ");
                String cmd = input[0];
                int number = Integer.parseInt(input[1]);
                process(cmd, number);
            }
            //결과 출력
            if (_max.isEmpty() && _min.isEmpty()) {
                System.out.println("EMPTY");
                continue;
            }
            if (_max.isEmpty()) {
                System.out.printf("%d %d\n", _min.last().value, _min.first().value);
                continue;
            }
            if (_min.isEmpty()) {
                System.out.printf("%d %d\n", _max.first().value, _max.last().value);
                continue;
            }
            System.out.printf("%d %d\n", _max.first().value, _min.first().value);
        }
    }

    static void process(String cmd, int number) {
        Data current = new Data(number);
        //추가
        if (cmd.equals("I")) {
            //둘 다 빈 경우
            if (_max.isEmpty() && _min.isEmpty()) {
                _max.add(current);
                return;
            }
            //_max만 빈 경우
            if (_max.isEmpty()) {
                if (number > _min.last().value) {
                    _max.add(current);
                    return;
                }
                _min.add(current);
                return;
            }
            //둘다 안 비었다 || min만 비었다.
            if (number >= _max.first().value) {
                _min.add(_max.pollFirst());
                _max.add(current);
                return;
            }
            _min.add(current);
            return;
        }

        //max에서 제거
        if (number == 1) {
            if (!_max.isEmpty()) {
                _max.pollFirst();
                return;
            }
            if (!_min.isEmpty()) {
                _min.pollLast();
                return;
            }
            return;
        }
        //min에서 제거
        if (!_min.isEmpty()) {
            _min.pollFirst();
            return;
        }
        if (!_max.isEmpty()) {
            _max.pollLast();
        }
    }
}
