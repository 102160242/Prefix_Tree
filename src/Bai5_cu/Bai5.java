package Bai5_cu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import TrieLib.Trie;

public class Bai5 {

	public static void main(String[] args) throws IOException {
		Trie trie = new Trie();
		
		Scanner sc = null;
		FileOutputStream outputStream = null;
	    String s = "";
	    int n;
	      try {
	         sc = new Scanner(new File("C:\\Users\\doanh\\eclipse-workspace\\DoAnGiaiThuatLapTrinh\\DoAn_Trie\\src\\Bai5\\input.txt"));
	         outputStream = new FileOutputStream("C:\\Users\\doanh\\eclipse-workspace\\DoAnGiaiThuatLapTrinh\\DoAn_Trie\\src\\Bai5\\output.txt");	        
	         // Doc m, n, xau S can kiem tra
	         s = sc.nextLine();
	         n = sc.nextInt();	  
	         sc.nextLine();
	         // Doc tu dien
	         for(int i = 0; i < n; i++)
	         {
	        	 String s1 = sc.nextLine();
	        	 //System.out.println(s1);   
	        	 trie.insert(s1);
	         }
	         int status = 0;
	         for(int i = 0; i < s.length(); i++)
	         {
	        	 String s1 = "";
	        	 for(int j = i; j < s.length(); j++)
	        	 {
	        		s1 += s.charAt(j);
	        		if(!trie.isPrefix(s1))
	        		{
	        			if(j - i > 1) 
	        			{
	        				status = i;
	        				i = j - 1;
	        			}
	        			//if(j + 1 < s.length() && trie.isPrefix(Character.toString(s.charAt(j+1))))
	        			//{
	        			//	status = i;
	        			//}
	        			break;
	        		}
	        		if(trie.contains(s1))
	        		{
	         			//System.out.println(s1);
	        			s = s.substring(0, i) + s.substring(j + 1, s.length());
	        			//System.out.println(s);
	        			i = status - 1;
	        			break;
	        		}
	        	 }
	         }
			outputStream.write(String.valueOf(s).getBytes());
 			System.out.println(s);
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
