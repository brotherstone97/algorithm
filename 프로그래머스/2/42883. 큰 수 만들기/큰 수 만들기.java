class Solution {
    public String solution(String number, int k) {
        //구해야하는 자릿수 = number.length()-k이다. 이 크기만큼의 SB생성해서 메모리 절약
        StringBuilder sb = new StringBuilder(number.length()-k);
        //number탐색 인덱스
        int idx=0;
        //한번의 순회에서의 가장 큰 값. 즉, 현재 칸에 집어넣을 숫자
        int max=0;
        
        //구해야하는 자릿수 만큼 루프 돌면서 매순간 max값과 idx를 구함
        for(int i=0; i<number.length()-k; i++){
            //자릿수 구할때마다 초기화
            max=0;
            //구해야할 남은 자릿수보다 (number.length()-1) - idx가 작으면 안됨.
            //이에 따라 j는 현재 idx에서 number.length() - (number.length() - k - i)까지만 이동가능하며 이른 간소화했을 때 j<=k+1이 나온다.
            for(int j=idx; j<=k+i; j++){
                if(max<number.charAt(j)-'0'){
                    //최댓값과 다음 자릿수 탐색을 시작할 idx갱신
                    max=number.charAt(j)-'0';
                    idx=j+1;
                }
            }
            sb.append(max);
        }
        
        return sb.toString();
    }
    
    //레퍼런스
    //https://born2bedeveloper.tistory.com/27
    //https://jhhj424.tistory.com/32
}