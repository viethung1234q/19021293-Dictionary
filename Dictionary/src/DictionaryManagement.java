import java.util.Formatter;
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

    // Insert words from commandline
    public void insertFromCommandline() {
        System.out.print("- Please enter number of words: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        String s = sc.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.print("- Please enter Word to Dictionary: ");
            String englishWord = sc.nextLine();

            System.out.print("- Please enter Vietnamese meaning to Dictionary: ");
            String meaning = sc.nextLine();
            System.out.println();

            Word newWord = new Word(englishWord, meaning);
            myDictionary.words.add(newWord);
        }
    }

    // Insert words from file
    public void insertFromFile() {
        try {
            File f = new File("dictionaries");
            Scanner sc = new Scanner(f);

            while (sc.hasNextLine()) {
                String englishWord = sc.next();
                String blank = sc.nextLine();
                String meaning = blank.substring(4);
                Word newWord = new Word(englishWord, meaning);
                myDictionary.words.add(newWord);
            }

            sc.close();
        } catch (FileNotFoundException e) {
            System.out.print("Error\n");
        }
    }

    //Export words to file
    public void dictionaryExportToFile() {
        try {
            Formatter f = new Formatter("ExportFile");

            for (Word item : myDictionary.words) {
                f.format("%s    %s%n", item.getWord_target(), item.getWord_explain());

            }

            f.close();
        } catch (FileNotFoundException e) {
            System.out.print("\nError");
        }
    }

    //Insert word
    public void addWord() {
        System.out.print("\n- Enter word you want to add: ");
        Scanner sc = new Scanner(System.in);
        String newWord = sc.nextLine();

        System.out.print("- Meaning: ");
        String meaning = sc.nextLine();

        Word addWord = new Word(newWord, meaning);
        myDictionary.words.add(addWord);
    }

    // Delete word
    public void deleteWord() {
        System.out.print("\n- Enter word you want to delete: ");
        Scanner sc = new Scanner(System.in);
        String deleteWord = sc.nextLine();

        for (Word item : myDictionary.words) {
            if (deleteWord.equals(item.getWord_target())) {
                Word delete = new Word();
                delete.setWord_target(deleteWord);
                delete.setWord_explain(item.getWord_explain());
                myDictionary.words.remove(delete);
                break;
            }
        }
    }

    //Change word and its meaning
    public void changeWord() {
        System.out.print("\n- Enter word you want to change: ");
        Scanner sc = new Scanner(System.in);
        String changeWord = sc.nextLine();

        System.out.print("\n- Enter word you want to add in and its meaning: ");
        String addWord = sc.nextLine();
        String meaning = sc.nextLine();

        for (Word item : myDictionary.words) {
            if (changeWord.equals(item.getWord_target())) {
                item.setWord_target(addWord);
                item.setWord_explain(meaning);
            }
        }
    }

    // Search word in dictionary
    public void dictionaryLookup() {
        System.out.print("\n- Enter word you want to search: ");
        Scanner sc = new Scanner(System.in);
        String wordSearch = sc.next();
        boolean find = false;

        for (Word item : myDictionary.words) {
            if (wordSearch.equals(item.getWord_target())) {
                System.out.print("- Vietnamese meaning: ");
                System.out.print(item.getWord_explain() + "\n");
                find = true;
            }
        }
        if (!find) System.out.print("- Cannot find your word !");
    }
}
