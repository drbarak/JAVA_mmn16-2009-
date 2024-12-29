
/**
 * Write a description of class TesterEX16 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class TesterEX16
{
    public static void main()
    {
        boolean p = false;
        int[] run = {1,1};

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
