import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int answer = 0;
        Meeting[] meetings = new Meeting[N];

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int start = Integer.parseInt(input[0]);
            int end = Integer.parseInt(input[1]);
            meetings[i] = new Meeting(start, end);
        }

        Arrays.sort(meetings, (o1, o2) -> {
            if (o1.start == o2.start) {
                return o1.end - o2.end;
            }
            return o1.start - o2.start;
        });

        PriorityQueue<Meeting> pq = new PriorityQueue<>((o1, o2) -> o1.end - o2.end);

        for (int i = 0; i < N; i++) {
            if (pq.isEmpty()) {
                pq.offer(meetings[i]);
                continue;
            }

            while (!pq.isEmpty() && pq.peek().end <= meetings[i].start) {
                pq.poll();
            }
            pq.offer(meetings[i]);
            answer = Math.max(pq.size(), answer);
        }

        System.out.println(answer);
    }

    static class Meeting {
        int start;
        int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}