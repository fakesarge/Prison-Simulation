package d;

public class PrisonBreak {
	private int prisoners;
	private int[] boxes	,BoxesClone;
	
	/**
	 * Initial Constructor For PrisonBreak
	 * @param numPrisoners, The number of prisoners chosen by the user. 
	 */
    public PrisonBreak(int numPrisoners) {
        boxes = new int[numPrisoners];
        this.prisoners = numPrisoners;
        reset(); // Initialize the array to be ordered 0 to numPrisoners - 1
    }
    
	@Override
	public String toString() {
	    String result = "Prisoner Box Layout:\n";
	    result += "--------------------\n";

	    int columns = 10;  // Number of boxes per row
	    for (int i = 0; i < boxes.length; i++) {
	        result += "| " + i + " -> " + boxes[i] + " |";
	  	        if ((i + 1) % columns == 0) {
	            result += "\n";
	        }
	    }

	    result += "\n--------------------\n";
	    return result;
	}


	
	/**
	 * Gets the number of prisoners
	 * @return
	 */
	public int getPrisoners() {
		return prisoners;
	}

	/**
	 * Sets the number of Prisoners
	 * @param prisoners
	 */
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
			    System.out.println("Prisoner " + start + " opened box " + i + " found " + BoxesClone[i]);
			}
			if (start == BoxesClone[i]) {
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
	
	/**
	 * Simulates the whole experiment 
	 * @param trials, the number of trials 
	 * @param whichPath, True for Smart Path, False for Random Path
	 * @return
	 */
	public double simulate(int trials, boolean whichPath) 
	{
		boolean path;
        int successCount = 0;

        for (int i = 0; i < trials; i++) {
        	shuffle(); // Shuffle before each trial
            boolean allFound = true;
        	
            for (int prisoner = 0; prisoner < boxes.length; prisoner++) {
            	if (whichPath) 
            	{
            		path = followSmartPath(prisoner, false);
            	}
            	else 
            	{
            		path = FollowRandomPath(prisoner, false);
            	}
                if (!path) {
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


