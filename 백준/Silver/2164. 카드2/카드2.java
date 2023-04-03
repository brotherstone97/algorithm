import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        Queue<Integer> cards = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            cards.offer(i);
        }

        while (cards.size() > 1) {
            cards.poll();
            int card = cards.poll();
            cards.offer(card);
        }

        System.out.println(cards.peek());
    }
}
