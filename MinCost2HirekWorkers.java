import java.util.*;


public class MinCost2HirekWorkers {
    //  Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
    // -> group of workers share the same "cost[i]  / quality[i]", let's call it PAID_RATIO
    // -> PAID_RATIO = total cost / total quality

    // Every worker in the paid group must be paid at least their minimum wage expectation.
    // -> cost[i] >= wage[i]
    // -> cost[i] / quality[i] >= wage[i] / quality[i]
    // -> PAID_RATIO >= MAX RATIO(maximum wage[i] / quality[i] in the group)

    // cost = groupQuality * PAID_RATIO
    // -> groupQuality * MAX RATIO(maximum wage[i] / quality[i] in the group) 
    // -> minCost = Math.min(minCost, minimum groupQuality * minimum MAX RATIO)

    // get minimum groupQuality -> pop highest quality when size is bigger than k -> maxHeap 
    // track minimum MAX RATIO -> keep current ratio -> increse order workers list
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        List<Worker> workers = new ArrayList<>();

        for (int i = 0; i < quality.length; i++) {
            workers.add(new Worker(quality[i], wage[i]));
        }

        // Sort by ratio increasingly
        Collections.sort(workers, (a, b) -> Double.compare(a.ratio, b.ratio));

        // Maxheap always pop highest quality
        PriorityQueue<Worker> maxHeap = new PriorityQueue<>((a, b) -> (b.quality - a.quality));

        int totalQuality = 0;
        double minCost = Double.MAX_VALUE;
        for (Worker worker: workers) {
            maxHeap.offer(worker);
            totalQuality += worker.quality;

            if (maxHeap.size() > K) {
                // pop highest quality worker
                Worker removedWorker = maxHeap.poll();
                totalQuality -= removedWorker.quality;
            }

            if (maxHeap.size() == K) {
                // groupQuality * MAX RATIO(maximum wage[i] / quality[i] in the group) 
                minCost = Math.min(totalQuality * worker.ratio, minCost);
            }
        }

        return minCost;
    }
    // time: O(nlogn) - sort and for loop 
    // space: O(n) - List

    class Worker {
        int quality;
        int wage;
        double ratio;
        
        public Worker(int quality, int wage) {
            this.quality = quality;
            this.wage = wage;
            // be careful about int = int / int, double = double / int
            ratio = wage * 1.0 / quality;
        }
    }


    public static void main(String[] args) {
        MinCost2HirekWorkers mchw = new MinCost2HirekWorkers();
        System.out.println(mchw.mincostToHireWorkers(new int[]{10,20,5}, new int[]{70,50,30}, 2)); // should return 105.0
        MinCost2HirekWorkers mchw2 = new MinCost2HirekWorkers();
        System.out.println(mchw2.mincostToHireWorkers(new int[]{3,1,10,10,1}, new int[]{4,8,2,2,7}, 3)); // should return 30.66667
    }
}
