package Bai6;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import TrieLib.Trie;

public class Bai6 {
	static Trie trie = new Trie();

	public static void main(String[] args) throws IOException {
		Scanner sc = null;
		FileOutputStream outputStream = null;
		int n;
		try {
			sc = new Scanner(new File(
					"C:\\Users\\doanh\\eclipse-workspace\\DoAnGiaiThuatLapTrinh\\DoAn_Trie\\src\\Bai6\\data\\secret\\1.in"));
			outputStream = new FileOutputStream(
					"C:\\Users\\doanh\\eclipse-workspace\\DoAnGiaiThuatLapTrinh\\DoAn_Trie\\src\\Bai6\\data\\secret\\1.ans");
			// Doc n can kiem tra
			n = sc.nextInt();
			sc.nextLine();
			long max = 0;
			long a = sc.nextInt();
			addBit(a);
			// Doc tu dien
			for (int i = 1; i < n; i++) {
				a = sc.nextInt();
				long mXor = maxXor(a);
				max = max > mXor ? max : mXor;
				addBit(a);
				// System.out.println(max);
				// trie.insert(s1);
			}
			// System.out.println(a);
			outputStream.write(String.valueOf(Long.toString(max)).getBytes());
			System.out.println(max);
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

	public static long maxXor(long a) {
		int c = 0;
		String s = "";
		for (int i = 30; i >= 0; i--) {
			long b = (a >> i) & 1;
			if (trie.isPrefix(s + Long.toString(1 - b))) {
				c += 1 << i;
				s += Long.toString(1 - b);
			} else {
				s += Long.toString(b);
			}
		}
		return c;
	}
}
