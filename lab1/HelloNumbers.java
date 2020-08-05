public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0, sum = 0;
        while (x < 10) {
        	sum = sum + x;
            System.out.println(sum);
            /*
            System.out.println(5 + "10");
            System.out.println(5 + 10);
            */
            x = x + 1;
        }
    }
}