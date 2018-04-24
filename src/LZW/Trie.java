package LZW;

public class Trie
{
    //member variables
    public TrieNode root;
    private String lastChar;

    //constructor
    public Trie()
    {
        //creates the root node
        root = new TrieNode();

        // Builds initial dictionary
        for (int i = 0; i < 256; i++) {
            root.children[i] =  new TrieNode((char)(i), i);
        }
    }

    //begins recursive string check
    public boolean encode(String str, int maxSize)
    {
        return root.encode(str, maxSize);
    }

    //finds the max dictionary size in bits
    public int getDictionaryBitLength()
    {
        return root.getDictionaryBitLength();
    }
}