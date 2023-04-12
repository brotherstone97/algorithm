import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        for (int i = 0; i < tc; i++) {
            int N = Integer.parseInt(br.readLine());
            Map<String, List<String>> map = new HashMap<>();

            for (int j = 0; j < N; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String name = st.nextToken();
                String kind = st.nextToken();

                if (map.containsKey(kind)) {
                    map.get(kind).add(name);
                    continue;
                }
                List<String> names = new ArrayList<>();
                names.add(name);
                map.put(kind, names);
            }
            System.out.println(calcCombination(map)-1);
        }
    }

    public static int calcCombination(Map<String, List<String>> map) {
        int sumCombination = 1;
        for (String s : map.keySet()) {
            List<String> value = map.get(s);
            sumCombination *= value.size() + 1;
        }
        return sumCombination;
    }

}

