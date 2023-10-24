import java.util.*;
class Solution {
    
    public int solution(int m, int n, int[][] puddles) {
        int[][] path = new int[n+1][m+1];
        
        for(int i = 0; i<puddles.length; i++){
             int pn = puddles[i][0];
             int pm = puddles[i][1];
             
             path[pm][pn] = -1;
        }
        
        // OR: 테스트케이스 1번 실패
        // AND: 테스트케이스 10번 실패
        /*if (path[1][2] == -1 && path[2][1] == -1){ 
            return 0;
        }*/
        
        
        for(int i = 0; i<=n; i++){
            path[i][0] = -1;
        }
        for(int i = 0; i<=m; i++){
            path[0][i] = -1;
        }
        
        
        path[1][1] = 1;
        for(int i = 1; i<=n; i++){
            for(int j =1; j<=m; j++){
                if((i==1 && j==1) || path[i][j] == -1){
                    // 집이거나, 홍수난 곳이라면
                    continue;
                }
                if(path[i-1][j] == -1 && path[i][j-1] == -1){
                    // 위, 왼쪽이 모두 홍수이거나 길이 없다면
                    path[i][j] = -1;
                    
                    if(i == n && j == m){// 테스트 케이스 10번 해결지점
                        path[i][j] = 0;
                    }
                    continue;
                }
                
                int route = 0;
                if(path[i-1][j] > 0){
                    // 위에서 온 경로의 개수 (아래쪽 V)
                    route += path[i-1][j];
                }
                
                if(path[i][j-1] > 0){
                    // 왼쪽에서 온 경로의 개수(오른쪽 ->)
                    route += path[i][j-1];   
                }
                
                path[i][j] = route % 1000000007;
            }
        }
        
        /*for(int i = 0; i<=n; i++){
            for(int j = 0; j<=m; j++){
                System.out.print(path[i][j]+" ");
            }
            System.out.println();
        }*/
        
        return path[n][m] % 1000000007;
    }
}

/*
테스트 케이스 10번 케이스
ex) n =3, m = 2 일때
집 = H, 홍수 = F, 길 = T, 학교 = S
H F
T F
F S
해당 모양일 경우 S 가 -1이었던걸 0으로 처리하게해서 갈길이 없도록 만들어야함

DP 배열
-1 -1 -1
-1  1 -1
-1  1 -1
-1 -1  0(->이부분이 -1이어서 테스트케이스 통과가 안되었던 거였음)
*/