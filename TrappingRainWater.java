public class TrappingRainWater {
    public int trap(int[] height) {
        int total = 0;

        // sliding window (sqeeze)
        int left  = 0;
        int right = height.length-1;

        while (left<right) { // sqeeze until left and right meet

            // find the smaller height
            if( height[left]<height[right] ) {

                
                int lower = height[left];
                // squeeze from left while the left height is smaller than the lower
                while(  left < right && 
                        height[left] <= lower ) {
                    // accumulate the water in every sqeezed height 
                    int currentCollection = lower - height[left];
                    total += currentCollection;
                    left++ ;
                }
            } else {
                int lower = height[right];
                while(  left < right && 
                        height[right] <= lower ) {
                    int currentCollection = lower - height[right];
                    total += currentCollection;
                    right-- ;
                }
            }
        }
        return total;
    }

    public static void main(String[] args) {
        TrappingRainWater f = new TrappingRainWater();
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(f.trap(height));
        height = new int[]{4,2,0,3,2,5};
        System.out.println(f.trap(height));

    }
}
