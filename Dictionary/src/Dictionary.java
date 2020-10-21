import java.io.*;
import java.util.*;


public class Dictionary {
	private static TreeMap<String, String> words = new TreeMap<>();
	
	// Initialize words loaded from file to Dictionary.
	static {
		try {
			File f = new File("EnglishTranslateOriginal.txt");
			Scanner sc = new Scanner(f);
			while (sc.hasNextLine()) {
				String englishWord = sc.next();
				String meaning = sc.nextLine().substring(1);
				
				words.put(englishWord, meaning);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	 // Getter / Setter words.
	 public static TreeMap<String, String> getWords() {
		 return words;
	 }
	 public static void setWords(TreeMap<String, String> words) {
		 Dictionary.words = words;
	 }
	 
	 // Add word method.
	 public static void addWord (String name, String meaning) {
		words.put(name, meaning);
	 }
	 
	 // Delete word method.
	 public static boolean deleteWord (String name) {
		 Object done = words.remove(name);
		 if (done == null) return false;
		 else return true;
	 }
	 
	 // Change word method.
	 public static String changeWord (String name, String meaning) {
		 return words.replace(name, meaning);
	 }
	 
	 // Move words from Dictionary to file.
	 public static void saveToFile() {
		 try {
			 Formatter f = new Formatter("EnglishTranslate.txt");
			 f.format("%s", " ");
			 for (Map.Entry<String, String> entry : words.entrySet()) {
				 f.format("%s %s%n", entry.getKey(), entry.getValue());
			 }
			 f.close();
		 } catch (FileNotFoundException e) {
			 e.printStackTrace();
		 }
	 }
	 
	 
	 // Get word from file to Dictionary.
	 public static void loadFromFile() {
		 try {
			File f = new File("EnglishTranslate.txt");
			Scanner sc = new Scanner(f);
			while (sc.hasNextLine()) {
				String englishWord = sc.next();
				String meaning = sc.nextLine().substring(1);
				
				words.put(englishWord, meaning);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	 }
	 
}
