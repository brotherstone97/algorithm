import java.util.*;

class Solution {
    public int solution(int[] arr) {
        
        Arrays.sort(arr);
        int max = arr[arr.length-1];
        
        int gcd=1;
        
        
        while(!isCoprime(arr, max)){
            Arrays.sort(arr);
            max= arr[arr.length-1];
            
            for(int i=2; i<=max; i++){
                int cnt=0;
                for(int j=0; j<arr.length; j++){
                    if(arr[j]%i==0){
                        arr[j]/=i;
                        continue;
                    }
                    cnt++;
                }
                if(cnt!=arr.length){
                    gcd*=i;
                    break;
                }
            }
        }
        
        return gcd * Arrays.stream(arr).reduce((e1,e2)->e1*e2).getAsInt();
    }
    
    private boolean isCoprime(int[] arr, int max){
        for(int i=2; i<=max; i++){
            int cnt=0;
            for(int e:arr){
                if(e%i==0){
                    cnt++;
                }
            }
            if(cnt>=2){
                return false;
            }
        }
        return true;
    }
}