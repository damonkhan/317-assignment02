package LZW;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Encoder
{
    public static void main(String[] args)
    {
        //ensures 1 input
        if(args.length == 1)
        {
            try
            {
                //ensures maxDictionarySize is greater than 8
                if(Integer.parseInt(args[0]) > 8)
                {
                    Encoder e = new Encoder(Integer.parseInt(args[0]));
                }
                else
                {
                    System.err.println("Please enter an integer greater than 8.");
                }
            }
            catch(Exception e)
            {
                System.err.println("Please enter a valid integer for the maximum dictionary size (bits).");
            }
        }
        else
        {
            System.err.println("Only a single integer is expected.");
        }
    }

    //Encodes using LZW
    public Encoder(int maxSize)
    {
        //creates a new trie to be used as our dictionary
        Trie t = new Trie();

        //handles input
        InputStreamReader isReader = new InputStreamReader(System.in);
        int currChar;

        //fill the array initially
        try (BufferedReader br = new BufferedReader(isReader))
        {
            //reads while the
            while ((currChar = br.read()) != -1)
            {
                //initial prefix
                String cp = "" + (char)currChar;

                while(cp.length() > 0 && (int)cp.charAt(0) < 256)
                {

                    //checks dictionary for the largest prefix
                    while(t.encode(cp, maxSize))
                    {
                        //adds a new char to prefix
                        cp += (char)br.read();

                        //checks if the dictionary is full
                        if (t.getDictionaryBitLength() > maxSize)
                        {
                            System.out.println("256");
                            //empties the dictionary
                            t = new Trie();
                        }
                    }

                    //makes the previous character the new prefix
                    cp = "" + cp.charAt(cp.length()-1);
                }
            }
        }
        catch(Exception e)
        {
            //print error
            e.printStackTrace();
        }
    }
}