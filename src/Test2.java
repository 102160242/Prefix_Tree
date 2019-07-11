import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

class TrieNode {
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
class Trie {
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
class Test2 {
	public static Trie trie = new Trie();
	public static void main(String[] args) throws IOException {
		Scanner sc = null;
		FileOutputStream outputStream = null;
	    int m, n;
	      try {
	         sc = new Scanner(new File("C:\\Users\\doanh\\eclipse-workspace\\DoAnGiaiThuatLapTrinh\\DoAn_Trie\\src\\Bai05\\input.txt"));
	         outputStream = new FileOutputStream("C:\\Users\\doanh\\eclipse-workspace\\DoAnGiaiThuatLapTrinh\\DoAn_Trie\\src\\Bai05\\output.txt");	        
	         // Doc n can kiem tra
	         m = sc.nextInt();	  
	         n = sc.nextInt();
	         sc.nextLine();
	         // Dua tin nhan vao Trie
	         for(int i = 0; i < m; i++)
	         {
	        	 String s = "";
	        	 int k = sc.nextInt();
	        	 for(int j = 0; j < k; j++)
	        	 {
	        		 s += sc.nextInt(); // Chuoi string cac bit tin nhan
	        	 }	        	 
	        	// System.out.println(s);   
	        	 trie.insert(s);
	         }
	         String ans = "";
	         sc.nextLine();
	         for(int i = 0; i < n; i++)
	         {
	        	 String s = "";
	        	 int k = sc.nextInt();
	        	 int a = 0;
	        	 TrieNode node = trie.getNode("");
	        	 for(int j = 0; j < k; j++)
	        	 {
	        		 s += Integer.toString(sc.nextInt()); // Chuoi string tu cac bit tu ma hoa
	        		 node = trie.getNode(s);	       
	        		 if(node == null)
	        		 {
	        			 break;
	        		 }
	        		 else if(node.isEndOfWord() && k != 1 && j != k - 1) a++; // Khong cong them neu day bit chi co 1 ky tu
	        	 }
	        	 if(node != null)
	        	 {	        	 
	        		 System.out.println(trie.countWords(node) + a); // Neu day tu ma hoa co do dai < nhanh lon nhat trong trie => In ra so la tu nhanh prefix + so la truoc no
	        		 ans += Long.toString(trie.countWords(node) + a);
	        		 ans += "\n";
	        	 }
	        	 else 
	        	 {
	        		 s = s.substring(0, s.length() - 1);
	        		 
	        		 if(s.isEmpty() || !trie.isPrefix(s)) 
	        	     {
	        			 System.out.println(0);
	        			 ans += "0\n";
	        		 }
	        		 else 
	        		 {
	        			 ans += "1\n";
	        			 System.out.println(1);
	        		 }
	        	 }
	        	 if(sc.hasNextLine()) sc.nextLine();
	         }
	         //System.out.println(a);
			outputStream.write(String.valueOf(ans).getBytes());
 			//System.out.println(ans);
	      } 
	      finally {
	         if (sc != null) {
	        	 sc.close();
	         }
	         if (outputStream != null) {
	            outputStream.close();
	         }
	      }	   
	}

}