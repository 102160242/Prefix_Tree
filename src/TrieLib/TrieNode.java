package TrieLib;
import java.util.HashMap;

public class TrieNode {
    private HashMap<Character, TrieNode> children;
    private long leaf;
    private boolean isWord;
    public TrieNode()
    {
    	children = new HashMap<Character, TrieNode>();
    	leaf = 0;
    }
	public HashMap<Character, TrieNode> getChildren() {
		return this.children;
	}
	public void setEndOfWord(boolean b) {
		this.isWord = true;
	}
	public boolean isEndOfWord() {
		return this.isWord;
	}
	public void increaseCountLeaf()
	{
		leaf++;
	}
	public long cleaf()
	{
		return leaf;
	}
}