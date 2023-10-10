import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
  // 퀵 정렬
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(in.readLine());
        for(int i = 0; i< n; i++){
            arr[i]= Integer.parseInt(st.nextToken());
        }
        quickSort(arr, 0, n-1, k-1);
        System.out.println(arr[k-1]);
    }

    public static void quickSort(int[] arr, int i, int j, int k){
        if(i < j){
            int pivot = partition(arr, i, j);
            if(pivot == k){
                return;
            }
            else if(k < pivot){
                quickSort(arr, i, pivot - 1, k);
            }else{
                quickSort(arr, pivot+1, j, k);
            }
        }
    }

    public static int partition(int[] arr, int S, int E){
        if (S +1 == E){
            if (arr[S] > arr[E]){
                swap(arr, S, E);
            }
            return E;
        }

        int m = (S + E)/2;
        swap(arr, S, m);
        int pivot = arr[S];
        int i = S+1, j = E;

        while(i <= j){
            while (j >= S+1 && pivot < arr[j]){
                j--;
            }
            while (i <= E && pivot > arr[i]){
                i++;
            }
            if(i<=j){
                swap(arr, i++, j--);
            }
        }

        arr[S] = arr[j];
        arr[j] = pivot;
        return j;
    }

    public static void swap (int arr[], int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
