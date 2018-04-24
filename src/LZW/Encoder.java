package LZW;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Encoder
{
    //Encodes using LZW
    public Encoder(int size)
    {
        // Create new dictionary
        Trie trie = new Trie();

        // Read in standard input
        InputStreamReader is = new InputStreamReader(System.in);
        // The index of the current character
        int current;


        try (BufferedReader br = new BufferedReader(is))
        {
            // While still data to be read in
            while ((current = br.read()) != -1)
            {
                //initial prefix
                String cp = "" + (char)current;

                // Populate the dictionary
                while(cp.length() > 0 && (int)cp.charAt(0) < 256)
                {

                    while(trie.encode(cp, size))
                    {
                        // Add current character to dictionary
                        cp += (char)br.read();

                        // Check that there is still space in the dictionary
                        if (trie.getDictionaryBitLength() > size)
                        {
                            System.out.println("Dictionary over capacity");
                            trie = new Trie();
                        }
                    }

                    // Shift to the next character
                    cp = "" + cp.charAt(cp.length()-1);
                }
            }
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
    }

    public static void main(String[] args)
    {
        // Check for valid input
        if(args.length != 1)
        {
            System.err.println("Usage: <int> - the maximum number of bits");
            // Return control to user
            return;
        }
        else
        {
            try
            {
                // Check that maximum bit size is valid
                if(Integer.parseInt(args[0]) > 8)
                {
                    // Create a new encoder of size n
                    Encoder encoder = new Encoder(Integer.parseInt(args[0]));
                }
                else
                {
                    System.err.println("Error: value must be greater than 8");
                }
            }
            catch(Exception e)
            {
                System.err.println(e);
            }
        }
    }
}