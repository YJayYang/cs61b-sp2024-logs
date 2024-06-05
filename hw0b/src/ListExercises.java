import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListExercises {

    /** Returns the total sum in a list of integers */
	public static int sum(List<Integer> L) {
        // TODO: Fill in this function.
        int sum = 0;
        if (L.isEmpty()){
            return sum;
        }else {
            for (int elem : L) {
                sum += elem;
            }
        }
        return sum;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        // TODO: Fill in this function.
        List<Integer> new_L = new ArrayList<>();
        for (int elem : L){
            if (elem % 2 == 0){
                new_L.add(elem);
            }
        }
        return new_L;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        // TODO: Fill in this function.
        Set<Integer> set = new HashSet<>(L1);
        List<Integer> commonList = new ArrayList<>();

        for (Integer item : L2) {
            if (set.contains(item)) {
                commonList.add(item);
            }
        }

        return commonList;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        // TODO: Fill in this function.
    int count = 0;

        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == c) {
                    count++;
                }
            }
        }

        return count;

    }
}

