class Solution {
    private int cnt;
    public int solution(int[] numbers, int target) {
        dfs(0, 1, target, numbers);
        return cnt;
    }
    
    private void dfs(int sum, int depth, int target, int[] numbers){
        if(depth>numbers.length){
            if(sum==target){
                cnt++;
            }
            return;
        }
        dfs(sum+numbers[depth-1], depth+1, target, numbers);
        dfs(sum-numbers[depth-1], depth+1, target, numbers);
    }
}

//1. 종료조건: depth>number.length;
//2. 시작은 0, dfs함수 내에서 +/-에 맞춰 두번 호출
//3. 최종 depth에서 누적합한 값이 target과 일치하면 answer++;