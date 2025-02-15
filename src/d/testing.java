package d;

import java.util.Random;

public class PrisonBreak {
    private int[] boxes; // To store box numbers

    // Constructor to initialize the boxes with prisoner numbers
    public PrisonBreak(int numPrisoners) {
        boxes = new int[numPrisoners];
        reset(); // Initialize the array to be ordered 0 to numPrisoners - 1
    }
    
    // Reset method to put the array in order
    public void reset() {
        for (int i = 0; i < boxes.length; i++) {
            boxes[i] = i;
        }
    }

    // Shuffle method to randomize the array
    public void shuffle() {
        Random rand = new Random();
        for (int i = 0; i < boxes.length; i++) {
            int randomIndex = rand.nextInt(boxes.length);
            int temp = boxes[i];
            boxes[i] = boxes[randomIndex];
            boxes[randomIndex] = temp;
        }
    }
    
    public boolean followSmartPath(int start, boolean show) {
        int currentBox = start;
        for (int i = 0; i < boxes.length / 2; i++) { // Prisoner can only open half of the boxes
            if (show) {
                System.out.println("Prisoner " + start + " opened box " + currentBox + " found " + boxes[currentBox]);
            }
            if (boxes[currentBox] == start) {
                return true; // Prisoner found their number
            }
            currentBox = boxes[currentBox]; // Follow the number in the current box
        }
        return false; // Prisoner failed to find their number
    }

    
    public boolean followRandomPath(int start, boolean show) {
        boolean[] opened = new boolean[boxes.length]; // To track opened boxes
        Random rand = new Random();
        int currentBox = start;
        
        for (int i = 0; i < boxes.length / 2; i++) {
            if (opened[currentBox]) {
                continue; // Skip if already opened
            }
            opened[currentBox] = true;
            if (show) {
                System.out.println("Prisoner " + start + " opened box " + currentBox + " found " + boxes[currentBox]);
            }
            if (boxes[currentBox] == start) {
                return true; // Prisoner found their number
            }
            currentBox = rand.nextInt(boxes.length); // Randomly choose the next box
        }
        return false; // Prisoner failed to find their number
    }

    
    public double simulate(int trials, boolean whichPath) {
        int successCount = 0;

        for (int i = 0; i < trials; i++) {
            shuffle(); // Shuffle before each trial
            boolean allFound = true;
            
            for (int prisoner = 0; prisoner < boxes.length; prisoner++) {
                boolean success = (whichPath) ? followSmartPath(prisoner, false) : followRandomPath(prisoner, false);
                if (!success) {
                    allFound = false;
                    break; // If one fails, stop
                }
            }
            
            if (allFound) {
                successCount++;
            }
        }
        
        return (double) successCount / trials * 100; // Return percentage of successful trials
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Prisoner Box Layout:\n");
        sb.append("--------------------\n");

        int columns = 10;  // Display 10 boxes per row
        for (int i = 0; i < boxes.length; i++) {
            sb.append(String.format("| %2d -> %2d |", i, boxes[i]));  // Format box and number

            if ((i + 1) % columns == 0) {
                sb.append("\n");  // Move to the next line after every 'columns' boxes
            }
        }

        sb.append("\n--------------------\n");
        return sb.toString();
    }



    private boolean simulateSmart() {
        for (int prisoner = 0; prisoner < boxes.length; prisoner++) {
            if (!followSmartPath(prisoner, false)) {
                return false; // If any prisoner fails, the trial fails
            }
        }
        return true; // All prisoners succeeded
    }

    
    // Additional helper methods like getters and setters
}
