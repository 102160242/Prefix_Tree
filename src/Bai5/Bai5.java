package Bai5;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import TrieLib.Trie;
import TrieLib.TrieNode;

public class Bai5 {
	static Trie trie = new Trie();

	public static void main(String[] args) throws IOException {
		Scanner sc = null;
		FileOutputStream outputStream = null;
		try {
			sc = new Scanner(new File(
					"C:\\Users\\doanh\\eclipse-workspace\\DoAnGiaiThuatLapTrinh\\DoAn_Trie\\src\\Bai5\\data\\secret\\6.in"));
			outputStream = new FileOutputStream(
					"C:\\Users\\doanh\\eclipse-workspace\\DoAnGiaiThuatLapTrinh\\DoAn_Trie\\src\\Bai5\\data\\secret\\6.ans");

			int m = sc.nextInt();
			int n = sc.nextInt();
			sc.nextLine();
			for (int i = 0; i < m; i++) {
				String s = "";
				int k = sc.nextInt();
				for (int j = 1; j <= k; j++) {
					s += sc.nextInt();
				}
				trie.insert(s);
				sc.nextLine();
			}
			String out = "";
			for (int i = 0; i < n; i++) {
				String s = "";
				int k = sc.nextInt();
				for (int j = 1; j <= k; j++) {
					s += sc.nextInt();
				}
				long ans = dowork(s, k);
				out += ans + "\n";
				System.out.println(ans);
				if (sc.hasNextLine())
					sc.nextLine();
			}
			outputStream.write(String.valueOf(out).getBytes());
		} finally {
			if (sc != null) {
				sc.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}

	public static long dowork(String s, int k) {
		long ans = 0;
		int dem = 0;
		TrieNode node;
		for (int i = 1; i <= k; i++) {
			node = trie.getNode(s.substring(0, i));// lay node cua xau s tu dau cho toi vi tri thu i
			if (node == null) {
				ans = 0; // neu node k ton tai thi break vong lap
				break;
			}
			if (node.isEndOfWord() == true) // neu xau s la 1 la' trong trie
			{
				dem++; // bien dem tu tang len 1
				ans = trie.countWords(trie.getNode(s.substring(0, i))) - 1; // dem trong cay trie cac node chua xau s
			} else /* if (trie.isPrefix(s.substring(0, i))) */ // neu xau s ton tai trong trie
			{
				ans = trie.countWords(trie.getNode(s.substring(0, i))); // dem trong cay trie cac node chua xau s
			}
		}
		return ans + dem;
	}
}
