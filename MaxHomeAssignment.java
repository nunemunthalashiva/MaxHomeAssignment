import java.util.*;

class State {
    private int xCoordinate, yCoordinate;
    private int xdir, ydir;
    private int maxX, maxY;

    /*
      Rotation matrix for 90 degree anti-clockwise: [-y, x]
      Rotation matrix for 90 degree clockwise: [y, -x]
    */

    public void updateState(char operation) {
        if (operation == 'L') {
            int newXdir = -ydir;
            int newYdir = xdir;
            xdir = newXdir;
            ydir = newYdir;
        } else if (operation == 'R') {
            int newXdir = ydir;
            int newYdir = -xdir;
            xdir = newXdir;
            ydir = newYdir;
        } else if (operation == 'M'){
            int newX = xCoordinate + xdir;
            int newY = yCoordinate + ydir;
            if (isValidState(newX, newY, maxX, maxY)) {
                xCoordinate = newX;
                yCoordinate = newY;
            } else {
                throw new IllegalStateException("Object trying to move to an invalid state");
            }
        }else{
          throw new IllegalArgumentException("Invalid direction " + operation + " found.");
        }
    }

    public char getDirection() {
        if (xdir == 1 && ydir == 0) {
          return 'E';
        }else if (xdir == -1 && ydir == 0) {
          return 'W';
        }
        else if (xdir == 0 && ydir == 1) {
          return 'N';
        }else{
          return 'S';
        }
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void updateState(String sequenceOfOperations) {
        for (char c : sequenceOfOperations.toCharArray()) {
            updateState(c);
        }
    }

    public State(int x, int y, int xdir, int ydir, int maxX, int maxY) {
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.xdir = xdir;
        this.ydir = ydir;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public static boolean isValidState(int newX, int newY, int upX, int upY) {
        return newX >= 0 && newX <= upX && newY >= 0 && newY <= upY;
    }

    public static int[] getDirectionVector(char dir) {
        if (dir == 'N') {
            return new int[]{0, 1};
        } else if (dir == 'S') {
            return new int[]{0, -1};
        } else if (dir == 'E') {
            return new int[]{1, 0};
        } else if (dir == 'W') {
            return new int[]{-1, 0};
        } else {
            throw new IllegalArgumentException("Invalid direction " + dir + " found.");
        }
    }
}

public class MaxHomeAssignment {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int maxX = sc.nextInt();
        int maxY = sc.nextInt();
        sc.nextLine();

        while (sc.hasNextLine()) {
            try {
                String line = sc.nextLine();
                if (line.trim().isEmpty()) continue;

                String[] parts = line.trim().split(" ");
                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);
                char dir = parts[2].charAt(0);
                String sequenceOfOperations = sc.nextLine().trim();

                int[] directionVector = State.getDirectionVector(dir);
                State currState = new State(x, y, directionVector[0], directionVector[1], maxX, maxY);
                currState.updateState(sequenceOfOperations);
                System.out.println(currState.getXCoordinate() + " " + currState.getYCoordinate() + " " + currState.getDirection());
            } catch (Exception e) {
                System.out.println("Exception occurred, Reason: " + e);
                break;
            }
        }
        sc.close();
    }
}
