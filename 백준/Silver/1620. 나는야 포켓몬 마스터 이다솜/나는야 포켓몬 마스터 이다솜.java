import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String firstLine = br.readLine();
        String[] NM = firstLine.split(" ");
        int N = Integer.parseInt(NM[0]);
        int M = Integer.parseInt(NM[1]);

        Map<String, Integer> stringInt = new HashMap<>();
        Map<Integer, String> intString = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            stringInt.put(input, i + 1);
            intString.put(i + 1, input);
        }
        for (int i = 0; i < M; i++) {
            String input = br.readLine();
            //문자열인지 숫자인지 구별해야함
            if (checkStringOrInt(input)) {
                int poketmonNumber = Integer.parseInt(input);
                System.out.println(intString.get(poketmonNumber));
                continue;
            }
            System.out.println(stringInt.get(input));
        }
    }

    public static boolean checkStringOrInt(String input) {
        char[] chArray = input.toCharArray();
        if (Character.isDigit(chArray[0])) {
            return true;
        }
        return false;
    }
}

