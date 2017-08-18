package project3;

import java.util.*;
import java.math.BigInteger;
import java.util.Random;

public class RabinKarp {

    private String pat;
    private long patHash;
    private int m;
    private long q;
    private int R;
    private long RM;

    public RabinKarp(char[] pattern, int R) {
        this.pat = String.valueOf(pattern);
        this.R = R;
        throw new UnsupportedOperationException("Operation not supported yet");
    }

    public RabinKarp(String pat) {
        this.pat = pat;      // save pattern (needed only for Las Vegas)
        R = 256;
        m = pat.length();
        q = longRandomPrime();

        // precompute R^(m-1) % q for use in removing leading digit
        RM = 1;
        for (int i = 1; i <= m - 1; i++) {
            RM = (R * RM) % q;
        }
        patHash = hash(pat, m);
    }

    // Compute hash for key[0..m-1]. 
    private long hash(String key, int m) {
        long h = 0;
        for (int j = 0; j < m; j++) {
            h = (R * h + key.charAt(j)) % q;
        }
        return h;
    }

    // Las Vegas version: does pat[] match txt[i..i-m+1] ?
    private boolean check(String txt, int i) {
        for (int j = 0; j < m; j++) {
            if (pat.charAt(j) != txt.charAt(i + j)) {
                return false;
            }
        }
        return true;
    }

    // Monte Carlo version: always return true
    // private boolean check(int i) {
    //    return true;
    //}
    /**
     * Returns the index of the first occurrence of the pattern string in the
     * text string.
     *
     * @param txt the text string
     * @return the index of the first occurrence of the pattern string in the
     * text string; n if no such match
     */
    public int search(String txt) {
        int n = txt.length();
        if (n < m) {
            System.out.println(m + " " + n + " " + patHash + " " + R + " " + RM);
            return n;
        }
        long txtHash = hash(txt, m);
        System.out.println(m + " " + n + " " + patHash + " " + txtHash + " " + R + " " + RM);

        // check for match at offset 0
        if ((patHash == txtHash) && check(txt, 0)) {
            return 0;
        }

        // check for hash match; if hash match, check for exact match
        for (int i = m; i < n; i++) {
            // Remove leading digit, add trailing digit, check for match. 
            txtHash = (txtHash + q - RM * txt.charAt(i - m) % q) % q;
            txtHash = (txtHash * R + txt.charAt(i)) % q;

            // match
            int offset = i - m + 1;
            if ((patHash == txtHash) && check(txt, offset)) {
                return offset;
            }
        }

        // no match
        return n;
    }

    // a random 31-bit prime
    private static long longRandomPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
        //return 101;
    }

    /**
     * Takes a pattern string and an input string as command-line arguments;
     * searches for the pattern string in the text string; and prints the first
     * occurrence of the pattern string in the text string.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        //10 20 50 49 256 89
        RabinKarp searcher = new RabinKarp(generatePattern(10));
        //int offset = searcher.search(generatePattern(10));
        searcher.search(generateText(20));

        //System.out.println(offset);
    }

    private static String generateText(int length) {
        String text = "";
        for (int i = 1; i <= length - 1; i++) {
            text += "a";
            if (i == length - 1) {
                text += "b";
            }
        }
        return text;
    }

    private static String generatePattern(int length) {
        String pattern = "";
        for (int i = 1; i <= length - 1; i++) {
            pattern += "a";
            if (i == length - 1) {
                pattern += "b";
            }
        }
        return pattern;
    }
}
