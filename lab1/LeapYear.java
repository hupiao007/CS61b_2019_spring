/** Class that determines whether or not a year is a leap year.
 *  @author Piao Hu
 */
public class LeapYear {
    /** return True if the given year is a leapyear, if not return False */
    
    public static boolean isLeapYear(int year) {
        
        if(year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
            return true;
        } else {
            return false;
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
        
        if (n == 0 || n == 1) {
            return n;
        }
        else {
            return fib(n - 1) + fib(n - 2);
        }
        
    }
    
    public static int fib2(int n, int k, int f0, int f1) {
        if (n == k) { 
            return f0;
        } else {
            return fib2(n, k + 1, f1, f0 + f1);
        }
    }

    /** HW0_Exercise_1a */
    public static void HW0_Exercise_1a() {
        int times = 1;
        while (times <= 5) {
            int i = 1;
            while (i <= times) {
                System.out.print("*");
                i += 1;
            }
            System.out.println("");
            times += 1;
        }
    }

    /** HW0_Excercise_1b */
    public static void drawTriangle(int n) {
        int lines = 1;
        while (lines <= n) {
            int times = 1;
            while (times <= lines) {
                System.out.print("*");
                times += 1;
            }
            System.out.println("");
            lines += 1;
        }
    }

    /** Returns the maximum value from m.
    * HW0_Exercise 2 
    */
    public static int max(int[] m) {
        int max_value = 0;
        int count = 0;
        while (count < m.length) {
            if (m[count] > max_value) {
                max_value = m[count];
            }
            count += 1;
        }
        return max_value;
    }

    /** Returns the maximum value from m using a for loop. 
    HW0_Exercise 3 */
    public static int forMax(int[] m) {
        int max_value = 0;
        for (int i = 0; i < m.length; i += 1) {
            if (m[i] > max_value) {
                max_value = m[i];
            }
        }
        return max_value;
    }

    /** Write a function windowPosSum(int[] a, int n) that replaces each 
    element a[i] with the sum of a[i] through a[i + n], but only if a[i] 
    is positive valued. If there are not enough values because we reach 
    the end of the array, we sum only as many values as we have.
    HW0_Exercise 4 */
    public static void windowPosSum(int[] a, int n) {
        for (int i = 0; i < a.length; i += 1) {
            if (a[i] > 0) {
                for (int j = 1; (j <= n) && (i + j < a.length); j += 1) {
                    a[i] += a[i + j];
                }
            }
        }
    }

    /** Must be provided an integer as a command line argument ARGS. */
    public static void main(String[] args) {
        /* for lab
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
        */
        // HW0_Exercise_1a();
        // drawTriangle(10);
        // int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
        // System.out.println(max(numbers));
        // System.out.println(forMax(numbers));

        int[] a = {1, 2, -3, 4, 5, 4};
        int n = 3;
        windowPosSum(a, n);

        // Should print 4, 8, -3, 13, 9, 4
        System.out.println(java.util.Arrays.toString(a));
    }
}
