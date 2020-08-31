package com;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Assignment4 {

	static Map<String, File> map = new HashMap<String, File>();
	static List<String> list = new ArrayList<String>();

	public static void main(String args[]) {
		String folder_path = "";
		System.out.println("Please give the folder path");

		try (Scanner sc = new Scanner(System.in)) {

			folder_path = sc.next();

			addFiles(folder_path);

			display();

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	// method to get month name(MMM) of file created
	public static String getMonthName(File file) {
		String date_str = getFileCreatedDate(file).toString();
		String month = date_str.substring(4, 7);
		return month;
	}

	// method to get file created date
	public static Date getFileCreatedDate(File file) {
		return new Date(file.lastModified());
	}

	// method to get files from given folder and subfolder
	public static void addFiles(String folder_path) throws IOException {
		try {
			DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(folder_path));

			for (Path path : directoryStream) {

				File file = path.toFile();
				addFiles(file.getPath());
				if (null != map.get(getMonthName(file))
						&& getFileCreatedDate(map.get(getMonthName(file))).compareTo(getFileCreatedDate(file)) >= 0) {
					map.put(getMonthName(file), map.get(getMonthName(file)));
				} else {
					map.put(getMonthName(file), file);
				}

				map.put(getMonthName(file), file);
				list.add(getMonthName(file));
			}
		} catch (Exception e) {

		}
	}

	// method to display output in required format
	public static void display() {
		System.out.println("Total number of files in given folder and its sub folders : " + list.size());
		System.out.println("Month wise earliest created files list.");
		for (Map.Entry<String, File> entry : map.entrySet()) {
			System.out.println("Month : " + entry.getKey());
			System.out.println("File : " + entry.getValue());
			System.out.println("Date : " + new Date(entry.getValue().lastModified()));
		}
	}
}