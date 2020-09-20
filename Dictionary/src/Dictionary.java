public class Dictionary {
    private Word[] words;
    private int wordCount;

    public Dictionary() {
        this.words = new Word[100];
        this.wordCount = 0;
    }

    public void addWord(Word newWord) {
        words[wordCount] = newWord;
        wordCount++;
    }

    public int getWordCount() {
        return wordCount;
    }
    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public Word[] getWords() {
        return words;
    }
    public void setWords(Word[] words) {
        this.words = words;
    }
}
