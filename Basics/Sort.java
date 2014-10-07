
public class Sort {

	public static void select_sort(double[] nums) {
		for(int i = 0; i < nums.length; i++) {
			int min = i;
			for(int j = i + 1; j < nums.length; j++)
				if(nums[j] < nums[min]) min = j;
			double tmp;
			tmp = nums[i];
			nums[i] = nums[min];
			nums[min] = tmp;
		}
	}

}