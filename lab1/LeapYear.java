/** Class that determines whether or not a year is a leap year.
 *  @author Piao Hu
 */
public class LeapYear {

    /** return True if the given year is a leapyear, if not return False */
    public static boolean isLeapYear(int year) {
        if year % 400 == 0 or (year % 4 == 0 and year % 100 != 0) {
            return True;
        }
        else {
            return False;
        }
    }

    /** Calls isLeapYear to print correct statement.
     *  @param  year to be analyzed
     */
    private static void checkLeapYear(int year) {
        if (isLeapYear(year)) {
            System.out.printf("%d is a leap year.\n", year);
        } else {
            System.out.printf("%d is not a leap year.\n", year);
        }
    }

    public static int fib(int n) {
        if n == 0 or n == 1{
            return n;
        }else {
            return fib(int n - 1) + fib(int n - 2);
        }
    }

    public static int fib2(int n, int k, int f0, int f1) {
        if (n == k) { 
            return f0;
        } else {
            return fib2(n, k + 1, f1, f0 + f1);
        }
    }

    /** Must be provided an integer as a command line argument ARGS. */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter command line arguments.");
            System.out.println("e.g. java Year 2000");
        }
        for (int i = 0; i < args.length; i++) {
            try {
                int year = Integer.parseInt(args[i]);
                checkLeapYear(year);
            } catch (NumberFormatException e) {
                System.out.printf("%s is not a valid number.\n", args[i]);
            }
        }
    }
}
