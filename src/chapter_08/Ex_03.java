package chapter_08;

public class Ex_03 {
	public static void main(String[] agrs) {
		 // Students' answers to the questions
	    char[][] answers = {
	      {'A', 'B', 'A', 'C', 'C', 'D', 'E', 'E', 'A', 'D'},
	      {'D', 'B', 'A', 'B', 'C', 'A', 'E', 'E', 'A', 'D'},
	      {'E', 'D', 'D', 'A', 'C', 'B', 'E', 'E', 'A', 'D'},
	      {'C', 'B', 'A', 'E', 'D', 'C', 'E', 'E', 'A', 'D'},
	      {'A', 'B', 'D', 'C', 'C', 'D', 'E', 'E', 'A', 'D'},
	      {'B', 'B', 'E', 'C', 'C', 'D', 'E', 'E', 'A', 'D'},
	      {'B', 'B', 'A', 'C', 'C', 'D', 'E', 'E', 'A', 'D'},
	      {'E', 'B', 'E', 'C', 'C', 'D', 'E', 'E', 'A', 'D'}};

	    // Key to the questions
	    char[] keys = {'D', 'B', 'D', 'C', 'C', 'D', 'A', 'E', 'A', 'D'};
	    
	    int[][] scores = new int[answers.length][2];
	    
	    // Assign the students' number. 
	    for (int i = 0; i < scores.length; i++) 
	    	scores[i][0] = i;
	    
	    // Grading students
	    for (int i = 0; i < answers.length; i++) {
	    	int correctCount = 0;
	    	for (int j = 0; j < answers[i].length; j++) {
	    		if (answers[i][j] == keys[j])
	    			correctCount++;
	    	}
	    	scores[i][1] = correctCount;
	    }
	    
	    /*
	    // Sorting the scores in ascending order using Insertion Sort.
	    for (int i = 1; i < scores.length; i++) {
	    	int currentStudent = scores[i][0];
	    	int currentScore = scores[i][1];
	    	int k;
	    	for (k = i - 1; k >= 0 && scores[k][1] > currentScore; k--) {
	    		scores[k+1][0] = scores[k+1][0];
	    		scores[k+1][1] = scores[k+1][1];
	    	}
	    	scores[k+1][0] = currentStudent;
	    	scores[k+1][1] = currentScore;
	    }
	    */
	    
	    // Sorting the scores in ascending order using Bubble Sort.
	    for (int i = 0; i < scores.length-1; i++) {
	    	for (int j = i + 1; j < scores.length; j++) {
	    		if (scores[i][1] > scores[j][1]) {
	    			int tempStudent = scores[i][0], 
	    				tempScore = scores[i][1];
	    			scores[i][0] = scores[j][0];
	    			scores[i][1] = scores[j][1];
	    			scores[j][0] = tempStudent;
	    			scores[j][1] = tempScore;
	    		}
	    	}
	    }
	    
	    // Printing the scores
	    for (int i = 0; i < scores.length; i++) {
	    	System.out.println("Student " + scores[i][0] + "'s score is " + scores[i][1]);
	    }
	}

}
