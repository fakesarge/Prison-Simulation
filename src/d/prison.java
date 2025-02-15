package d;

import java.util.Arrays;
import java.util.Random;

public class PrisonBreak {
	private int prisoners;
	private int[] boxes	,BoxesClone;
	
	/**
	 * Initial Constructor For PrisonBreak
	 * @param numPrisoners, The number of prisoners choosen by the user. 
	 */
    public PrisonBreak(int numPrisoners) {
        boxes = new int[numPrisoners];
        reset(); // Initialize the array to be ordered 0 to numPrisoners - 1
    }
    
	@Override
	public String toString() {
	    String result = "Prisoner Box Layout:\n";
	    result += "--------------------\n";

	    int columns = 10;  // Number of boxes per row
	    for (int i = 0; i < boxes.length; i++) {
	        result += "| " + i + " -> " + boxes[i] + " |";
	        
	        // Add a newline every 'columns' boxes to form a grid
	        if ((i + 1) % columns == 0) {
	            result += "\n";
	        }
	    }

	    result += "\n--------------------\n";
	    return result;
	}



	public int getPrisoners() {
		return prisoners;
	}

	public void setPrisoners(int prisoners) {
		this.prisoners = prisoners;
	}
    
    /**
     * Resets the array to be in order
     */
    public void reset() {
        for (int i = 0; i < boxes.length; i++) {
            boxes[i] = i;
        }
    }
    
    /**
     * Follows the random Path 
     * @param start, The prisoner that starts the path
     * @param show, If you want to see the steps of the path.
     * @return Returns true if the prisoner found its path.
     */
	public boolean FollowRandomPath(int start,boolean show) 
	{	
		BoxesClone = boxes.clone();
		for(int i = 0; i<(prisoners/2);i++) 
		{
			if (show) {
                System.out.println("Prisoner " + " opened box " + i + " found " + boxes[BoxesClone[i]-1]);
            }
			//System.out.println("Opened Box #"+BoxesClone[i]+" Got #"+(boxes[BoxesClone[i]-1]));
			if(start == boxes[BoxesClone[i]-1]) {//System.out.println("\nFound My Number");
			return true;
			}
		}
		return false;
	}
	
	/**
	 * Follows Smart Path
	 * @param start, the prisoner that starts the path
	 * @param show, if you want to see the steps followed by the prisoner.
	 * @return
	 */
	public boolean followSmartPath(int start,boolean show) 
	{	
		int pick = start;
		for(int i = 0; i<(prisoners/2);i++) 
		{
			if (show) {
                System.out.println("Prisoner " + start + " opened box " + pick + " found " + boxes[pick]);
            }
            if (boxes[pick] == start) {
                return true; // If Prisoners found their number
            }
            pick = boxes[pick]; // Follows the number in the current box
		}
		return false;
	}	
	
	public double simulate(int trials, boolean whichPath) 
	{
        int successCount = 0;

        for (int i = 0; i < trials; i++) {
        	shuffle(); // Shuffle before each trial
            boolean allFound = true;
        	
            for (int prisoner = 0; prisoner < boxes.length; prisoner++) {
                boolean success = (whichPath) ? followSmartPath(prisoner, false) : FollowRandomPath(prisoner, false);
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


	
	/**
	 * Randomly Shuffles Boxes Array
	 */
	public void shuffle() {  
			int rand = (int)(Math.random()*boxes.length); 
	        for (int i = boxes.length - 1; i > 0; i--) {  
	           
	            int j = (int)(Math.random()*i); ;  
	  	            int temp = boxes[i];  
	  	        boxes[i] = boxes[j];  
	  	        boxes[j] = temp;  
	        }  
	    }  
	



}

