import java.util.*;
import java.io.*;

public class Main{



    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String function = br.readLine();

        char[] operators = function.toCharArray();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Stack<String> op = new Stack<>();

        HashMap<String, Integer> priority = new HashMap<>();
        priority.put("+", 0);
        priority.put("-", 0);
        priority.put("*", 1);
        priority.put("/", 1);

        for(char o : operators){
            //System.out.println(o);
            if(o >= 'A' && o <= 'Z'){
                bw.write(o);
            }else{
                if(op.isEmpty()){
                    op.push(String.valueOf(o));
                }else{
                    if(o == '('){
                        op.push(String.valueOf(o));
                    }
                    else if(o == ')'){
                        while(true){
                            String pop = op.pop();
                            if (pop.equals("(")){
                                break;
                            }
                            bw.write(pop);
                        }
                    } else if(op.peek().equals("(")){
                        op.push(String.valueOf(o));
                    } else {
                        int topOp, nextOp;
                        topOp = priority.get(op.peek());
                        nextOp = priority.get(String.valueOf(o));

                        if(topOp >= nextOp){
                            while(topOp >= nextOp){
                                String pop = op.pop();
                                bw.write(pop);
                                if(op.isEmpty() || op.peek().equals("(")){
                                    break;
                                }
                                topOp = priority.get(op.peek());
                            }
                            op.push(String.valueOf(o));
                        }else{ //topOp <= nextOp
                            op.push(String.valueOf(o));
                        }

                    }
                }
            }
        }

        while(!op.isEmpty()){
            bw.write(op.pop());
        }
        bw.flush();
        bw.close();


    }
    // 후위표기식 특성상 괄호가 여러개 들어가면 어떻게 되나..? 를 중점으로 디버깅함
    // (A+B+C+D)
    // ((A+B)*C-D)*E
    // 같은 우선순위를 가진 연산자는 먼저 나온것이 먼저 수행되는 형태를 요구하기 때문에, 스택에 연산자를 넣을 때, 크거나 같다면 pop하는 방법으로 처리
    // 그리고 이런 경우, 우선순위가 같으면 계속해서 pop 해야하기 때문에, 연산자 스택이 비었거나, ( 여는 괄호가 나온다면 멈추도록함.

}
