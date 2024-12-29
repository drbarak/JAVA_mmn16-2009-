import java.util.Arrays;
/**
 * A class to store methods solving exercise 16 semester 2009א
 *
 * @author (Zvika Barak)
 * @version (28.12.2024)
 */
public class Ex16
{
    private static boolean p = false;
    /**
     * findMinDifference - find the pair of members of an array of integers
     * whose absolute diference is minimal
     *
     * @param  int[] nums  the array with the integers
     * 
     * @return int[]   an array with 5 members (min difference, 1st number index,
     *                  1st number value, 2nd number index, 2nd number value  
     */
    public static int[] findMaxDifference(int[] nums)
    {
        return findDifference(nums, true);
    }
    /**
     * findMinDifference - find the pair of members of an array of integers
     * whose absolute diference is minimal
     *
     * @param  int[] nums  the array with the integers
     * 
     * @return int[]   an array with 5 members (min difference, 1st number index,
     *                  1st number value, 2nd number index, 2nd number value  
     */
    public static int[] findMinDifference(int[] nums)
    {
        return findDifference(nums, false);
    }
    private static int[] findDifference(int[] nums, boolean findMax)
    {
        if (nums.length < 2) return new int[] {-1,-1,-1,-1,-1};
        // step 1: find min and max of array
        int min = 0;
        int max = 0;
        int i;
        for (i=0; i<nums.length;i++)
        {
            if (nums[min] > nums[i]) min = i;
            if (nums[max] < nums[i]) max = i;
        }
        if (p) Print.p(min, nums[min], max, nums[max]);
        // step 2: create array of differnces with min, size of max-min+1
        boolean[] diff = new boolean[nums[max]-nums[min]+1];
        int[] diffIndex = new int[diff.length]; // to save the index of the number
        int index;
        for (i=0; i<nums.length;i++)
        {
            index = (findMax? nums[max]-nums[i] : nums[i]-nums[min]);
            //Print.p(i, index, nums[i], nums[min]);
            //Print.p(diff.length);
            if (diff[index])    // found duplicates so the minDiff = 0
            {
                int j;
                for (j=0; j<i; j++)
                    if (nums[j] == nums[i]) break;
                return new int[] {0, i, nums[i], j, nums[j]};
            }
            diff[index] = true;
            diffIndex[index] = i;
        }
        if (p) Print.p(Arrays.toString(diff));
        if (p) Print.p(Arrays.toString(diffIndex));
        //Step 3: find min/max differences
        int minDiff = (findMax ? Integer.MIN_VALUE : Integer.MAX_VALUE);
        int prevIndex = -1, minIndex = -1;
        int d = -1;
        int firstIndex = diffIndex[0]; // for finding max
        int secondIndex = diffIndex[diff.length-1];
        if (findMax) // for finding max item 0 points tothe maximum because we subtract from the max
        {
            minIndex = 0;   // the largest number in the array
            minDiff = diff.length - 1;  // the smallest number in the array
        }
        else
        {
            for (i=0; i<diff.length; i++)
            {
                if (prevIndex != -1 && diff[i])
                {
                    d = i - prevIndex;
                    if (!findMax && d < minDiff)
                    {
                        minDiff = d;
                        minIndex = diffIndex[i];
                    }
                    else if (findMax && d > minDiff)
                    {
                        minDiff = d;
                        //minIndex = diffIndex[prevIndex];//
                    }
                    prevIndex = i;
                }
                else if (prevIndex == -1)
                    prevIndex = i;
                if (p) Print.p(i, d, minDiff, minIndex);   
            }
            firstIndex = minIndex - minDiff;
            secondIndex = minIndex;
        }
        if (p) Print.p(minDiff, minIndex);
        if (p) Print.p(firstIndex, secondIndex);
        return new int[] {minDiff, firstIndex, nums[firstIndex], secondIndex, nums[secondIndex]};
    }
}
