/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3;

import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author zubin
 */
public class Test {

    public static void main(String a[]) {
        int R = 256;
        int m = 2;
        long q = longRandomPrime();

        // precompute R^(m-1) % q for use in removing leading digit
        long RM = 1;
        for (int i = 1; i <= m - 1; i++) {
            RM = (R * RM) % q;
        }

        System.out.println(RM);

        long RM2 = (long) Math.pow(R, (m - 1)) % q;

        System.out.println(RM2);
    }

    private static long longRandomPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }
}
