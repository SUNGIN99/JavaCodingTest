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

}