import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        int[] line = new int[N];
        for (int i = 0; i < N; i++) {
            line[i] = sc.nextInt();
        }
        Arrays.sort(line);

        int sum = 0;
        int prevTime = 0;
        for (int i = 0; i < N; i++) {
            sum += prevTime + line[i];
            prevTime += line[i];
        }
        System.out.println(sum);
    }
}

