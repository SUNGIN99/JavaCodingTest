import java.util.*;
import java.io.*;

public class Main{

    static class Trie{
        String word;
        boolean isWord;
        HashMap<String, Trie> subWord;

        public String toString(){
            return " " + subWord.keySet();
        }

        Trie(String w){
            this.word = w;
            isWord = false;
            subWord = new HashMap<>();
        }
    }

    static HashMap<String, Trie> dict;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        boolean isValid;
        for(int i = 0; i<t; i++){
            dict = new HashMap<>();
            int numCount = Integer.parseInt(br.readLine());
            isValid = true;
            for(int j = 0; j<numCount; j++){
                if(!trieString(br.readLine())){
                    isValid = false;
                }
            }

            //System.out.println(dict);
            System.out.println(isValid?"YES":"NO");

        }
    }

    static boolean trieString(String number){
        HashMap<String, Trie> tempDict = dict;
        Trie curTrie = null;
        boolean result = true;
        for(String num : number.split("")){

            curTrie = tempDict.getOrDefault(num, new Trie(num));
            if(curTrie.isWord){
                //System.out.println(number);
                result = false;
            }

            tempDict.put(num, curTrie);
            //System.out.println(tempDict);
            tempDict = curTrie.subWord;
        }

        if(curTrie.subWord.size() > 0){
            //System.out.println(number);
            result = false;
        }
        curTrie.isWord= true;
        //System.out.println();

        return result;
    }

}