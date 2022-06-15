import java.util.*;

public class fruitsIntoBasket {
    int countFruits(char[] fruits, int k) {
        int n = fruits.length;
        int maxLen = 0;
        Map<Character, Integer> fruitCount = new HashMap<>();

        int left = 0 ; 
        int right = 0 ;

        while (right<n) {
            Character currentFruit = fruits[right] ; 
            int currentFruitCount = fruitCount.getOrDefault(currentFruit, 0);
            fruitCount.put(currentFruit, currentFruitCount+1);

            while ( fruitCount.size() > k ) {

                Character leftmostFruit = fruits[left];
                int leftmostFruitCount = fruitCount.get(currentFruit);
                fruitCount.put(leftmostFruit, leftmostFruitCount-1);

                if (fruitCount.get(leftmostFruit)==0) {
                    fruitCount.remove(leftmostFruit);
                }

                left++;
            }
            maxLen = Math.max( maxLen , right - left + 1 ) ;
            right++ ;
        }
        return maxLen;

    }



    public static void main(String[] args) {
        System.out.println(new fruitsIntoBasket().countFruits(new char[] {'a', 'b', 'c', 'a', 'b', 'c'}, 3)); // 3
        System.out.println(new fruitsIntoBasket().countFruits(new char[] {'a', 'b', 'c', 'a', 'b', 'c'}, 2)); // 2
        System.out.println(new fruitsIntoBasket().countFruits(new char[] {'b', 'a', 'x', 'a', 'b', 'b'}, 2)); // 5
        
        
    }
}
