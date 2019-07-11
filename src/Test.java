import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import TrieLib.Trie;
class TrieNode_ {
    private HashMap<Character, TrieNode_> children;
    private long leaf;
    private boolean isWord;
	private int maxLength;
    public TrieNode_()
    {
    	children = new HashMap<Character, TrieNode_>();
    	leaf = 0;
    }
	public HashMap<Character, TrieNode_> getChildren() {
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
	public int maxLength()
	{
		return maxLength;
	}
	public void increaseLength()
	{
		maxLength++;
	}
}
class Trie_ extends Trie {
    private TrieNode_ root;
    public Trie_()
    {
    	root = new TrieNode_();
    }
    public void insert(String word) {
        TrieNode_ current = root;
        //current.increaseLength();
        for (int i = 0; i < word.length(); i++) {
        	current.increaseLength();
            current = current.getChildren()
              .computeIfAbsent(word.charAt(i), c -> new TrieNode_());   
            current.increaseCountLeaf();
        }
        current.setEndOfWord(true);
    }
    public TrieNode_ getNode_(String s)
    {
        TrieNode_ current = root;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            TrieNode_ node = current.getChildren().get(ch);
            if (node == null) {
                return null;
            }
            current = node;
        }
    	return current;
    }
    public long countWords(TrieNode_ trieNode)
    {
    	return trieNode == null ? 0 : trieNode.cleaf();
    }
    public int maxLength(TrieNode_ trieNode)
    {
    	return trieNode.maxLength();
    }
}
class Tests {
	public static Trie_ trie = new Trie_();
	public static void s(String[] args) throws IOException {
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
	        	 //System.out.println(s);   
	        	 trie.insert(s);
	         }
	         String ans = "";
	         sc.nextLine();
	         for(int i = 0; i < n; i++)
	         {
	        	 String s = "";
	        	 int k = sc.nextInt();
	        	 int a = 0;
	        	 TrieNode_ node = trie.getNode_("");
	        	 int maxLength = trie.maxLength(node);
	        	 for(int j = 0; j < k; j++)
	        	 {
	        		 s += Integer.toString(sc.nextInt()); // Chuoi string tu cac bit tu ma hoa
	        	 }
	        	 if(k > maxLength)
	        	 {
		        	 if(trie.isPrefix(s.substring(0, maxLength - 1)))
	        	     {
	        			 System.out.println(1);
	        			 ans += "1\n";
	        		 }
		        	 else	        		 
	        	     {
	        			 System.out.println(0);
	        			 ans += "0\n";
	        		 }
	        	 }
	        	 else
	        	 {
	        		 String s1 = "";
		        	 for(int j = 0; j < k; j++)
		        	 {
		        		 s += Integer.toString(sc.nextInt()); // Chuoi string tu cac bit tu ma hoa
		        		 node = trie.getNode_(s);	       
		        		 if(node.isEndOfWord() && k != 1 && j != k - 1) a++; // Khong cong them neu day bit chi co 1 ky tu
		        		 if(trie.getNode_(s1 + s.charAt(j+1)) == null) break;
		        	 }
        	 
		        		 System.out.println(trie.countWords(node) + a); // Neu day tu ma hoa co do dai < nhanh lon nhat trong trie => In ra so la tu nhanh prefix + so la truoc no
		        		 ans += Long.toString(trie.countWords(node) + a);
		        		 ans += "\n";

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

class Test {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		FileOutputStream outputStream = null;
	    int m = 50000, n = 50000, min = 1, max = 10000;
	    String out = "";
	    out = Integer.toString(m) + " " + Integer.toString(n) + "\n";
    	Random r = new Random();

	      try {
		      outputStream = new FileOutputStream("C:\\Users\\doanh\\eclipse-workspace\\DoAnGiaiThuatLapTrinh\\DoAn_Trie\\src\\Bai5\\data\\secret\\11.in", true);	        
			    for(int i = 0; i < m + n; i++)
			    {
			    	int k = r.nextInt(max-min) + min;
			    	out = out + Integer.toString(k) + " ";
			    	for(int j = 0; j < k; j++)
			    	{
			    		double a = Math.random();
			    		int bit;
			    		if(a > 0.5) bit = 1;
			    		else bit = 0;
			    		out = out + Integer.toString(bit) + " ";
			    	}
			    	out += "\n";
			    	outputStream.write(String.valueOf(out).getBytes());
			    	out = "";
			    }
			    
		    	//System.out.println(out);
	      } 
	      finally {
	         if (outputStream != null) {
	        	 outputStream.close();
	         }
	      }	   
	}

}