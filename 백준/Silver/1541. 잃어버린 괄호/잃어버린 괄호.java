import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String cal = sc.nextLine();

        String[] str = cal.split("-");

        int result = 0;
        for (int i = 0; i < str.length; i++) {
            int temp = mySum(str[i]);

            if (i == 0){
                result += temp;
            }else{
                result -= temp;
            }
        }
        System.out.println(result);

    }
    public static int mySum(String formula){
        String[] nums = formula.split("[+]");
        int sum = 0;
        for(int i = 0; i<nums.length; i++){
            sum += Integer.parseInt(nums[i]);
        }
        return sum;
    }



}