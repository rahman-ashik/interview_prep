import java.util.*;

/*
You are visiting a farm that has a single row of fruit trees arranged from left to right. 
The trees are represented by an integer array fruits where fruits[i] is the type of fruit 
the ith tree produces.
You want to collect as much fruit as possible. However, the owner has some strict rules 
that you must follow:

You only have two baskets, and each basket can only hold a single type of fruit. 
There is no limit on the amount of fruit each basket can hold.
Starting from any tree of your choice, you must pick exactly one fruit from every 
tree (including the start tree) while moving to the right. The picked fruits must 
fit in one of your baskets.
Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
Given the integer array fruits, return the maximum number of fruits you can pick.

*/



// Given an integer array, return the length of the longest subarray 
// that can be formed by exactly two integers





public class FruitIntoBasket {
    public int totalFruit(int[] tree) {
        // map to store the number of each fruit
        Map<Integer, Integer> count = new HashMap<Integer, Integer>();

        // sliding window
        int res = 0, i = 0;
        for (int j = 0; j < tree.length; ++j) {
            // add the fruit to the map
            count.put(tree[j], count.getOrDefault(tree[j], 0) + 1);

            // if the number of fruits in the map is greater than 2,
            while (count.size() > 2) {
                // remove the first fruit
                count.put(tree[i], count.get(tree[i]) - 1);
                // if the number of the fruit is 0, remove it from the map
                if (count.get(tree[i]) == 0) count.remove(tree[i]);
                i++;
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }


    public static void main(String[] args) {
        FruitIntoBasket fruitIntoBasket = new FruitIntoBasket();
        int[] fruits = {1,2,1};
        System.out.println(fruitIntoBasket.totalFruit(fruits));
        int [] fruits2 = {0,1,2,2};
        System.out.println(fruitIntoBasket.totalFruit(fruits2));
        int [] fruits3 = {1,2,3,2,2};
        System.out.println(fruitIntoBasket.totalFruit(fruits3));
        int [] fruits4 = {3,3,3,1,2,1,1,2,3,3,4};
        System.out.println(fruitIntoBasket.totalFruit(fruits4)); // 5

    }
    
}





