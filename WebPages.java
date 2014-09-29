import java.util.ArrayList;

public class WebPages {
    //Holds list of Term objects associated with each parsed word in web page
    private ArrayList<Term> termIndex;

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
            for (Term word : termIndex) {
                System.out.println("WORDS");
                System.out.println(word.getName());
            }
        }
    }

    public ArrayList<Term> mergeSort (ArrayList<Term> list, int first, int last, int sortType){
        ArrayList<Term> leftSide = new ArrayList<Term>();
        ArrayList<Term> rightSide = new ArrayList<Term>();
        int mid =0;
        if (list.size()<=1){
            return list;
        }
        else {
           mid = list.size()/2;
           for (int i =0; i<mid;i++){
                leftSide.add(list.get(i));
            }
           for (int j = mid; j<list.size(); j++){
                rightSide.add(list.get(j));
           }
           if (sortType == 0){
                mergeSort(leftSide,0,mid,0);
                mergeSort(rightSide,mid+1,list.size(),0);
                list = merge (leftSide,rightSide,list, 0);
           }
           else{
                mergeSort(leftSide,0,mid,1);
                mergeSort(rightSide,mid+1,list.size(),1);
                list = merge (leftSide,rightSide,list, 1);
                }

    }
        return list;
    }

    private ArrayList<Term> merge (ArrayList<Term> leftS,ArrayList<Term> rightS,ArrayList<Term> wholeS, int sign){
        int left = 0;
        int right = 0;
        int total = 0;
        int mergecount =0;
        while (left <= leftS.size()-1 && right <= rightS.size()-1){
            if (sign == 0){
                if ((leftS.get(left).compareTo(rightS.get(right)) <= 0)){
                    wholeS.set(total,leftS.get(left));
                    left++;
                    mergecount++;
                }
                else{
                    wholeS.set(total, rightS.get(right));
                    right++;
                }
                total++;
                }
            else {
                if (leftS.get(left).getTotalFrequency() <= rightS.get(right).getTotalFrequency()){
                    wholeS.set(total,leftS.get(left));
                    left++;
                    mergecount++;
                    }
                else{
                    wholeS.set(total, rightS.get(right));
                    right++;
                 }
                total++;
                }
            }
        ArrayList<Term> finished;
        int last= 0;
        if(left >= leftS.size()){
            finished = rightS;
            last = right;
        }
        else{
            finished = leftS;
            last = left;
        }
        for (int i = last; i<finished.size();i++){
            wholeS.set(total,finished.get(i));
            total++;
        }
        //System.out.println("number of times:" + mergecount);
        return wholeS;
    }

    //Prunes out *n* most common words
    public void pruneStopWords(int n){
        //Use merge sort
        termIndex = mergeSort(termIndex,0,termIndex.size(),1);
        for (int i = 0; i < n; i++){
            termIndex.remove(termIndex.size()-1);
        }
        termIndex = mergeSort(termIndex,0,termIndex.size(),0);
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
}