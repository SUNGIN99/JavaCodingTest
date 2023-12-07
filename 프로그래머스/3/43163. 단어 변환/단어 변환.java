class Solution {
    boolean[] visited;
    int answer;
    String[] wordsGlobal;
    public int solution(String begin, String target, String[] words) {
        answer = Integer.MAX_VALUE;
        visited = new boolean[words.length];
        wordsGlobal = words;
        
        for(int i = 0; i<words.length; i++){
            if(validTransition(begin, words[i]) == 1){
                dfs(words[i], target, i, 1);    
            }
        }
        
        if(answer == Integer.MAX_VALUE){
            return 0;
        }
        
        return answer;
    }
    
    public void dfs(String begin, String target, int v_index, int count){
        if(visited[v_index]){
            return;
        }
        
        if(begin.equals(target)){
            answer = Math.min(answer, count);
            return;
        }
        
        visited[v_index] = true;
        for(int i = 0; i<wordsGlobal.length; i++){
            if(!visited[i] && validTransition(begin, wordsGlobal[i]) == 1){
                dfs(wordsGlobal[i], target, i, count+1);
            }
        }
        
        visited[v_index] = false;
    }
    
    public int validTransition(String s1, String s2){
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        
        int diff = 0;
        for(int i = 0; i<c1.length; i++){
            if(c1[i] != c2[i]){
                diff ++;
            }
        }
        
        return diff;
    }
}