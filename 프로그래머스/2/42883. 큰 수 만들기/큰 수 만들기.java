class Solution {
    public String solution(String number, int k) {
        int len = number.length() - k;
        StringBuilder sb = new StringBuilder(len);
        
        int start = 0;
        int range = k;
        
        for (int i = 0; i < len; i++) {
            int max = 0;
            for (int j = start; j <= range; j++) {
                if (number.charAt(j) - '0' > max) {
                    max = number.charAt(j) - '0';
                    start = j+1;
                }
            }
            range++;
            sb.append(max);
        }
        
        return sb.toString();
    }
}