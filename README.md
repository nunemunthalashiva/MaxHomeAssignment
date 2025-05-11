# Robot State Simulator

This Java program simulates the movement of a robot within a bounded grid. The robot can rotate left (`L`), rotate right (`R`), or move forward based on a sequence of commands. The final state of the robot (coordinates and direction) is printed after processing the input instructions.

## Assumptions

- **Invalid Operation**:  
  If any character in the command sequence is not `'L'`, `'R'`, or `'M'`, the program throws an `IllegalArgumentException`.

- **Out of Bounds**:  
  If a move causes the robot to cross the grid boundary, the program throws an `IllegalStateException`.

## How to Compile and Run

```bash
javac assignment.java
java MaxHomeAssignment
