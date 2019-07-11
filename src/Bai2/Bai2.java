package Bai2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import TrieLib.*;

public class Bai2 {
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String args[]) throws IOException {
		Trie trie = new Trie();

		Scanner sc = null;
		FileOutputStream outputStream = null;

		String s = "";
		try {
			sc = new Scanner(new File(
					"C:\\Users\\doanh\\eclipse-workspace\\DoAnGiaiThuatLapTrinh\\DoAn_Trie\\src\\Bai2\\data\\secret\\10.in"));
			outputStream = new FileOutputStream(
					"C:\\Users\\doanh\\eclipse-workspace\\DoAnGiaiThuatLapTrinh\\DoAn_Trie\\src\\Bai2\\data\\secret\\10.ans");
			int n = sc.nextInt();
			int m = sc.nextInt();
			sc.nextLine();
			// Doc bieu tuong cam xuc
			for (int i = 0; i < n; i++) {
				String emoji = sc.nextLine();
				// System.out.println(emoji);
				trie.insert(emoji);
			}
			// Doc noi dung van ban can kiem tra
			for (int i = 0; i < m; i++) {
				s += sc.nextLine() + "\n";
			}

			int dem = 0;
			for (int i = 0; i < s.length(); i++) {
				String t = "";
				for (int j = i; j < s.length(); j++) {
					t += s.charAt(j);
					if (!trie.isPrefix(t)) {
						break;
					}
					if (trie.contains(t)) {
						dem++;
						i = j;
						break;
					}
				}
			}
			outputStream.write(String.valueOf(Integer.toString(dem)).getBytes());
			System.out.println(dem);
		} finally {
			if (sc != null) {
				sc.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}
}
