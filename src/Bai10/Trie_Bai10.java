package Bai10;

import java.util.Map;

import TrieLib.Trie;
import TrieLib.TrieNode;

public class Trie_Bai10 extends Trie {
	public int count(TrieNode c, int cnt, int ans)
	{
		if(c.isEndOfWord())
		{
			ans = ans > ++cnt ? ans : cnt;			
		}
    	if(c.getChildren().size() != 0)
    	{
            for(Map.Entry<Character, TrieNode> entry : c.getChildren().entrySet())
            {
            	ans = this.count(entry.getValue(), cnt, ans);
            }
    	}
		return ans;
	}
}
