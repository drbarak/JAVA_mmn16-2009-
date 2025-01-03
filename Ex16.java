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
    
    public static int[] sort(int[] nums, boolean descending)
    {
        if (nums.length < 2) return nums;
        // step 1: find min and max of array O(n)
        int min = nums[0], max = min;
        for (int num: nums)
        {
            if (min > num) min = num;
            if (max < num) max = num;
        }
        // step 2: create an array of differnces with min, size of max-min+1
        int[] frequency = new int[max - min + 1];
        for (int num : nums)
            frequency[descending? max-num : num-min]++;
        //Step 3: sort
        int[] sorted = new int[nums.length];
        int index = 0;
        for (int i=0; i<frequency.length; i++)
        {        // Adjust value based on sort direction
            int value = descending ? max - i : min + i;
            while (frequency[i] > 0)
            {
                sorted[index++] = value;
                frequency[i]--;
            }
        }
        return sorted;
    }
    public static int[] sortWithPrinting(int[] nums, boolean descending) // with modifications for clarity and bravity from chatGPT
    {
        if (nums.length < 2) return nums;
        // step 1: find min and max of array O(n)
        int min = nums[0];
        int max = min;
        for (int num: nums)
        {
            if (min > num) min = num;
            if (max < num) max = num;
        }
        if (p) Print.p(min, max);
        // step 2: create an array of differnces with min, size of max-min+1
        int[] frequency = new int[max - min + 1];
        for (int num : nums)
            frequency[descending? max-num : num-min]++;
        if (p) Print.p(frequency);
        //Step 3: sort
        int[] sorted = new int[nums.length];
        int index = 0;
        for (int i=0; i<frequency.length; i++)
        {
            int value = descending ? max - i : min + i; // Adjust value based on sort direction
            while (frequency[i] > 0)
            {
                sorted[index++] = value;
                frequency[i]--;
            }
        }
        return sorted;
    }
    /**
     * find - find whether a given number x is one of the members of a 2-dim
     * square array, sorted such that each squares of 4 numbers is ordered so
     * that  mat[0][0]<=mat[0][1]<=mat[1][0]<=mat[1][1]
     *  and the member mat[1][1] of one square is <= of mat[0][0] of its 
     *  neigbour square, and the last square in a row maintain this property
     *  in relation to the 1st square of the next row.
     *  It means that the smallest number is in mat[0][0] and the largest number
     *  is in mat[n][n] where n is the size of each 1-dim array of mat
     *  
     *  the numbers of squares in mat is (n/2)*(n/2) = n*n/4
     *
     * @param  int[][] mat  the array with the integers
     * 
     * @return boolean  true if found in mat and false otherwise
     *
     * The worst case is when the number is not found and becuase we are using
     * binary search then the max number of searches is log(k) where k is the
     * length of the sorted array, which in our case is k=n*n/4 and thus
     * log(n*n/4) = 2log(n)-2 (the log is base 2)
     * Time complexity = O(log(n))
     */
    public static boolean find(int[][] mat, int x)
    {
        // verify that x is within the matrix, assuming the matrix is sorted
        if (x > mat[mat.length-1][mat.length-1]) return false;
        if (x < mat[0][0]) return false;
        // perform a binary search on the number in the edge of each square
        // which is the largest number in the square and they are sorted already
        // for example, if the matrix of 4X4 is
        // int[][] mat = {{-4,-2,5,9},{2,5,12,13},{13,20,25,25},{22,24,49,57}};
        // then the largest number in each sqaure are in even rows and even col
        // if we start counting from 1 but since the matrix starts at 0,0
        // the rows/col are odd, starting from 1,1 to 3,3
        // so in this example there are 5,13,24,57
        // once we find the proper square we look within it to see if we find
        // the number
        int numSquares = mat.length;
        // Each sqaure consists of 4 cells
        // Squares are numbered from i=0 to n-1 where n=numSquares
        // Sqaures 0 to n-1 in first row, n to 2*n-1
        // in 2nd row, 2*n to 3*n-1 and so on
        // Starting row of each square=2*(i/n), and starting column=2*(i%n)
        //   where i is the square number
        int n = numSquares/2;   // per row/col
        /*
        for (int i=0; i<n*n;i++)
        {
            int row = calcRowCol(i, n, true);
            int col = calcRowCol(i, n, false);
            //Print.p(i,row, col,mat[row+1][col+1]);
        }
        */
        int startSquare = 0, endSquare = n*n-1;
        int midSquare;
        while (startSquare <= endSquare)
        {
            midSquare = (endSquare+startSquare)/2;
            int row = 2*(midSquare / n);
            int col = 2*(midSquare % n);
            if (p) Print.p(midSquare,row, col, mat[row+1][col+1]);
            if (x <= mat[row+1][col+1])// found the square that x might be in
            {
                if (p) Print.p(x, mat[row+1][col+1]);
                if (x == mat[row+1][col+1]) return true;
                if (x == mat[row][col+1]) return true;
                if (x == mat[row+1][col]) return true;
                if (x == mat[row][col]) return true;
                return false;
            }
            if (x > mat[row+1][col+1]) startSquare = midSquare + 1;
            else endSquare = midSquare - 1;
        }
        /*
        int row, col;
        Print.p(mat.length, numSquares, x);
        for (row=0; row<=numSquares; row+=2)
        {
            for (col=0; col<=numSquares; col+=2)
            {
                Print.p(row, col, x, mat[row+1][col+1]);
                if (x <= mat[row+1][col+1])// found the square that x might be in
                {
                    if (x == mat[row+1][col+1]) return true;
                    if (x == mat[row][col+1]) return true;
                    if (x == mat[row+1][col]) return true;
                    if (x == mat[row][col]) return true;
                    return false;
                }
            }
        }
        */
        return false;
    }
    /*
    private static int calcMid(int end, int start)
    {
        return (end - start + 1)/2 - 1;
    }
    private static int calcRowCol(int i, int n, boolean row)
    {
        return 2*(row? i/n : i%n);
    }
    */
    /**
     * findMinDifference - find the pair of members of an array of integers
     * whose absolute diference is MAXIMAL
     *
     * @param  int[] nums  the array with the integers
     * 
     * @return int[]   an array with 5 members (min difference, 1st number index,
     *                  1st number value, 2nd number index, 2nd number value
     * 
     * Time complexity = O(n)+O(n)
     */
    public static int[] findMaxDifference(int[] nums)
    {
        return findDifference(nums, true);
    }
    /**
     * findMinDifference - find the pair of members of an array of integers
     * whose absolute diference is MINIMAL
     *
     * @param  int[] nums  the array with the integers
     * 
     * @return int[]   an array with 5 members (min difference, 1st number index,
     *                  1st number value, 2nd number index, 2nd number value  
     * 
     * Time complexity = O(n)+O(n)+O(max-min+1)
     */
    public static int[] findMinDifference(int[] nums)
    {
        return findDifference(nums, false);
    }
    private static int[] findDifference(int[] nums, boolean findMax)
    {
        if (nums.length < 2) return new int[] {-1,-1,-1,-1,-1};
        // step 1: find min and max of array O(n)
        int min = 0;
        int max = 0;
        int i;
        for (i=0; i<nums.length;i++)
        {
            if (nums[min] > nums[i]) min = i;
            if (nums[max] < nums[i]) max = i;
        }
        if (p) Print.p(min, nums[min], max, nums[max]);
        // step 2: create an array of differnces with min, size of max-min+1
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
        if (findMax) // for finding max item 0 points to the maximum because we subtract from the max
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
    /**
     * Find the length of the longest continous sub-string of s2 which consists
     * of all the characters in s1
     * Example:  if s1="xyz" and s2=" abxyryxzycx" then longest is "yxzy" and
     * method returns 4
     */
    public static int maxSection(String s1, String s2)
    {
        //p = true;
        if (true) return maxSection(s1, s2, 0, 0, 0, false);
        int max = 0, len = 0;
        char c;
        int pos;
        int[] presence = new int[s2.length()];
        boolean prev = false;
        for (int i=0; i<s2.length(); i++)
        {
            c = s2.charAt(i);
            pos = s1.indexOf(c);
            if (pos > -1)   // found
            {
                //presence[i]++;
                if (len > 0)
                {
                    if (!prev)
                    //if (presence[i-1] == 0)
                        len = 0;
                }
                len++;
                prev = true;
            }
            else 
            {
                prev = false;
                if (max < len) max = len;
            }
            Print.p(i, len, max);
        }
        //Print.p(presence);
        return max;
    }
    private static int maxSection(String s1, String s2, int index, int len, int max, boolean prev)
    {
        //Print.p(1000, s1.length(), index1);
        if (index == s2.length()) return max;
        char c = s2.charAt(index);
        int pos = s1.indexOf(c);
        if (pos > -1) // found char of s2 in s1
        {
            if (p) Print.p(2000, pos, index);
            if (p) Print.p(""+c);
            if (len > 0)
                if (!prev) len = 0;
            len++;
            prev = true;
        }
        else
        {
            prev = false;
            if (max < len) max = len;
        }
        return maxSection(s1, s2, index+1, len, max, prev);
    }
}
