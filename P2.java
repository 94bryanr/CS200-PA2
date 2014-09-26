import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class P2 {
    public static void main(String[] args) {
        //Object to hold word information for given web pages.
        WebPages webPage = new WebPages();
        //Commands from input file
        ArrayList<String> commands = parseInputFile(args[0]);
        //Scan in files until *EOF* command
        boolean reachedEOF = false;
        for (String command : commands) {
            if (command.equals("*EOFs*"))
                reachedEOF = true;
            if (!reachedEOF) {
                webPage.addPage(command);
                webPage.printTerms();
            }
            if (reachedEOF) {
                for (String appearence : webPage.whichPages(command)) {
                    System.out.println(appearence);
                }
            }

            webPage.pruneStopWords(2);
        }
    }

    public static ArrayList<String> parseInputFile(String filename) {
        ArrayList<String> commands = new ArrayList<String>();

        File input = new File(filename);
        try {
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                commands.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return commands;
    }
}