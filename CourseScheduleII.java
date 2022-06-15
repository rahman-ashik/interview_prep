import java.util.*;

public class CourseScheduleII {

    

    // The reason Topological sort is suitable for this problem is because 
    // certains nodes must come before other nodes (i.e. there is an ordering of nodes),
    //  and that's where Topological shines.
    // The basic idea of Topological sort is that we first choose the node 
    // that has no parent, record them, and remove them from the graph and 
    // update the graph accordingly, do this layer by layer, until there is 
    // no more nodes / no more valid path to choose from. More details can 
    // be found in the codes below.
    // Remember that it is possible that a graph doesn't have a valid topological 
    // sort path and it happens when there is a cycle such as 1 --> 0 and 0 --> 1.
    public int[] findOrder(int numCourses, int[][] prerequisites) { 
        if (numCourses == 0) return null;
        // Convert graph presentation from edges to indegree of adjacent list.
        int indegree[] = new int[numCourses], order[] = new int[numCourses], index = 0;
        for (int i = 0; i < prerequisites.length; i++) // Indegree - how many prerequisites are needed.
            indegree[prerequisites[i][0]]++;    
    
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) 
            if (indegree[i] == 0) {
                // Add the course to the order because it has no prerequisites.
                order[index++] = i;
                queue.offer(i);
            }
    
        // How many courses don't need prerequisites. 
        while (!queue.isEmpty()) {
            int prerequisite = queue.poll(); // Already finished this prerequisite course.
            for (int i = 0; i < prerequisites.length; i++)  {
                if (prerequisites[i][1] == prerequisite) {
                    indegree[prerequisites[i][0]]--; 
                    if (indegree[prerequisites[i][0]] == 0) {
                        // If indegree is zero, then add the course to the order.
                        order[index++] = prerequisites[i][0];
                        queue.offer(prerequisites[i][0]);
                    }
                } 
            }
        }
    
