public class Word {
    private String word_target;
    private String word_explain;

    Word() {
        word_target = "";
        word_explain = "";
    }

    Word(Word newWord) {
        this.word_target = newWord.getWord_target();
        this.word_explain = newWord.getWord_explain();
    }

    Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }

    // Word_target's getter/setter
    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    public String getWord_target() {
        return word_target;
    }

    // Word_explain's getter/setter
    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

    public String getWord_explain() {
        return word_explain;
    }
    
    // Compare 2 word to sort them in order
    public int compareTo(Word other) {
        return (this.word_target.compareTo(other.word_target));
    }
}
