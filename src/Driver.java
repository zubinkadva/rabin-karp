
import java.util.Scanner;

/* Author: Zubin Kadva
 * Class: Analysis of Algorithms, Spring 2017
 * Project: Driver program for naive and Rabin Karp
 */
class Driver {

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

    public static void main(String a[]) {

        String text = Driver.generateText(1000);
        String pattern = Driver.generatePattern(100);

        // Case 1: Fixed pattern, variable text       
        // Case 2: Fixed text, variable pattern
        
        //System.out.println(NaiveSearch.search(text, pattern));
        
        //System.out.println(RabinKarp.search(text, pattern));
        
        System.out.println(RabinKarpMatcher.search(text, pattern));
        
        //System.out.println(new project3.RabinKarp(pattern).search(text));
        
        //RabinKarpSearch rks = new RabinKarpSearch();
        //System.out.println(rks.patternSearch(text.toCharArray(), pattern.toCharArray()));  
        
        //new Scanner(System.in).next();
    }
}
