package test1;

import java.math.BigDecimal;
import java.util.Scanner;

import static java.math.BigDecimal.ROUND_HALF_UP;

public class Test1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //used for next try if you entered incorrect N
        while (true) {
            System.out.println("Enter N, it must be int type and  N>=0 ");
            if (scanner.hasNextInt()) {
                int n = scanner.nextInt();
                if (n >= 0) {
                    System.out.println("Count of correct pairs " + countCorrect(n));
                    break;
                } else {
                    System.out.println("N must be >= 0");
                    scanner.nextLine();
                }
            } else {
                System.out.println("N must be int type");
                scanner.nextLine();
            }
        }
    }

    private static BigDecimal countCorrect(long n) {
        //count of correct calculating by formula  : 1/(n+1) * (2n! / (n!*(2n-n)!))
        //                                                         a       b      c   d
        //I used BigDecimal to calculate large values
        BigDecimal a = BigDecimal.valueOf(1 / (double) (n + 1));
        BigDecimal b = factorial(2 * n);
        BigDecimal c = factorial(n);
        BigDecimal d = factorial((2 * n) - n);
        //this variant throws  Non-terminating decimal expansion; no exact representable decimal result.
        // BigDecimal result = a.multiply(b.divide(c));
        BigDecimal result = b.divide(c.multiply(d), 0, ROUND_HALF_UP).multiply(a);
        return result.setScale(0, ROUND_HALF_UP);
    }

    private static BigDecimal factorial(long n) {
        //I used BigDecimal to calculate large values
        BigDecimal result = BigDecimal.valueOf(1);
        for (long i = 1; i <= n; i++) {
            result = result.multiply(BigDecimal.valueOf(i));
        }
        return result;
    }

}

