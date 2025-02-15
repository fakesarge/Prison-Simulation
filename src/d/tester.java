package d;

import java.util.Arrays;

public class tester {
	    public static void main(String[] args) {
	        PrisonBreak pb = new PrisonBreak(100); // Create with 100 prisoners
	        
	        pb.shuffle();  // Shuffle without any arguments
	        System.out.println(pb.toString());  // Show the boxes
	        
	        // Simulate using the smart path strategy
	        double successRate = pb.simulate(1000, true);
	        System.out.println("Smart Path Success Rate: " + successRate + "%");  // Print the success rate

	        // Simulate using the random path strategy
	        successRate = pb.simulate(1000, false);  // No need to redeclare successRate
	        System.out.println("Random Path Success Rate: " + successRate + "%");  // Print the success rate
	    }
	}



