import java.util.*;
import java.io.*;

public class Main {
    static int white;
    static int blue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());


        String[][] originalSquare = new String[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                originalSquare[i][j] = st.nextToken();
            }
        }

        divide(originalSquare);
        System.out.println(white);
        System.out.println(blue);
//        for(int i =0; i<N; i++){
//            System.out.println(Arrays.toString(originalSquare[i]));
//        }
    }

    //1. 사각형을 네등분하는 재귀함수
    private static void divide(String[][] square) {
        int squareSize = square.length;
        if (isSame(square)) {
            if (square[0][0].equals("0")) {
                white++;
            } else {
                blue++;
            }
            return;
        }
        //slicing 4번호출
        //섹션1
        divide(slicing(square, 0, squareSize / 2, 0, squareSize / 2));
        //섹션2
        divide(slicing(square, 0, squareSize / 2, squareSize / 2, squareSize));
        //섹션3
        divide(slicing(square, squareSize / 2, squareSize, 0, squareSize / 2));
        //섹션4
        divide(slicing(square, squareSize / 2, squareSize, squareSize / 2, squareSize));
    }

    //2. 완전탐색하는 함수. 매개변수로 넘겨진 2차원 배열의 모든 요소가 같은지 판단
    private static boolean isSame(String[][] square) {
        int size = square.length;
        String criteria = square[0][0];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!square[i][j].equals(criteria)) {
                    return false;
                }
            }
        }
        return true;
    }

    //3. 배열 슬라이싱
    private static String[][] slicing(String[][] square, int startRow, int endRow, int startCol, int endCol) {
        int newSize = square.length / 2;
        String[][] sliced = new String[newSize][newSize];

        int newRow = 0;
        for (int i = startRow; i < endRow; i++) {
            int newCol = 0;
            for (int j = startCol; j < endCol; j++) {
                sliced[newRow][newCol] = String.valueOf(square[i][j]);
                newCol++;
            }
            newRow++;
        }
//        for(int i =0; i<newSize; i++){
//            System.out.println(Arrays.toString(sliced[i]));
//        }
        return sliced;
    }
}