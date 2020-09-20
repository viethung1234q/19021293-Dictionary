import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.io.File;

/**
 * Main method.
 */
public class Main {
    public static void main(String[] args) {
        DictionaryCommandline cmd = new DictionaryCommandline();
        cmd.insertFromFile();
        cmd.showAllWords();
    }
}
