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
            if (command.equals("*EOFs*")) {
                System.out.println("Command: " + command);
                reachedEOF = true;
                System.out.println();
                continue;
            }
            if (!reachedEOF) {
                System.out.println("Command: " + command);
                webPage.addPage(command);
                webPage.printTerms();
                System.out.println("END\n\n");
            }
            if (reachedEOF) {
                System.out.println("Command: " + command);
                for (String appearance : webPage.whichPages(command)) {
                    System.out.println(appearance);
                }
                System.out.println();
            }
        }

        //webPage.pruneStopWords(3);
        //webPage.printTerms();
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