import java.util.ArrayList;
import java.util.List;

public class JavaExercises {

    /** Returns an array [1, 2, 3, 4, 5, 6] */
    public static int[] makeDice() {
        // TODO: Fill in this function.
        int [] J_array = new int[6];
        int i = 1;
        while (i <= 6){
            J_array[i-1] = i;
            i++;
        }



        return J_array;
    }

    /** Returns the order depending on the customer.
     *  If the customer is Ergun, return ["beyti", "pizza", "hamburger", "tea"].
     *  If the customer is Erik, return ["sushi", "pasta", "avocado", "coffee"].
     *  In any other case, return an empty String[] of size 3. */
    public static String[] takeOrder(String customer) {
        // TODO: Fill in this function.
        String c1 = "Ergun";
        String c2 = "Erik";
        int size = 3;
        String[] order_1 = {"beyti", "pizza", "hamburger", "tea"};
        String[] order_2 = {"sushi", "pasta", "avocado", "coffee"};
        String[] empty = new String[size];

        if ( c1.equals(customer)) {
            return order_1;
        }else if (c2.equals(customer)) {
            return order_2;
        }else {
            return empty;
        }

    }

    /** Returns the positive difference between the maximum element and minimum element of the given array.
     *  Assumes array is nonempty. */
    public static int findMinMax(int[] array) {
        // TODO: Fill in this function.
        int temp;

        for(int i=0;i<array.length-1;i++){//外层循环控制排序趟数
            for(int j=0;j<array.length-1-i;j++){//内层循环控制每一趟排序多少次
                if(array[j]>array[j+1]){
                    temp=array[j];
                    array[j]=array[j+1];
                    array[j+1]=temp;
                }
            }
        }
        return array[array.length - 1] - array[0];
    }

    /**
      * Uses recursion to compute the hailstone sequence as a list of integers starting from an input number n.
      * Hailstone sequence is described as:
      *    - Pick a positive integer n as the start
      *        - If n is even, divide n by 2
      *        - If n is odd, multiply n by 3 and add 1
      *    - Continue this process until n is 1
      */
    public static List<Integer> hailstone(int n) {
        return hailstoneHelper(n, new ArrayList<>());
    }

    private static List<Integer> hailstoneHelper(int x, List<Integer> list) {
        // TODO: Fill in this function.
        list.add(x);
        while (x != 1){
            if (x % 2 == 0){
                x /= 2;
                list.add(x);
            }else {
                x = x * 3 + 1;
                list.add(x);
            }
        }
        return list;
    }

}
