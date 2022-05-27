public class ContainerWithMostWater {
    public static int maxArea(int[] height) {
        int max = 0;

        int start = 0;
        int end = height.length - 1;

        // slide and sqeeze the two pointers to find the max area
        while (start < end) {
            // getting area with the formula: 
            // Area = length of shorter vertical line * distance between lines
            int  currArea = getArea(height, start, end); 
            max = Math.max(max, currArea);

            // if start is lower than end, sqeeze from the start 
            if (height[start] < height[end]) {
                start++;
            } else { // if end is lower than start, sqeeze from the end
                end--;
            }
        }
        return max;
    }

    public static int getArea(int[] height, int i, int j) {
        int min = Math.min(height[i], height[j]);
        int area = min * (j - i);
        return area;
    }



    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea(height));
        
    }
}
