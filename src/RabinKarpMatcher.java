/* Author: Zubin Kadva, Siddhesh Jethe
 * Class: Analysis of Algorithms, Spring 2017
 * Project: Rabin Karp modular hashing
 * Reference: http://algs4.cs.princeton.edu/53substring/RabinKarp.java.html
 */

import java.math.BigInteger;
import java.security.SecureRandom;

public class RabinKarpMatcher {

    static long q = BigInteger.probablePrime(31, new SecureRandom()).longValue();
    static int d = 256;

    private static long hash(String key, int m) {
        long h = 0;
        for (int i = 0; i < m; i++) {
            h = (d * h + key.charAt(i)) % q;
        }
        return h;
    }

    // Las Vegas version: does pat[] match txt[i..i-m+1] ?
    private static boolean check(String txt, int i, int m, String pat) {
        for (int j = 0; j < m; j++) {
            if (pat.charAt(j) != txt.charAt(i + j)) {
                return false;
            }
        }
        return true;
    }

    // Monte Carlo version: always return true
    private static boolean check(int i) {
        return true;
    }

    // Returns first index position or -1 if not found    
    public static int search(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        // h = d ^ (m-1) mod q
        long h = BigInteger.valueOf(d).modPow(BigInteger.valueOf(m - 1),
                BigInteger.valueOf(q)).longValue();

        for (int i = 1; i <= m - 1; i++) {
            h = (d * h) % q;
        }
        long p = hash(pattern, m);
        long t = hash(text, m);

        // check for match at offset 0
        if ((p == t) && check(text, 0, m, pattern)) {
            return 0;
        }

        for (int i = m; i < n; i++) {
            // Remove leading digit, add trailing digit, check for match. 
            t = (t + q - h * text.charAt(i - m) % q) % q;
            t = (t * d + text.charAt(i)) % q;

            // match
            int offset = i - m + 1;
            if ((p == t) && check(text, offset, m, pattern)) {
                return offset;
            }
        }
        return -1;
    }

}
