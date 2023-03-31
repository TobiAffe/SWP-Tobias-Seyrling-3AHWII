package streams;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class sourcecode_cleaner {

	static class MyFilter implements FilenameFilter {
		private String fileExtension;

		public MyFilter(String fileExtension) {
			this.fileExtension = fileExtension;
		}

		public boolean accept(File dir, String name) {
			return name.endsWith("." + fileExtension) || dir.isDirectory();
		}
	}

	public static void main(String[] args) throws IOException {
		File srcDir = new File("C:\\tmp\\a");
		File destDir = new File("C:\\tmp\\b");
		cleanSource(srcDir, destDir, "java", 0, srcDir);
	}

	public static void cleanSource(File srcDir, File destDir, String fileExtension, int level, File originalDir) throws IOException {
		MyFilter filter = new MyFilter("fileExtension");
		File[] files = srcDir.listFiles(filter);

		if (files == null)
			return;
		for (File fc : files) {
			String destString = fc.toString().replace(originalDir.toString(), destDir.toString());
			if (fc.isDirectory()) {
				new File(destString).mkdir();
				System.out.println(printPath(level) + "DIR:" + fc.getName());
				cleanSource(fc, destDir, fileExtension, level + 1,originalDir);

			} else {
				System.out.println(printPath(level) + "FILE:" + fc.getName());
				cleanFile(fc, destString, fileExtension);
			}
		}
	}

	public static String printPath(int level) {
		String str = "";
		for (int i = 0; i < level; i++) {
			str = str + "   ";
		}
		str = str + "|-- ";
		return str;
	}

	public static void cleanFile(File f, String destString, String fileExtension) throws IOException {
		File fnew = new File(destString);
		FileWriter fw = new FileWriter(fnew);
		Scanner s = new Scanner(f);
		s.useDelimiter("\\Z"); // Trennzeichen ist das letzte Zeichen in der Datei
		String content = s.next();
		for (char c : content.toCharArray()) {
			if (f.getName().contains(fileExtension)) {
				switch (c) {
				case 'ü':
					fw.append("ue");
					break;
				case 'ß':
					fw.append("ss");
					break;
				case 'ö':
					fw.append("oe");
					break;
				case 'ä':
					fw.append("ae");
					break;
				default:
					fw.append(c);
					break;
				}
			} else {
				fw.append(c);
			}
		}
		s.close();
		fw.flush();
		fw.close();
	}

}
