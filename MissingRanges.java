import java.util.*;

public class MissingRanges {
    //  Find the range between lower and first element in the array.
    //  Find ranges between adjacent elements in the array.
    //  Find the range between upper and last element in the array.
    public static List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> list = new ArrayList<String>();

        for(int n : nums){
            int justBelow = n - 1;

            if(lower == justBelow) 
                list.add(lower+"");
            else if(lower < justBelow) 
                list.add(lower + "->" + justBelow);

            lower = n+1;
        }

        // edge case for lower bound
        if(lower == upper) 
            list.add(lower+"");
        else if(lower < upper) 
            list.add(lower + "->" + upper);


        return list;
    }


    public static void main(String[] args) {
        int[] nums = {0, 1, 3, 50, 75}; // ["2","4->49","51->74","76->99"]
        int lower = 0;
        int upper = 99;
        List<String> res = findMissingRanges(nums, lower, upper);
        for (String s : res) {
            System.out.println(s);
        }

        System.out.println("\n");
        
    }
}
