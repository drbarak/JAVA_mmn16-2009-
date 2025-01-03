public class TesterEX16
{
    public static void main()
    {
        boolean p = false;
        int[] run = {0,0,0,0,1};
        if (run[4] == 1)
        {
            Print.p("------ sort ------");
            int[] nums = {3, 8, 15, 1, 7};
            //nums = new int[]{-10,-3,-7,-1,9,8};
            //nums = new int[]{-10,-3,-1,-1,9,8};
            Print.p("Original array:", nums);
            Print.p("Sorted array: ", Ex16.sort(nums, true));
        }
        if (run[3] == 1)
        {
            Print.p("------ maxSection ------");
            String s1 = "xyz";
            String s2 = " abxyryxzycx";
            //s2 = " abxyryxdzzcx ";
            //s2 = " abcd ";
            Print.p("Longest substring with characters of ["+s1+"] in ["+s2+"] is ", Ex16.maxSection(s1, s2));
        }
        if (run[2] == 1)
        {
            Print.p("------ findXinMatrix ------");
            int[][] mat = {{-4,-2,5,9},{2,5,12,13},{13,20,25,25},{22,24,49,57}};
            mat = new int[][]{{1,2},{3,4}};
            int x = 2;
            Print.p(mat);
            Print.p("findXinMatrix for x="+x+", "+Ex16.find(mat, x));
        }
        if (run[1] == 1)
        {
            Print.p("------ findMaxDifference ------");
            int[] nums = {3, 8, 15, 1, 7};
            nums = new int[]{-10,-3,-7,-1,9,8};
            //nums = new int[]{-10,-3,-7,-1,3,8,-3};
            Print.p(nums);
            Print.p("Maximum Difference: ",Ex16.findMaxDifference(nums));
        }
        if (run[0] == 1)
        {
            Print.p("------ findMinDifference ------");
            int[] nums = {3, 8, 15, 1, 7};
            nums = new int[]{-10,-3,-7,-1,3,8};
            //nums = new int[]{-10,-3,-7,-1,3,8,-3};
            Print.p(nums);
            Print.p("Minimum Difference: ",Ex16.findMinDifference(nums));
        }
    }
}
