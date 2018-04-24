package LZW;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Decoder
{

    private int dictionarySize;
    private Map<Integer, String> dictionary = new HashMap<Integer, String>();
    private Map<String, Integer> dictionaryValues = new HashMap<String, Integer>();

    public Decoder()
    {
        //handles input
        InputStreamReader isReader = new InputStreamReader(System.in);
        int phraseNumber;
        String input;
        String cp = "";
        String next = "";
        String output ="";
        //fill the array initially
        try (BufferedReader br = new BufferedReader(isReader))
        {
            //populates the dictionary
            initialiseDictionary();

            //get first line
            input = br.readLine();
            input = input.trim();
            phraseNumber = Integer.parseInt(input);

            //gets dictionary Entry
            cp = dictionary.get(phraseNumber);
            System.out.printf(cp);


            start:
            while ((input) != null && !input.isEmpty())
            {
                //checks dictionary for key
                while(dictionaryValues.containsKey(cp))
                {
                    //get next line
                    input = br.readLine();

                    //breaks out if input is null
                    if(input == null || input.trim().isEmpty())
                        break start;

                    //gets next phrase number
                    input = input.trim();

                    phraseNumber = Integer.parseInt(input);

                    if(phraseNumber == 256)
                    {
                        //initialises the dictionary
                        initialiseDictionary();
                        cp ="";
                        input = br.readLine();

                        //breaks out if input is null
                        if(input == null || input.trim().isEmpty())
                            break start;

                        //gets next phrase number
                        input = input.trim();
                        phraseNumber = Integer.parseInt(input);
                    }

                    //gets next phrase
                    next = dictionary.get(phraseNumber);

                    //builds strings of 3 of the same char
                    if(next == null)
                    {
                        next = cp+cp.charAt(0);
                    }

                    //prints to output
                    //next = next.replaceAll("%", "%%");
                    System.out.printf(next.replaceAll("(?:[^%]|\\A)%(?:[^%]|\\z)", "%%"));

                    //gets previous char
                    cp += next.charAt(0);

                }

                //adds key to dictionary
                dictionary.put(dictionarySize++, cp);
                dictionaryValues.put(cp, dictionarySize);
                //sets the current phrase to be the next phrase
                cp = next;
            }

        }
        catch(Exception e)
        {
            //print error
            e.printStackTrace();
        }
    }

    //initialises the Dictionary
    //fills the dictionary for the first time
    private void initialiseDictionary()
    {
        dictionarySize = 258;
        dictionary = new HashMap<Integer, String>();
        dictionaryValues = new HashMap<String, Integer>();

        for (int i = 0; i < 256; i++)
        {
            dictionary.put(i, "" + (char) i);
            dictionaryValues.put( "" + (char) i,i);
        }
    }

    public static void main(String[] args)
    {
        try
        {
            Decoder decoder = new Decoder();
        }
        catch(Exception e)
        {
            System.err.println("Error: unexpected exception");
        }
    }


}