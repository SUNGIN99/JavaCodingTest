import java.util.*;
import java.io.*;

public class Main {

    static int[][] papers;
    static int paper0 = 0, paper1 = 0, paper_1 = 0;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        papers = new int[n][];

        for(int i =0 ;i<n; i++){
            papers[i] = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n; j++){
                papers[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        paperCut(0, 0, n);

        System.out.println(paper_1);
        System.out.println(paper0);
        System.out.println(paper1);
    }

    static void paperCut(int standX, int standY, int size){
        if(size == 1){
            if(papers[standX][standY] == 0){
                paper0++;
            }else if(papers[standX][standY] == 1){
                paper1++;
            }else{
                paper_1++;
            }
            return;
        }

        boolean zeroPaper = false, onePaper = false, mOnePaper = false;
        for(int i = 0; i< size; i++){
            for(int j = 0; j<size; j++){
                if(papers[standX+i][standY+j] == 0){
                    zeroPaper = true;
                }else if(papers[standX+i][standY+j] == 1){
                    onePaper = true;
                }else{
                    mOnePaper = true;
                }
            }
        }

        if(zeroPaper){
            if(!onePaper && !mOnePaper){
                //System.out.println("zero ## x: " + standX + ", y: " + standY + " => size: " + size);
                paper0++;
            }else{ // 종이가 하나가 아닐때
                paperCut(standX, standY, size / 3);
                paperCut(standX, standY + size/3, size / 3);
                paperCut(standX, standY + (size/3) * 2, size / 3);

                paperCut(standX + size/3, standY, size / 3);
                paperCut(standX + size/3, standY + size/3, size / 3);
                paperCut(standX + size/3, standY + (size/3) * 2, size / 3);

                paperCut(standX + (size/3) * 2, standY, size / 3);
                paperCut(standX + (size/3) * 2, standY + size/3, size / 3);
                paperCut(standX + (size/3) * 2, standY + (size/3) * 2, size / 3);
            }
        }
        else if(onePaper){
            if(!zeroPaper && !mOnePaper){
                //System.out.println("one ## x: " + standX + ", y: " + standY + " => size: " + size);
                paper1++;
            }else{// 종이가 하나가 아닐때
                paperCut(standX, standY, size / 3);
                paperCut(standX, standY + size/3, size / 3);
                paperCut(standX, standY + (size/3) * 2, size / 3);

                paperCut(standX + size/3, standY, size / 3);
                paperCut(standX + size/3, standY + size/3, size / 3);
                paperCut(standX + size/3, standY + (size/3) * 2, size / 3);

                paperCut(standX + (size/3) * 2, standY, size / 3);
                paperCut(standX + (size/3) * 2, standY + size/3, size / 3);
                paperCut(standX + (size/3) * 2, standY + (size/3) * 2, size / 3);
            }
        }
        else{//
            //System.out.println("-one ## x: " + standX + ", y: " + standY + " => size: " + size);
            paper_1++;
        }

    }


}