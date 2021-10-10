// main method - takes in an integer array
// sets index 0 to a value called highest
// loops thorugh each element in array and checks if it is higher than the highest
// If it is, then it replaces highest with that value

public class Practice {
	public int findMaximum(int[] initArray) {
		int max = initArray[0];
		for (int x : initArray) {
			if (x > max) {
				max = x;
			}
		}	
		return max;

	}
	public static void main(String[] args) {
		Practice obj = new Practice();
		int[] myArray = new int[]{1, 7, 6, 4, 9, 14, 2, 0, -10};
		System.out.println(obj.findMaximum(myArray));
	}
}