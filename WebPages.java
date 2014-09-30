import java.util.ArrayList;

public class WebPages {
    //Holds list of Term objects associated with each parsed word in web page
    private ArrayList<Term> termIndex;
    private MergeSort ms;

    //Constructor
    public WebPages(){
        termIndex = new ArrayList<Term>();
    }

    //Passes filename to HTMLParser to get parsed array WITH duplicates
    //Calls adds terms to termIndex
    public void addPage(String document){
    //add to termIndex the parsed words from *document*
        HTMLParser pageParser = new HTMLParser(document);

        //For each word in our parsed array...
        for(String word: pageParser.getParsedArray()){

            if (termIndex.size() == 0) {
                addNewTerm(word, document);
            }

            else{
                boolean add = false;
                boolean cont = true;
                for(Term term: termIndex){
                    if(cont) {
                        add = true;
                        if(P2.getDebugState() >= 1)
                            System.out.printf("%s %-12s %s %s%n", "Checking:", term.getName(), "Against:", word);
                        if (word.equals(term.getName())) {
                            term.addNewOccurrence(document);
                            if(P2.getDebugState() >= 1)
                                System.out.println("Added: " + word + " Occurrence");
                            cont = false;
                            add = false;
                        }
                    }
                }
                if(add) {
                    addNewTerm(word, document);
                    if(P2.getDebugState() >= 1)
                        System.out.println("Added: " + word);
                }
                if(P2.getDebugState() >= 1) {
                    System.out.printf("%-15s %-20s %-20s %s %n",
                            "Current List:", "Document Frequency", "Total Frequency", "Occurrences");
                    printTerms();
                    System.out.println("NEXT ITERATION\n");
                }
            }
        }
    }

    private void addNewTerm(String name, String document){
        Term newTerm = new Term(name);
        newTerm.addNewOccurrence(document);
        termIndex.add(newTerm);
    }

    //Iterates through the array of termIndex and prints each word
    public void printTerms(){
        if(P2.getDebugState() >= 1) {
            for (Term word : termIndex) {
                System.out.printf("%-15s %-20s %-20s %s%n",
                        word.getName(), word.getDocFrequency(), word.getTotalFrequency(), word.getStringList());
            }
        }

        if(P2.getDebugState() == 0) {
            System.out.println("WORDS");
            for (Term word : termIndex) {
                System.out.println(word.getName());
            }
            System.out.println();

        }
    }

    //Prunes out *n* most common words
    public void pruneStopWords(int n){
        Term.compareType = 1; //Sets to compare terms by frequency
        ms.mergesort(termIndex); // sort by total frequency
        System.out.println("Copies: " + ms.getCounter()); // Prints number of times mergesort ran
        //removes most frequent words
        for (int i = 0; i < n; i++){
            termIndex.remove(termIndex.size()-1);
        }
        Term.compareType = 0; //Sets to compare terms alphabetically
        ms.mergesort(termIndex); // sort alphabetically
        System.out.println("Copies: " + ms.getCounter()); // Prints number of times mergesort ran

        System.out.println();
    }

    //Prints which pages *word* exist on
    public String[] whichPages(String word) {
        word = word.toLowerCase();
        ArrayList<String> pages = new ArrayList<String>();
        for(Term term: termIndex){
            if (term.getName().equals(word)){
                for(Occurrence occ: term.getDocsList()){
                    pages.add(occ.getDocName());
                }
            }
        }
        return pages.toArray(new String[pages.size()]);
    }

    public int getLength(){
        return termIndex.size();
    }

    public Term[] getTermIndexArray(){
        Term[] words = new Term[0];
        words = termIndex.toArray(words);
        return words;
    }
}