        return (index == numCourses) ? order : new int[0];
    }







    public static int[] findOrder2(int numCourses, int[][] prerequisites) {

        // we maintain a result array to store the final order
        int[] result = new int[numCourses];

        // we maintain a map array to store the prerequisites
        int[] preCnt = new int[numCourses];

        // an array to store the indegrees of each course
        List<Integer>[] nextSet = new List[numCourses];

        // to make sure all the courses get visited
        for(int i=0; i<numCourses; i++) nextSet[i] = new ArrayList<>();
    
        // iterate through the prerequisites array and 
        for(int[] p : prerequisites) {
            preCnt[p[0]]++;
            nextSet[p[1]].add(p[0]);
        }
    
        // 
        Queue<Integer> sourceNode = new LinkedList<Integer>();
        for(int i=0; i<numCourses; i++)
            if(preCnt[i] == 0)
                sourceNode.add(i);
        
        for(int i=0; i<numCourses; i++) {
            if(sourceNode.isEmpty())
                return new int[0];
            int n = sourceNode.poll();
            result[i] = n;
            for(int next : nextSet[n]) {
                preCnt[next]--;
                if(preCnt[next] == 0)
                    sourceNode.add(next);
            }
        }
        return result;
    }

    public int[] findOrder3(int numCourses, int[][] prerequisites) {
        // this doesn't when work on all preReq has to be fulfilled 
        // gives wrong answer for [[0,1],[0,2],[1,2]] => [2,0,1] instead of [2,1,0]
        Map<Integer, List<Integer>> map = new HashMap<>();
        Set<Integer> preReqCourses = new HashSet<>();

        for (int i = 0; i < numCourses; i++) { map.put(i, new ArrayList<>()); }

        for (int[] pre : prerequisites) {
            map.get(pre[1]).add(pre[0]);
            preReqCourses.add(pre[0]);
        }

        Set <Integer> nonPreReqCourses = new HashSet<>();

        for (int i = 0; i < numCourses; i++) {
            if (!preReqCourses.contains(i)) {
                nonPreReqCourses.add(i);
            }
        }

        System.err.println("map: " + map);
        System.err.println("preReqCourses: " + preReqCourses);
        System.err.println("nonPreReqCourses: " + nonPreReqCourses);

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> popped = new LinkedList<>();

        int [] result = new int[numCourses];

        for (int i : nonPreReqCourses) {
            queue.add(i);       
        }
        

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (visited.contains(cur)) { continue; }
            visited.add(cur);
            popped.add(cur);
            for (int next : map.get(cur)) {
                queue.add(next);
            }
            System.err.println("stack: " + queue + " visited: " + visited + " popped: " + popped);
        }


        if (visited.size() != numCourses) { result = new int[0]; }

        int i = 0;
        while (!popped.isEmpty()) {
            result[i++] = popped.poll();
        }

        return result;
    }


    //OOP approach: very slow
    public int[] findOrder4(int numCourses, int[][] prerequisites) {
        Set <Course> courses = new HashSet<>();
        for (int i = 0; i < numCourses; i++) courses.add(new Course(i));
        for (int[] prerequisite : prerequisites) {
            int mustTake = prerequisite[1] ;
            int prerequisiteOf = prerequisite[0];
            courses.forEach(course -> {
                if (course.id == prerequisiteOf) {
                    course.preReq.add(mustTake);
                }
            });
        }
         System.err.println("courses: " + courses);

        // iterate thru the courses and find the ones that have no prerequisites
        // and add them to a queue
        Queue<Course> queue = new LinkedList<>();
       // Queue<Integer> result = new LinkedList<>();
        for (Course course : courses) {
            if (course.preReq.size() == 0) {
                queue.offer(course);
            }
        }

        // start polling from the queue and add the courses to the result array
        // as long as the queue is not empty
        int[] result = new int[numCourses];
        int i = 0;

        
        while (!queue.isEmpty()) {
            System.err.println("queue: " + queue);
            System.err.println("courses: " + courses);
            Course course = queue.poll();
            result[i++] = course.id;
            for ( Course c : courses ) {
                if (c.preReq.contains(course.id)) {
                    c.preReq.remove(course.id);
                    if (c.preReq.size() == 0) {
                        queue.offer(c);
                    }
                }
            }
           
        }
        return i == numCourses ? result : new int[0];
    }

    // Indegree approach with Topological sort:
    public int[] findOrder5(int numCourses, int[][] prerequisites) {
      
          Map<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>();
          int[] indegree = new int[numCourses];
          int[] topologicalOrder = new int[numCourses];
      
          // Create the adjacency list representation of the graph
          for (int i = 0; i < prerequisites.length; i++) {
            int dest = prerequisites[i][0];
            int src = prerequisites[i][1];
            List<Integer> lst = adjList.getOrDefault(src, new ArrayList<Integer>());
            lst.add(dest);
            adjList.put(src, lst);
      
            // Record in-degree of each vertex
            indegree[dest] += 1;
          }
      
          // Add all vertices with 0 in-degree to the queue
          Queue<Integer> q = new LinkedList<Integer>();
          for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
              q.add(i);
            }
          }
      
          int i = 0;
          // Process until the Q becomes empty
          while (!q.isEmpty()) {
            int node = q.remove();
            topologicalOrder[i++] = node;
      
            // Reduce the in-degree of each neighbor by 1
            if (adjList.containsKey(node)) {
              for (Integer neighbor : adjList.get(node)) {
                indegree[neighbor]--;
      
                // If in-degree of a neighbor becomes 0, add it to the Q
                if (indegree[neighbor] == 0) {
                  q.add(neighbor);
                }
              }
            }
          }
      
          // Check to see if topological sort is possible or not.
          if (i == numCourses) {
            return topologicalOrder;
          }
      
          return new int[0];
    }



    public static void main(String[] args) {
        CourseScheduleII courseScheduleII = new CourseScheduleII();
        int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};
        int[] result = courseScheduleII.findOrder(4, prerequisites);
        for(int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " => ");
        }
        // System.err.println("\n===========================");
        // prerequisites = new int[][]{{1,0},{3,1},{3,2}};
        // result = courseScheduleII.findOrder(4, prerequisites);
        // for(int i = 0; i < result.length; i++) {
        //     System.out.print(result[i] + " => ");
        // }
        // System.err.println("\n===========================");
        // prerequisites = new int[][]{};
        // result = courseScheduleII.findOrder(2, prerequisites);
        // for(int i = 0; i < result.length; i++) {
        //     System.out.print(result[i] + " => ");
        // }
        // System.err.println("\n===========================");
        // System.err.println();
        // prerequisites = new int[][]{{0,1}};
        // result = courseScheduleII.findOrder(2, prerequisites);
        // for(int i = 0; i < result.length; i++) {
        //     System.out.print(result[i] + " => ");
        System.err.println("\n===========================");
        System.err.println();
        prerequisites = new int[][]{{0,1}, {1,2} ,{0,2}};
        result = courseScheduleII.findOrder(3, prerequisites);
        for(int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " => ");
        }
    }
}

class Course {
    int id;
    Set<Integer> preReq;
    boolean taken;
    Course(int id, Set<Integer> preReq) {
        this.id = id;
        this.preReq = preReq;
        this.taken = false;
    }
    Course(int id) {
        this.id = id;
        this.preReq = new HashSet<Integer>();
        this.taken = false;
    }
    @Override
    public String toString() {
        String PreReqs = "";
        for (Integer p: preReq) {
            PreReqs += p+ " ";
        }
        PreReqs += "}";
        return "\n\tCourse{" +
                " id=" + id +
                ", preReq=" + PreReqs  ;
    }
}
