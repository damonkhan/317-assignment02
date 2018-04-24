package LZW;

public class TrieNode
{
    //member variables
    private static int nextIndex;
    public TrieNode[] children;
    private TrieNode parent;
    private char character;
    private int index;
    private double dictionaryBitLength;
    private static TrieNode leaf;

    //constructor for the first trienode
    public TrieNode()
    {
        children = new TrieNode[256];
        nextIndex = 1;
    }

    //finds the current max bit size of the dictionary
    public int getDictionaryBitLength()
    {
        return (int)(Math.ceil(Math.log(nextIndex-1) / Math.log(2)));
    }

    //constructor for any children trienode
    public TrieNode(char _character, int _index)
    {
        children = new TrieNode[256];
        index = _index;
        character = _character;
        nextIndex++;

        if(index == 257)
        {
            index = 258;
            nextIndex = 259;
        }

    }

    //checks that if the specified string is in our dictionary
    public boolean encode(String str, int maxSize)
    {


        //fixes output for last char
        if(str.charAt(0) > 256)
        {
            System.out.println(this.index);
            return false;
        }

        //char does not exist
        if(this.children[str.charAt(0)] == null)
        {
            //creates a trienode for the specified char
            this.children[str.charAt(0)] = new TrieNode(str.charAt(0), nextIndex);
            this.children[str.charAt(0)].parent = this;
            //outputs the current index
            System.out.println(this.index);

            return false;
        }
        else
        {
            //exit condition for recursive check
            if (str.length() > 1)
            {
                //recursively checks for each char in the string
                return this.children[str.charAt(0)].encode(str.substring(1), maxSize);
            }
            else
            {
                return true;
            }
        }
    }
}