package Bai10;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Bai10 {
	static Trie_Bai10 trie = new Trie_Bai10();

	public static void main(String[] args) throws IOException {
		Scanner sc = null;
		FileOutputStream outputStream = null;
		try {
			sc = new Scanner(new File(
					"C:\\Users\\doanh\\eclipse-workspace\\DoAnGiaiThuatLapTrinh\\DoAn_Trie\\src\\Bai10\\data\\secret\\10.in"));
			outputStream = new FileOutputStream(
					"C:\\Users\\doanh\\eclipse-workspace\\DoAnGiaiThuatLapTrinh\\DoAn_Trie\\src\\Bai10\\data\\secret\\10.ans");
			int n = sc.nextInt();
			sc.nextLine();
			for (int i = 0; i < n; i++) {
				String s = "";
				s = sc.nextLine();
				trie.insert(s);
			}
			int ans = trie.count(trie.getNode(""), 0, 0);
			outputStream.write(String.valueOf(Integer.toString(ans)).getBytes());
			System.out.print(ans);
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
