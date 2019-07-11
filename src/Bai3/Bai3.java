package Bai3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import TrieLib.Trie;

public class Bai3 {

	public static void main(String[] args) throws IOException {
		Trie trie = new Trie();
		
		Scanner sc = null;
		FileOutputStream outputStream = null;
	    String s = "";
	    
	      try {
	         sc = new Scanner(new File("C:\\Users\\doanh\\eclipse-workspace\\DoAnGiaiThuatLapTrinh\\DoAn_Trie\\src\\Bai3\\data\\secret\\10.in"));
	         outputStream = new FileOutputStream("C:\\Users\\doanh\\eclipse-workspace\\DoAnGiaiThuatLapTrinh\\DoAn_Trie\\src\\Bai3\\data\\secret\\10.ans");	        
	         // Doc xau S can kiem tra
	         s = sc.nextLine();
	         //if(s.isEmpty()) System.out.println("TRONG!");
	         int n = sc.nextInt();
	         sc.nextLine();
	         // Doc tu dien
	         for(int i = 0; i < n; i++)
	         {
	        	 String s1 = sc.nextLine();
	        	 //System.out.println(s1);   
	        	 trie.insert(s1);
	         }
	         
	        long[] cnt = new long[s.length()];
	        for(int i = 0; i < s.length(); i++) cnt[i] = 0;
	        
	        // Xac dinh
	 		for(int i = 0; i < s.length(); i++)
	 		{
	 			String s2 = "";
	 			// Xau S[j]
	 			for(int j = 0; j <= i; j++)
	 			{
	 				s2 += s.charAt(j);
	 			}
	 			// Kiem tra hau to
	 			for(int j = i; j >= 0; j--)
	 			{
					String s3 = getLastnCharacters(s2, i - j + 1);
					if (trie.contains(s3)) // Neu s3 la hau to cua s2 va s3 la mot tu khoa
	 				{
	 					int index = i - s3.length();
	 					if(index < 0) cnt[i] = (cnt[i] + 1) % 1337377;
	 					else cnt[i] = (cnt[i] + cnt[index]) % 1337377;
	 				}
	 				//System.out.println(cnt[i]);
	 			}
	 		}
	 		long output = cnt[s.length() - 1] % 1337377; // So cach chia
			outputStream.write(String.valueOf(Long.toString(output)).getBytes());
 			System.out.println(output);
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
	public static String getLastnCharacters(String inputString, int subStringLength)
	{
		int length = inputString.length();
		if(length <= subStringLength)
		{
			return inputString;
		}
		int startIndex = length-subStringLength;
		return inputString.substring(startIndex);
	}
}
