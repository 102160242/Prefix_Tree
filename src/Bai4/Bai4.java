package Bai4;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import TrieLib.Trie;

public class Bai4 {

	public static void main(String[] args) throws IOException {
		Trie trie = new Trie();

		Scanner sc = null;
		FileOutputStream outputStream = null;
		String s = "";
		int n, m;
		try {
			sc = new Scanner(new File(
					"C:\\Users\\doanh\\eclipse-workspace\\DoAnGiaiThuatLapTrinh\\DoAn_Trie\\src\\Bai4\\data\\secret\\1.in"));
			outputStream = new FileOutputStream(
					"C:\\Users\\doanh\\eclipse-workspace\\DoAnGiaiThuatLapTrinh\\DoAn_Trie\\src\\Bai4\\data\\secret\\1.ans");
			// Doc m, n, xau S can kiem tra
			n = sc.nextInt();
			m = sc.nextInt();
			sc.nextLine();
			s = sc.nextLine();
			// Doc tu dien
			for (int i = 0; i < m; i++) {
				String s1 = sc.nextLine();
				// System.out.println(s1);
				trie.insert(s1);
			}

			int[] cnt = new int[s.length()];
			for (int i = 0; i < s.length(); i++)
				cnt[i] = 0;

			// Xac dinh
			for (int i = 0; i < n; i++) {
				String s2 = "";
				// Xau S[j]
				for (int j = 0; j <= i; j++) {
					s2 += s.charAt(j);
				}
				cnt[i] = ((i == 0) ? 1 : cnt[i - 1] + 1);

				// Kiem tra hau to
				for (int j = i; j >= 0; j--) {
					String s3 = getLastnCharacters(s2, i - j + 1);
					if (trie.contains(s3)) // Neu s3 la hau to cua s2 va s3 la mot tu khoa
					{
						int index = i - s3.length();
						if(index < 0) cnt[i] = 0;
						else
						cnt[i] = (cnt[i] > cnt[index]) ? cnt[index] : cnt[i];
					}
					// System.out.println(cnt[i]);
				}
			}
			int output = cnt[n - 1]; // So cach chia
			outputStream.write(String.valueOf(Integer.toString(output)).getBytes());
			System.out.println(output);
		} finally {
			if (sc != null) {
				sc.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}

	public static String getLastnCharacters(String inputString, int subStringLength) {
		int length = inputString.length();
		if (length <= subStringLength) {
			return inputString;
		}
		int startIndex = length - subStringLength;
		return inputString.substring(startIndex);
	}
}
