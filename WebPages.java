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
                        if (word.equals(term.getName())) {
                            term.addNewOccurrence(document);
                            cont = false;
                            add = false;
                        }
                    }
                }
                if(add) {
                    addNewTerm(word, document);
                }
            }
        }

        //To sort after adding all pages
        ms.mergesort(termIndex);
    }

    private void addNewTerm(String name, String document){
        Term newTerm = new Term(name);
        newTerm.addNewOccurrence(document);
        termIndex.add(newTerm);
    }

    //Iterates through the array of termIndex and prints each word
    public void printTerms(){
        System.out.println("WORDS");
            for (Term word : termIndex) {
                System.out.println(word.getName());
        }
    }

    //Prunes out *n* most common words
    public void pruneStopWords(int n){
        System.out.println();
        ms.getCounter(); //Sets counter to 0
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
        printTerms();
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