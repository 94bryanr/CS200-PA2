import java.util.ArrayList;

public class WebPages {
    //Holds list of Term objects associated with each parsed word in web page
    private BST termIndex;

    private MergeSort ms;

    //Number of webpage files
    private int docCount;

    //Constructor
    public WebPages(){
        docCount = 0;
        termIndex = new BST();
    }

    //Passes filename to HTMLParser to get parsed array WITH duplicates
    //Calls adds terms to termIndex
    public void addPage(String document){
        //add to termIndex the parsed words from *document*
        HTMLParser pageParser = new HTMLParser(document);

        docCount++;

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
        //No longer needed:
        //ms.mergesort(termIndex);
    }

    private void addNewTerm(String name, String document){
        Term newTerm = new Term(name);
        newTerm.addNewOccurrence(document);
        termIndex.add(newTerm);
    }

    //Iterates through the array of termIndex and prints each word
    public void printTerms() {
        System.out.println("WORDS");
        for (Term word : termIndex) {
            System.out.println(word.getName());
        }
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

    public int getDocCount() {
        return docCount;
    }

    public BST getTermIndex() {
        return termIndex;
    }
}