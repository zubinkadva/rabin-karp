/* Author: Zubin Kadva
 * Class: Analysis of Algorithms, Spring 2017
 * Project: Rabin Karp
 */

class RabinKarp {

    private static final int prime = 101;

    // Returns first index position or -1 if not found
    public static int search(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        int pHash = hash(pattern, m - 1);
        int tHash = hash(text, m - 1);

        for (int i = 1; i <= n - m + 1; i++) {
            if (pHash == tHash) {
                if (pattern.equals(text.substring(i - 1, i + m - 1))) {
                    return i - 1;
                }
            }
            //if (i < n - m + 1) {
            tHash = reHash(text, m, i - 1, i + m - 1, tHash);
            //}
        }
        return -1;
    }

    private static int hash(String text, int length) {
        int hash = 0;
        for (int i = 0; i <= length; i++) {
            // sum (ASCII * prime^index)
            hash += ((int) text.charAt(i)) * Math.pow(prime, i);
        }
        return hash;
    }

    private static int reHash(String text, int length,
            int oldIndex, int newIndex, int hash) {
        // x = oldHash - ASCII
        int newHash = hash - ((int) text.charAt(oldIndex));
        // x = x / prime
        newHash = newHash / prime;
        // x + prime^m-1 * ASCII
        newHash += ((int) text.charAt(newIndex))
                * Math.pow(prime, (length - 1));
        return newHash;
    }

}
