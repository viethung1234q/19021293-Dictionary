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

        /*try {
            Formatter f = new Formatter("myFile");
            f.format("day la file mau");
            f.format("%s %s %s%n", "1","John","Smith");
            f.format("%s %s %s%n", "2","Amy","Brown");
            f.close();
        } catch (FileNotFoundException e) {
            System.out.print("Error");
        }*/

        /*try {
            File x = new File("myFile");
            Scanner sc = new Scanner(x);
            //StringBuilder content = new StringBuilder();
            while(sc.hasNextLine()) {
                System.out.print(sc.nextLine() + "\n");
            }
            //System.out.println(content);
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.print("Error");
        }*/

    }
}
