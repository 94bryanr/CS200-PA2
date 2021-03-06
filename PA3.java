import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
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
            ArrayList<String> nonEmptyCommands = new ArrayList<String>();
            for(String command: commands){
                if(command.trim().compareTo("") != 0)
                nonEmptyCommands.add(command);
            }
            commands = nonEmptyCommands;

            if (commands.size() == 0) {
                System.out.println("Error: empty file");
            } else {
                //Scan in files until *EOF* command
                boolean reachedEOF = false;
                for (String command : commands) {
                    if (command.equals("*EOFs*")) {
                        webPage.printTerms();
                        reachedEOF = true;
                        System.out.println();
                        continue;
                    }
                    if (!reachedEOF) {
                        webPage.addPage(command);
                    }
                    if (reachedEOF) {
                        webPage.getTermIndex().get(command, true);
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