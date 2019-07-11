package Bai8;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import TrieLib.Trie;

public class Bai8 {
	static Trie trie = new Trie();

	public static void main(String[] args) throws IOException {
		Scanner sc = null;
		FileOutputStream outputStream = null;
		int n;
		long k;
		try {
			sc = new Scanner(new File(
					"C:\\Users\\doanh\\eclipse-workspace\\DoAnGiaiThuatLapTrinh\\DoAn_Trie\\src\\Bai8\\data\\secret\\2.in"));
			outputStream = new FileOutputStream(
					"C:\\Users\\doanh\\eclipse-workspace\\DoAnGiaiThuatLapTrinh\\DoAn_Trie\\src\\Bai8\\data\\secret\\2.ans");
			// Doc n can kiem tra
			n = sc.nextInt();
			k = sc.nextLong();
			sc.nextLine();
			long ans = 0;
			long pre = 0;
			addBit(0);
			// trie.
			// Doc tu dien
			for (int i = 0; i < n; i++) {
				int a = sc.nextInt();
				pre ^= a;
				ans += get(pre, k);
				addBit(pre);
				// System.out.println(max);
				// trie.insert(s1);
			}
			// System.out.println(a);
			outputStream.write(String.valueOf(Long.toString(ans)).getBytes());
			System.out.println(ans);
		} finally {
			if (sc != null) {
				sc.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}

	public static void addBit(long a) {
		String s = "";
		for (int i = 30; i >= 0; i--) {
			s += (a >> i) & 1;
		}
		// System.out.print(s);
		trie.insert(s);
	}

	public static long get(long a, long k) {
		int c = 0;
		String s = "";
		for (int i = 30; i >= 0; i--) {
			long b = (a >> i) & 1;
			if (((k >> i) & 1) == 1) {
				c += trie.countWords(trie.getNode(s + Long.toString(b)));
				if (trie.isPrefix(s + Long.toString(1 - b))) {
					s += Long.toString(1 - b);
				} else {
					break;
				}
			} else {
				if (trie.isPrefix(s + Long.toString(b))) {
					s += Long.toString(b);
				} else
					break;
			}
		}
		// System.out.println(s);
		return c;
	}
}
