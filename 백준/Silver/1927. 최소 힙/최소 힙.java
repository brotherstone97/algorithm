import java.io.IOException;
import java.util.PriorityQueue;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(br.readLine());
            if(input==0){
                if(pq.isEmpty()){
                    System.out.println(0);
                    continue;
                }
                System.out.println(pq.poll());
                continue;
            }
            pq.offer(input);
        }

    }
}

