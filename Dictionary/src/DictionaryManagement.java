import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class DictionaryManagement {
    protected Dictionary myDictionary;

    DictionaryManagement() {
        myDictionary = new Dictionary();
    }

    // myDictionary's getter/setter
    public void setMyDictionary(Dictionary myDictionary) {
        this.myDictionary = myDictionary;
    }
    public Dictionary getMyDictionary() {
        return myDictionary;
    }

    public void insertFromCommandline() {
        System.out.print("Please enter number of words: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        String s = sc.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.print("Please enter Word to Dictionary: ");
            String englishWord = sc.nextLine();

            System.out.print("Please enter Vietnamese meaning to Dictionary: ");
            String meaning = sc.nextLine();
            System.out.println();

            Word newWord = new Word(englishWord, meaning);
            myDictionary.addWord(newWord);
        }
    }

    public void insertFromFile() {
        try {
            File x = new File("myFile");
            Scanner sc = new Scanner(x);
            while (sc.hasNextLine()) {
                String englishWord = sc.next();
                String blank = sc.nextLine();
                String meaning = blank.substring(4);
                Word newWord = new Word(englishWord, meaning);
                myDictionary.addWord(newWord);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.print("Error");
        }
    }
}
