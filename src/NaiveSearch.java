/* Author: Zubin Kadva
 * Class: Analysis of Algorithms, Spring 2017
 * Project: Naive Search
 */

class NaiveSearch {

    // Returns first index position or -1 if not found
    public static int search(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        for (int i = 0; i < n - m + 1; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (text.toLowerCase().charAt(i + j) != 
                        pattern.toLowerCase().charAt(j)) {
                    break;
                }
            }
            if (j == m) {
                return i;
            }
        }
        return -1;
    }

}
