public class MaximizeDistanceToClosestPerson {
    public int maxDistToClosest(int[] seats) {
        int n = seats.length;
        int empty = 0;
        int result = 0;
        int idx1 = -1, idx2 = -1;
        
        for(int i = 0; i < n; ++i){

            // if a seat is full, reset the empty counter
            if(seats[i] == 1){
                empty = 0;
                if(idx1 == -1) idx1 = i; // updating for left most "1", it gets updated only once
                idx2 = i; // updating for right most "1", it gets updated only when a new "1" is found
            } else {
                empty++;
                result = Math.max(result, (empty+1)/2);
            }
        }

        // last and first seats are always subject to edge cases. i.e: {0,0,0,0,0,1,0,0,0,0}
        result = Math.max(result, Math.max(idx1, n-1-idx2));
        return result;
    }





    public static void main(String[] args) {
        MaximizeDistanceToClosestPerson f = new MaximizeDistanceToClosestPerson();
        int[] seats = {1,0,0,0,1,0,1};
        System.out.println(f.maxDistToClosest(seats)); // 2
        seats = new int[]{1,0,0,0};
        System.out.println(f.maxDistToClosest(seats)); // 3
        seats = new int[]{0,0,0,0,0,1,0,0,0,0};
        System.out.println(f.maxDistToClosest(seats)); // 1
    }
}
