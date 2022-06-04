/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */


 class Robot {
    private boolean isMoving = false;
    public boolean move() {
        isMoving = !isMoving;
        return isMoving;
    }
    public void turnLeft() {
        System.err.println("turnLeft");
    }
    
    public void turnRight() {
        System.err.println("turnRight");
    }
    public void clean()  {
        System.err.println("cleaning");
    }
 }

public class RobotVaccume {
    public void cleanRoom(Robot robot) {
        
        
    }

    public static void main(String[] args) {
        Robot robot = new Robot();
        RobotVaccume robotVaccume = new RobotVaccume();
        robotVaccume.cleanRoom(robot);
    }
}
