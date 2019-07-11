package Bai1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import TrieLib.Trie;

public class Bai1 {
	public static void main(String[] args) throws IOException {
		Trie trie = new Trie();

		Scanner sc = null;
		FileOutputStream outputStream = null;
		int dem = 0;
		String s = "";
		try {
			sc = new Scanner(new File(
					"C:\\Users\\doanh\\eclipse-workspace\\DoAnGiaiThuatLapTrinh\\DoAn_Trie\\src\\Bai1\\data\\secret\\10.in"));
			outputStream = new FileOutputStream(
					"C:\\Users\\doanh\\eclipse-workspace\\DoAnGiaiThuatLapTrinh\\DoAn_Trie\\src\\Bai1\\data\\secret\\10.ans");
			s = sc.nextLine();
			int n = sc.nextInt();
			sc.nextLine();
			// Doc tu dien
			// System.out.print(s);
			for (int i = 0; i < n; i++) {
				String s1 = sc.nextLine();
				// System.out.println(s1);
				trie.insert(s1);
			}
			// Xac dinh
			int start_pos = 0;
			String t = "";
			boolean dk = true; // Dieu kien chay
			while (dk) {
				t = "";
				for (int i = start_pos; i < s.length(); i++) {
					t += s.charAt(i);
					if (!trie.isPrefix(t)) {
						// Thay doi gia tri start_pos
						start_pos++;
						break;
					} else if (trie.contains(t))
						dem++;
					if (i == s.length() - 1)
						start_pos++;
				}
				if (start_pos == s.length())
					dk = false; // Neu da xet het xau, dung vong lap!
			}
			outputStream.write(String.valueOf(Integer.toString(dem)).getBytes());
			System.out.print(dem);
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
