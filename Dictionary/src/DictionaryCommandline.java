import java.util.Scanner;

public class DictionaryCommandline extends DictionaryManagement {
    public void showAllWords() {
        System.out.printf("%-4s|%-15s|%-15s%n","No", "Word", "Meaning");
        int i = 1;
        for (Word item : myDictionary.words) {
            if (i <= myDictionary.words.size()) {
                System.out.printf("%-4d|%-15s|%-15s%n", i, item.getWord_target(), item.getWord_explain());
                i++;
            }
        }
    }

    public void dictionarySearcher() {
        System.out.print("- Enter word you want to search: ");
        Scanner sc = new Scanner(System.in);
        String searchWord = sc.nextLine();
        int length = searchWord.length();

        for (Word item : myDictionary.words) {
            if (searchWord.equals(item.getWord_target().substring(0, length))) {
                System.out.println(item.getWord_target());
            }
        }
    }

    public void dictionaryBasic() {
        insertFromCommandline();
        showAllWords();
    }

    public void dictionaryAdvanced() {
        insertFromCommandline();
        showAllWords();
        dictionaryLookup();
    }
}
