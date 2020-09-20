public class DictionaryCommandline extends DictionaryManagement {
    public void showAllWords() {
        System.out.printf("%-4s|%-15s|%-15s%n","No", "Word", "Meaning");
        for (int i = 0; i < myDictionary.getWordCount(); i++) {
            System.out.printf("%-4d|%-15s|%-15s%n", i + 1, myDictionary.getWords()[i].getWord_target(),
                                                           myDictionary.getWords()[i].getWord_explain());
        }
    }
    public void dictionaryBasic() {
        insertFromCommandline();
        showAllWords();
    }
}
