package TrieLib;

import java.util.Map;

public class Trie {
    private TrieNode root;
    public Trie()
    {
    	root = new TrieNode();
    }
    public void insert(String word) {
        TrieNode current = root;
     
        for (int i = 0; i < word.length(); i++) {
        	//if(current.getChildren() != null) current.increaseCountLeaf();
            current = current.getChildren()
              .computeIfAbsent(word.charAt(i), c -> new TrieNode());   
            current.increaseCountLeaf();
        }
        current.setEndOfWord(true);
    }
    public boolean contains(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.getChildren().get(ch);
            if (node == null) {
                return false;
            }
            current = node;
        }
        return current.isEndOfWord();
    }
    public boolean isPrefix(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.getChildren().get(ch);
            if (node == null) {
                return false;
            }
            current = node;
        }
        return true;
    }
    public void delete(String word) {
        delete(root, word, 0);
    }
     
    private boolean delete(TrieNode current, String word, int index) {
        if (index == word.length()) {
            if (!current.isEndOfWord()) {
                return false;
            }
            current.setEndOfWord(false);
            return current.getChildren().isEmpty();
        }
        char ch = word.charAt(index);
        TrieNode node = current.getChildren().get(ch);
        if (node == null) {
            return false;
        }
        boolean shouldDeleteCurrentNode = delete(node, word, index + 1) && !node.isEndOfWord();
     
        if (shouldDeleteCurrentNode) {
            current.getChildren().remove(ch);
            return current.getChildren().isEmpty();
        }
        return false;
    }
    public TrieNode getNode(String s)
    {
        TrieNode current = root;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            TrieNode node = current.getChildren().get(ch);
            if (node == null) {
                return null;
            }
            current = node;
        }
    	return current;
    }
    public long countWords(TrieNode trieNode)
    {
    	/*int count = 0;
    	TrieNode current = trieNode;
    	if(current == null) return count;
    	if(current.isEndOfWord()) count += 1;
    	if(current.getChildren().size() != 0)
    	{
            for(Map.Entry<Character, TrieNode> entry : current.getChildren().entrySet())
            {
            	count += countWords(entry.getValue());
            }
    	}
		return count;*/
    	return trieNode == null ? 0 : trieNode.cleaf();
    }
}