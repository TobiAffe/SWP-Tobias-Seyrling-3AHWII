package file_tree;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

public class file_tree {
	
	static class MyFilter implements FilenameFilter {

		@Override
		public boolean accept(File dir, String name) {
			return name.endsWith(".class") || name.endsWith(".java") ||
				name.length() >= 10 || dir.length() <= 1024 * 1024 || dir.isDirectory();
					
		}
	}
	
	static int dir = 0;
	static int file = 0;
	static int size = 0;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		File f = new File("C:\\Users\\tseyr\\Desktop");
		tree(0, f);
		System.out.println();
		System.out.println("dircount" + dir);
		System.out.println("filecount:" + file);
		System.out.println("total size:" + size / (1024*1024) + "MB");
	}
	
	public static void tree (int level, File f) throws IOException {
		{
			MyFilter filter = new MyFilter();
			File[] files = f.listFiles(filter);
			
			if (files == null) return;
			for (File fc : files)
			{
				if (fc.isDirectory())
				{
					System.out.println(printPath(level) + "DIR:" + fc.getName());
					tree(level + 1, fc);
					dir++;
				} else
				{
					BasicFileAttributes attr = Files.readAttributes(fc.toPath(), BasicFileAttributes.class);
					FileTime fileTime = attr.creationTime();
					System.out.println(printPath(level) + "FILE:" + fc.getName() + " | " + 
							"DATE:" + fileTime + " | " + fc.length() + "Byte");
					file++;
					size += fc.length();
				}
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
}
