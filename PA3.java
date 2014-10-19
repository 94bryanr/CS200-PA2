import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PA3 {
    public static final int DEBUG = 0;

    public static void main(String[] args) {
        if (args.length < 1 || args.length >1){
            System.out.println("Error: incorrect arguments");
        }
        else {
            //Object to hold word information for given web pages.
            WebPages webPage = new WebPages();
            //Commands from input file
            ArrayList<String> commands = parseInputFile(args[0]);
            if (commands.size() == 0) {
                System.out.println("Error: empty file");
            } else {
                //Scan in files until *EOF* command
                boolean reachedEOF = false;
                for (String command : commands) {
                    if (command.equals("*EOFs*")) {
                        webPage.printTerms();
                        reachedEOF = true;
                        continue;
                    }
                    if (!reachedEOF) {
                        webPage.addPage(command);
                    }
                    if (reachedEOF) {
                        String whichPagesString = command;
                        String appearancesString = "";
                        boolean found = false;
                        for (String appearance : webPage.whichPages(command)) {
                            found = true;
                            appearancesString = appearance + ", " + appearancesString;
                        }
                        if (!found) {
                            System.out.println(whichPagesString + " not found");
                        }
                        if (found) {
                            appearancesString = appearancesString.substring(0, appearancesString.length() - 2);
                            System.out.println(command + " found in: " + appearancesString);
                        }
                    }
                }
            }
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