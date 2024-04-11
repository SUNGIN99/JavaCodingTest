import java.util.*;
import java.io.*;

public class Main{
    static char[] func;
    static int n;
    static int max = 0;
    static ArrayList<Integer> number;
    static ArrayList<String> operand;
    static int numCount, opCount;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        number= new ArrayList<>();
        operand = new ArrayList<>();

        func = br.readLine().toCharArray();
        for(char c : func){
            if(c >= '0' && c <='9'){
                number.add(c - '0');
                numCount++;
            }
            else if(c == '+' || c== '*' || c=='-'){
                operand.add(String.valueOf(c));
                opCount++;
            }
        }
        //System.out.println(number);
        //System.out.println(operand);
        
        if(n == 1){
            System.out.println(number.get(0));
            return;
        }

        int result1 = maxNum(number.get(0), 1, 0, ""+number.get(0));
        int result2 = maxNum(getResult(number.get(0), operand.get(0), number.get(1)), 2, 1, "(" +number.get(0) + operand.get(0)+number.get(1) + ")");
        System.out.println(Math.max(result1, result2));
    }

    static int maxNum(int sum, int numIndex, int opIndex, String str){
        if(numIndex >= numCount || opIndex >= opCount) {
            //System.out.println(str + "= " +sum);
            //System.out.println("----");
            return sum;
        }
        int num1 = number.get(numIndex);
        int result1 = maxNum(
                getResult(sum, operand.get(opIndex), num1),
                numIndex+1,
                opIndex+1,
                str + operand.get(opIndex) + num1);

        int result2 = Integer.MIN_VALUE;
        if(numIndex + 1 <= numCount-1){
            int num2 = number.get(numIndex+1);
            result2 = maxNum(
                    getResult(sum, operand.get(opIndex), getResult(num1, operand.get(opIndex+1), num2))
                    , numIndex+2,
                    opIndex+2,
                    str + operand.get(opIndex) + "(" + num1+operand.get(opIndex+1) + num2 + ")");
        }

        return Math.max(result1, result2);

    }

    static int getResult(int n1, String op, int n2){
        //System.out.println(n1 + op + n2);
        if(op.equals("+")){
            return n1 + n2;
        }else if(op.equals("-")){
            return n1 - n2;
        }else{
            return n1 * n2;
        }
    }


}