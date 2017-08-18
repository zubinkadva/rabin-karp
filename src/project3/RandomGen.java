package project3;

import java.util.Random;
 
public class RandomGen 
{
 
    protected String randomgenr()
    {
        String alphabet= "abcdefghijklmnopqrstuvwxyz"; 
        int length = 100;
        Random rng=new Random();
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = alphabet.charAt(rng.nextInt(alphabet.length()));
        }
        return new String(text);

    }
}