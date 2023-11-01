import java.util.*;
class Solution {
    private int zeroCnt;
    private int oneCnt;
    private int[][] sqr;
    
    public int[] solution(int[][] arr) {
        sqr = arr;
        dnq(0, 0, sqr.length);
        
        return new int[]{zeroCnt, oneCnt};
    }
    
    private void dnq(int rowStart, int colStart, int size){
        //열의 시작 좌표와 끝 좌표, 행의 시작 좌표와 끝 좌표를 뺀값이 1보다 작거나 같을 때 종료
        //근데 size로 계산하는게 더 간편하고 단순해보임
        if(size==1){
            if(sqr[rowStart][colStart] == 0){
                zeroCnt++;
                return;
            }
            oneCnt++;
            return;
        }       
            //s가 같은 숫자일 때 리턴
            if(isSameNumber(rowStart, colStart, size)){
                if(sqr[rowStart][colStart] == 0){
                    zeroCnt++;
                    return;
                }
                oneCnt++;
                return;
            }
        
            //하나의 블럭이 아니면 4개로 분할
            //제 1사분면
            dnq(rowStart, colStart+size/2, size/2);
            //제 2사분면
            dnq(rowStart, colStart, size/2);
            //제 3사분면
            dnq(rowStart + size/2, colStart, size/2);
            //제 4사분면
            dnq(rowStart + size/2, colStart + size/2, size/2);

    }
    
    private boolean isSameNumber(int rowStart, int colStart, int size){
        for(int i=rowStart; i<rowStart+size; i++){
            for(int j=colStart; j<colStart+size; j++){
                if(sqr[rowStart][colStart]!=sqr[i][j]){
                    return false;
                }   
            }
        }
        return true;
    }
}