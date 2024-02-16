import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    private static int[] distances;
    private static int[] prices;
    private static int idx;

    private static int N;
    private static long res;

    public static void main(String[] args) throws IOException {
        //0. 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        distances = new int[N - 1];
        prices = new int[N - 1];

        String[] distancesInput = br.readLine().split(" ");
        String[] pricesInput = br.readLine().split(" ");

        for (int i = 0; i < N - 1; i++) {
            distances[i] = Integer.parseInt(distancesInput[i]);
            prices[i] = Integer.parseInt(pricesInput[i]);
        }

        while (idx < N - 1) {
            res += searchCurrentCost();
        }

        System.out.println(res);
    }

    //1. 현재 노드보다 기름값이 싼 도시가 나올 때까지의 누적 비용을 구하는 메서드
    private static long searchCurrentCost() {
        int currentPrice = prices[idx];
        int accDist = 0;
        for (int i = idx; i < N - 2; i++) {
            accDist += distances[i];
            //다음 노드가 현재 노드보다 저렴하면 다음 노드까지 가는 길 까지만 주유
            if (prices[i + 1] < currentPrice) {
                idx = i + 1;
                return (long) currentPrice * accDist;
            }
        }
        //끝 노드까지 비교했을 때도 현재 노드가 가장 저렴하면 현재 노드의 가격으로 끝까지 주유
        idx = N - 1;
        return (long) currentPrice * (accDist + distances[distances.length - 1]);
    }
}