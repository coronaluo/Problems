package leetcode.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FindDuplicatedFiles {
	public static void main(String[] args) {
		HashMap<String, List<String>> rst = new HashMap<String, List<String>>();
		new FindDuplicatedFiles().findDuplicatedFiles(new File("/Users/coronaluo/Workspace/leetcode/Solution/testfiles"), rst);
		for (Map.Entry<String, List<String>> entry : rst.entrySet()) {
			for (String path : entry.getValue()) {
				System.out.println(path);
			}
			System.out.println("---");
		}
	}
	
	private String getFileData(File f) {
		StringBuilder data = new StringBuilder((int)f.length());
		try {
			Scanner scanner = new Scanner(f);
			while (scanner.hasNextLine()) data.append(scanner.nextLine() + "\n");
			return data.toString();
		} catch (IOException e) {
			throw new RuntimeException("cant read file " + f.getAbsolutePath(), e);
		}
		
	}
	public void findDuplicatedFiles(File dir, HashMap<String, List<String>> rst) {
		if (dir == null) return ;
		for (File child : dir.listFiles()) {
			if (child.isDirectory()) {
				findDuplicatedFiles(child, rst);
			} else {
				String filedata = getFileData(child);
				//System.out.println(filedata);
				if (!rst.containsKey(filedata)) {
					rst.put(filedata, new ArrayList<String>());
				}
				rst.get(filedata.toString()).add(child.getAbsolutePath());
			}
		}
	}
}
