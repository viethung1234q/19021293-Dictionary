/**
 * Main method.
 */
public class Main {
    public static void main(String[] args) {
        DictionaryCommandline cmd = new DictionaryCommandline();
        cmd.insertFromFile();
        cmd.dictionaryExportToFile();
    }
}
