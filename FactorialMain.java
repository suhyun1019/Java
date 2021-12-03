import java.util.Scanner;

public class FactorialMain {
    public static void main (String[] args) {

        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        System.out.println("Enter a number: ");

        for(int i=1;i<=num;i++)
            System.out.println("Factorial of "+i+" = "+getFactorial(i));
    }

    private static long getFactorial(final int n) {
        int result = 1;
        for(int i=1;i<=n;i++)
            result = result*i;

        return result;
    }
}